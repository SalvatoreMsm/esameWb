/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.util.Map;
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
public class CorsiaTest {

    private Corsia corsia;
    private Lezione lezione1;
    private Lezione lezione2;
    private Vasca vasca;

    // Sottoclasse concreta di Vasca per test
    static class VascaTestImpl extends Vasca {
        public VascaTestImpl(String id_vasca) { super(id_vasca); }
        @Override
        public TipoVasca getTipo(){ 
            return TipoVasca.MISTA; 
        }
        
    }

    @BeforeEach
    void setUp() {
        corsia = new Corsia("C1");
        vasca = new VascaTestImpl("V1");
        lezione1 = new Lezione("L1", "10:00", "11:00");
        lezione2 = new Lezione("L2", "11:00", "12:00");
    }

    @Test
    void testCostruttoreEGetter() {
        assertEquals("C1", corsia.getIdCorsia());
        assertNull(corsia.getVasca());
        assertTrue(corsia.getElencoLezioni().isEmpty());
    }

    @Test
    void testSetVascaERemoveVasca() {
        corsia.setVasca(vasca);
        assertEquals(vasca, corsia.getVasca());

        corsia.RemoveVasca();
        assertNull(corsia.getVasca());
    }

    @Test
    void testAddLezioneECercaLezione() throws LezioneNonPresenteException {
        corsia.addLezione(lezione1);
        corsia.addLezione(lezione2);

        assertEquals(lezione1, corsia.CercaLezione("L1"));
        assertEquals(lezione2, corsia.CercaLezione("L2"));

        // prova eccezione per lezione inesistente
        assertThrows(LezioneNonPresenteException.class, () -> corsia.CercaLezione("L3"));
    }

    @Test
    void testRemoveLezione() throws LezioneNonPresenteException, CorsiaGiaPresenteException {
        corsia.addLezione(lezione1);
        corsia.addLezione(lezione2);

        corsia.addLezione(lezione1); // garantiamo che corsia sia assegnata
        corsia.addLezione(lezione2);

        corsia.RemoveLezione("L1");
        assertThrows(LezioneNonPresenteException.class, () -> corsia.CercaLezione("L1"));
        assertEquals(lezione2, corsia.CercaLezione("L2"));
    }

    @Test
    void testRemoveAllLezioni() throws CorsiaGiaPresenteException {
        corsia.addLezione(lezione1);
        corsia.addLezione(lezione2);

        corsia.RemoveAllLezioni();
        assertTrue(corsia.getElencoLezioni().isEmpty());
    }

    @Test
    void testToString() {
        // senza vasca
        assertTrue(corsia.toString().contains("vasca NON assegnata"));

        corsia.setVasca(vasca);
        assertTrue(corsia.toString().contains("V1"));
    }
}