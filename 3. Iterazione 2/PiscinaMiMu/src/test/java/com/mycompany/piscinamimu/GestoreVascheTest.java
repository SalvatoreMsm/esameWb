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
public class GestoreVascheTest {

    private GestoreVasche gestore;

    // Sottoclassi concrete di Vasca per il test
    static class VascaTest extends Vasca {
        private TipoVasca tipo;
        public VascaTest(String id, TipoVasca tipo){ 
            super(id); this.tipo = tipo; 
        }
        @Override
        public TipoVasca getTipo(){ 
            return tipo; 
        }
    }

    private Corsia corsia1;

    @BeforeEach
    void setUp() {
        gestore = new GestoreVasche();
        corsia1 = new Corsia("C1");
    }

    @Test
    void testAggiungiVascaECerca() throws Exception {
        // aggiungi vasca direttamente
        Vasca v = new VascaTest("V1", Vasca.TipoVasca.DONNE);
        gestore.aggiungiVasca("DONNE", "V1");

        assertEquals("V1", gestore.cercaVasca("V1").getIdVasca());
        assertThrows(VascaNonPresenteException.class, () -> gestore.cercaVasca("NONESISTE"));
    }

    @Test
    void testAggiungiVascaDuplicata() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        assertThrows(VascaGiaPresenteException.class, () -> gestore.aggiungiVasca("DONNE", "V1"));
    }

    @Test
    void testAssegnaCorsiaToVasca() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        gestore.AssegnaCorsiaToVasca("V1", corsia1);

        Vasca v = gestore.cercaVasca("V1");
        assertEquals(corsia1, v.getElencoCorsie().get("C1"));
        assertEquals(v, corsia1.getVasca());
    }

    @Test
    void testCheckCorsiaNonEsistente() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        gestore.AssegnaCorsiaToVasca("V1", corsia1);
        assertThrows(CorsiaGiaEsistenteException.class, () -> gestore.CheckCorsiaNonEsistente("C1"));
    }

    @Test
    void testRemoveCorsiaToVasca() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        gestore.AssegnaCorsiaToVasca("V1", corsia1);

        gestore.RemoveCorsiaToVasca("V1", "C1");

        Vasca v = gestore.cercaVasca("V1");
        assertFalse(v.getElencoCorsie().containsKey("C1"));
        assertNull(corsia1.getVasca());
    }

    @Test
    void testCercaCorsia() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        gestore.AssegnaCorsiaToVasca("V1", corsia1);

        assertEquals(corsia1, gestore.CercaCorsia("C1"));
        assertThrows(CorsiaNonEsistenteException.class, () -> gestore.CercaCorsia("NONESISTE"));
    }

    @Test
    void testMostraVaschePerTipologia() throws Exception {
        gestore.aggiungiVasca("DONNE", "V1");
        gestore.aggiungiVasca("UOMINI", "V2");

        assertDoesNotThrow(() -> gestore.mostraVaschePerTipologia("DONNE"));
        assertDoesNotThrow(() -> gestore.mostraVaschePerTipologia("UOMINI"));
        assertThrows(TipologiaVascaNonEsistenteException.class, () -> gestore.mostraVaschePerTipologia("INVALIDO"));
    }
}