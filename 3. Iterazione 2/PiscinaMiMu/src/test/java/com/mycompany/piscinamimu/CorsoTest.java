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
public class CorsoTest {

    private Corso corso;
    private DescrizioneCorso descrizione;

    
    @BeforeEach
    void setUp() {
        descrizione = new DescrizioneCorso("NuotoSincronizzato", "Donne", 10, 2, 0);
        corso = new Corso("C1", descrizione);
    }

    @Test
    void aggiungiLezione_ok() throws Exception {
        corso.aggiungiLezione("L1", "10:00", "11:00");
        assertTrue(corso.isLezionePresente("L1"));
    }

    @Test
    void aggiungiLezione_giaPresente() throws Exception {
        Lezione l = new Lezione("L1", "10:00", "11:00");
        corso.aggiungiLezione("L1", "10:00", "11:00");

        assertThrows(LezioneGiaPresenteException.class, () -> corso.aggiungiLezione("L1", "10:00", "11:00"));
    }

    @Test
    void aggiungiLezione_programmazionePiena() throws Exception {
        // durata = 2 → massimo 2 lezioni
        corso.aggiungiLezione("L1", "10:00", "11:00");
        corso.aggiungiLezione("L2", "11:00", "12:00");

        assertThrows(ProgrammazionePienaException.class, () ->
                corso.aggiungiLezione("L3", "12:00", "13:00"));
    }

    @Test
    void eliminaLezione_ok() throws Exception {
        corso.aggiungiLezione("L1", "10:00", "11:00");

        corso.eliminaLezione("L1");

        assertFalse(corso.isLezionePresente("L1"));
    }

    @Test
    void eliminaLezione_nonPresente() throws Exception{
        assertThrows(LezioneNonPresenteException.class, () -> corso.eliminaLezione("L99"));
    }

    @Test
    void cercaLezione_ok() throws Exception {
        corso.aggiungiLezione("L1", "10:00", "11:00");

        Lezione trovata = corso.cercaLezione("L1");

        assertEquals("L1", trovata.getIdLezione());
        assertEquals("10:00", trovata.getOraInizio());
        assertEquals("11:00", trovata.getOraFine());

    }

    @Test
    void cercaLezione_nonPresente() {
        assertThrows(LezioneNonPresenteException.class, () -> corso.cercaLezione("L99"));
    }

    @Test
    void isLezionePresente_test() throws Exception {
        assertFalse(corso.isLezionePresente("L1"));

        corso.aggiungiLezione("L1", "10:00", "11:00");

        assertTrue(corso.isLezionePresente("L1"));
    }

    @Test
    void isSenzaLezioni_test() throws Exception {
        assertTrue(corso.isSenzaLezioni());

        corso.aggiungiLezione("L1", "10:00", "11:00");

        assertFalse(corso.isSenzaLezioni());
    }

    @Test
    void toString_test() throws Exception {
        String s = corso.toString();
        assertTrue(s.contains("ID Corso: C1"));
        assertTrue(s.contains("Nessuna lezione presente"));

        corso.aggiungiLezione("L1", "10:00", "11:00");
        s = corso.toString();
        assertTrue(s.contains("Lezioni:"));
        assertTrue(s.contains("L1"));
    }
}
