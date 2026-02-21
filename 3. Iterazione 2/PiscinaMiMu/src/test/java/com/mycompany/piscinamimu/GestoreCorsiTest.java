/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author salvatore
 */
public class GestoreCorsiTest {
    
    private GestoreCorsi gestore;
    private DescrizioneCorso descr;

    @BeforeEach
    void setUp() {
        gestore = new GestoreCorsi();
        descr = new DescrizioneCorso("NuotoSincronizzato", "Donne", 10, 5, 0);
    }

    @Test
    public void aggiungiCorso_ok() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertEquals("C1", gestore.cercaCorso("C1").getIdCorso());
    }

    @Test
    public void aggiungiCorso_giaPresente() throws Exception {
        gestore.aggiungiCorso("C1", descr);

        assertThrows(CorsoGiaPresenteException.class, () -> 
            gestore.aggiungiCorso("C1", descr)
        );
    }

    @Test
    public void cercaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("NON_ESISTE")
        );
    }

    @Test
    public void eliminaCorso_ok() throws Exception {
        gestore.aggiungiCorso("C3", descr);
        gestore.eliminaCorso("C3");

        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("C3")
        );
    }

    @Test
    public void eliminaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.eliminaCorso("nessuno")
        );
    }

    @Test
    public void aggiungiLezione_alCorsoEsistente() throws Exception {
        gestore.aggiungiCorso("C4", descr);
        Corso corso = gestore.cercaCorso("C4");
        corso.aggiungiLezione("L1", "10:00", "11:00");
        Lezione l = corso.cercaLezione("L1");

        assertTrue(corso.isLezionePresente("L1"));
        assertEquals("L1", l.getIdLezione());
        assertEquals("10:00", l.getOraInizio());
        assertEquals("11:00", l.getOraFine());
    }

    @Test
    public void aggiungiLezione_giaPresente() throws Exception {
        gestore.aggiungiCorso("C5", descr);
        Corso corso = gestore.cercaCorso("C5");
        Lezione l1 = new Lezione("L1", "08:00", "09:00");
        corso.aggiungiLezione("L1", "08:00", "09:00");

        assertThrows(LezioneGiaPresenteException.class, () -> corso.aggiungiLezione("L1", "08:00", "09:00"));
    }

    @Test
    public void aggiungiLezione_programmazionePiena() throws Exception {
        // Corso con durata = 2, provo ad aggiungere 3 lezioni
        DescrizioneCorso descPiccolo = new DescrizioneCorso("MiniCorso", "Adulti", 5, 2, 0);
        gestore.aggiungiCorso("C6", descPiccolo);
        Corso corso = gestore.cercaCorso("C6");

        corso.aggiungiLezione("L1", "08:00", "09:00");
        corso.aggiungiLezione("L2", "09:00", "10:00");

        assertThrows(ProgrammazionePienaException.class, () ->
            corso.aggiungiLezione("L3", "10:00", "11:00")
        );
    }


    @Test
    public void eliminaLezione_nonPresente() throws Exception {
        gestore.aggiungiCorso("C8", descr);
        Corso corso = gestore.cercaCorso("C8");

        assertThrows(LezioneNonPresenteException.class, () -> corso.eliminaLezione("L1"));
    }
    
    
    @Test
    public void ModificaNome_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "Acqua gym";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaNome("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals("Acqua gym", c.getDescrizione().getNome());
        
    }
    
    @Test
    public void ModificaNome_not_ok() throws Exception{
        String input = "Acqua gym";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaNome("nessuno"));
    }
    

    
    @Test
    public void ModificaOraInizioLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "10:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaOraInizioLezione("C1", "L1");
        
        assertEquals("10:00", c.cercaLezione("L1").getOraInizio());
        
    }
    
    @Test
    public void ModificaOraInizioLezione_corso_not_ok() throws Exception{
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaOraInizioLezione("C1", "L1"));
    }
    
    @Test 
    public void ModificaOraInizioLezione_lezione_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        assertThrows(LezioneNonPresenteException.class, () -> gestore.ModificaOraInizioLezione("C1", "nessuna"));
    }
    
    @Test
    public void ModificaOraInizioLezione_lezione_orari_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraInizioLezione("C1", "L1"));
    }
    
    @Test
    public void ModificaOraFineLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaOraFineLezione("C1", "L1");
        
        assertEquals("12:00", c.cercaLezione("L1").getOraFine());
        
    }

    
    @Test
    public void ModificaOraFineLezione_lezione_orari_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione("L1", "10:00", "11:00");
        
        String input = "9:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraFineLezione("C1", "L1"));
    }
    @Test
    public void AssegnaCorso_ok() throws Exception {
        Istruttore istr = new Istruttore("I1", "Mario");
        gestore.aggiungiCorso("C10", descr);
        Corso c = gestore.cercaCorso("C10");

        istr.AssegnaCorso(c);

        assertTrue(istr.getCorsi().containsKey("C10"));
    }

    @Test
    public void AssegnaCorso_nonDisponibile() throws Exception {
        Istruttore istr = new Istruttore("I2", "Luca");
        gestore.aggiungiCorso("C11", descr);
        Corso c = gestore.cercaCorso("C11");

        assertThrows(IstruttoreNonDisponibile.class, () -> 
            istr.AssegnaCorso(c)
        );
    }
    
    

}