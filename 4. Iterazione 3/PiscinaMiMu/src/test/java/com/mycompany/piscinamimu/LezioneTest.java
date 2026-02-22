/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pmilo
 */
class LezioneTest {

    private Lezione lezione;
    private Corsia corsia;
    private Vasca vasca;

    // Sottoclasse concreta di Vasca per i test
class VascaTestImpl extends Vasca {

    public VascaTestImpl(String id_vasca) {
        super(id_vasca);
    }

    @Override
    public TipoVasca getTipo() {
        return TipoVasca.BAMBINI; // valore dummy
    }
}

    @BeforeEach
    void setUp() {
        lezione = new Lezione("L1", "10:00", "11:00");
        vasca = new VascaTestImpl("V1");   // usa la sottoclasse concreta
        corsia = new Corsia("C1");         // costruttore reale
        corsia.setVasca(vasca);            // assegna la vasca alla corsia
    }

    @Test
    void testCostruttoreEGetter() {
        assertEquals("L1", lezione.getIdLezione());
        assertEquals("10:00", lezione.getOraInizio());
        assertEquals("11:00", lezione.getOraFine());
        assertNull(lezione.getCorsia());
    }

    @Test
    void testAddCorsiaOK() throws CorsiaGiaPresenteException {
        lezione.addCorsia(corsia);
        assertEquals(corsia, lezione.getCorsia());
    }

    @Test
    void testAddCorsiaDuplicata() throws CorsiaGiaPresenteException {
        lezione.addCorsia(corsia);
        assertThrows(CorsiaGiaPresenteException.class, () -> lezione.addCorsia(corsia));
    }

    @Test
    void testCheckValidTimeStatic() {
        assertTrue(Lezione.checkValidTimeStatic("10:00", "11:00"));
        assertFalse(Lezione.checkValidTimeStatic("12:00", "11:00"));
    }

    @Test
    void testStampaBreve() throws CorsiaGiaPresenteException {
        // Con corsia e vasca
        lezione.addCorsia(corsia);
        String s = lezione.stampaBreve();
        assertTrue(s.contains("C1"));
        assertTrue(s.contains("V1"));

        // Senza corsia
        lezione.RemoveCorsia();
        s = lezione.stampaBreve();
        assertTrue(s.contains("[Corsia NON assegnata]"));
    }
}