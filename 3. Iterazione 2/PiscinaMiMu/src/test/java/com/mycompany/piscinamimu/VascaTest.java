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
public class VascaTest {

    private Vasca vasca;
    private Corsia corsia1;
    private Corsia corsia2;

    // Sottoclasse concreta di Vasca per i test
    static class VascaTestImpl extends Vasca {
        public VascaTestImpl(String id_vasca) { super(id_vasca); }
        @Override
        public TipoVasca getTipo() { return TipoVasca.MISTA; }
    }

    @BeforeEach
    void setUp() {
        vasca = new VascaTestImpl("V1");
        corsia1 = new Corsia("C1");
        corsia2 = new Corsia("C2");
    }

    @Test
    void testCostruttoreEGetter() {
        assertEquals("V1", vasca.getIdVasca());
        assertTrue(vasca.getElencoCorsie().isEmpty());
    }

    @Test
    void testAggiungiCorsiaOK() throws CorsiaGiaPresenteException {
        vasca.AggiungiCorsia(corsia1);
        assertEquals(corsia1, vasca.getElencoCorsie().get("C1"));
        assertEquals(vasca, corsia1.getVasca());
    }

    @Test
    void testAggiungiCorsiaDuplicata() throws CorsiaGiaPresenteException {
        vasca.AggiungiCorsia(corsia1);
        assertThrows(CorsiaGiaPresenteException.class, () -> vasca.AggiungiCorsia(corsia1));
    }

    @Test
    void testCercaCorsiaOK() throws CorsiaGiaPresenteException, CorsiaNonPresenteNellaVascaException {
        vasca.AggiungiCorsia(corsia1);
        assertEquals(corsia1, vasca.cercaCorsia("C1"));
    }

    @Test
    void testCercaCorsiaNonPresente() {
        assertThrows(CorsiaNonPresenteNellaVascaException.class, () -> vasca.cercaCorsia("C1"));
    }

    @Test
    void testRimuoviCorsiaOK() throws CorsiaGiaPresenteException, CorsiaNonPresenteNellaVascaException {
        vasca.AggiungiCorsia(corsia1);
        vasca.rimuoviCorsia(corsia1);
        assertTrue(vasca.getElencoCorsie().isEmpty());
        assertNull(corsia1.getVasca());
    }

    @Test
    void testRimuoviCorsiaNonPresente() {
        assertThrows(CorsiaNonPresenteNellaVascaException.class, () -> vasca.rimuoviCorsia(corsia1));
    }

    @Test
    void testToString() throws CorsiaGiaPresenteException {
        vasca.AggiungiCorsia(corsia1);
        String s = vasca.toString();
        assertTrue(s.contains("V1"));
    }
}