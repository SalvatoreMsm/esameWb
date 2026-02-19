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
    void aggiungiCorso_ok() throws Exception {
        gestore.aggiungiCorso("C1", descr);
        assertEquals("C1", gestore.cercaCorso("C1").getIdCorso());
    }

    @Test
    void aggiungiCorso_giaPresente() throws Exception {
        gestore.aggiungiCorso("C1", descr);

        assertThrows(CorsoGiaPresenteException.class, () -> 
            gestore.aggiungiCorso("C1", descr)
        );
    }

    @Test
    void cercaCorso_ok() throws Exception {
        gestore.aggiungiCorso("C2", descr);
        Corso corso = gestore.cercaCorso("C2");
        assertNotNull(corso);
        assertEquals("C2", corso.getIdCorso());
    }

    @Test
    void cercaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("NON_ESISTE")
        );
    }

    @Test
    void eliminaCorso_ok() throws Exception {
        gestore.aggiungiCorso("C3", descr);
        gestore.eliminaCorso("C3");

        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.cercaCorso("C3")
        );
    }

    /*@Test
    void eliminaCorso_nonPresente() {
        assertThrows(CorsoNonPresenteException.class, () -> 
            gestore.eliminaCorso("nessuno")
        );
    }*/

    @Test
    void aggiungiLezione_alCorsoEsistente() throws Exception {
        gestore.aggiungiCorso("C4", descr);
        Corso corso = gestore.cercaCorso("C4");
        Lezione lezione = new Lezione("L1", "10:00", "11:00");
        corso.aggiungiLezione(lezione);

        assertTrue(corso.isLezionePresente("L1"));
        assertEquals(lezione, corso.cercaLezione("L1"));
    }

    @Test
    void aggiungiLezione_corsoNonPresenteLanciaEccezione() {
        assertThrows(CorsoNonPresenteException.class, () -> {
            // Se il corso non esiste, cercaCorso fallirà
            gestore.cercaCorso("C_no").aggiungiLezione(
                new Lezione("Lx", "10:00", "11:00")
            );
        });
    }

    @Test
    void aggiungiLezione_giaPresente() throws Exception {
        gestore.aggiungiCorso("C5", descr);
        Corso corso = gestore.cercaCorso("C5");
        Lezione l1 = new Lezione("L1", "08:00", "09:00");
        corso.aggiungiLezione(l1);

        assertThrows(LezioneGiaPresenteException.class, () -> corso.aggiungiLezione(l1));
    }

    @Test
    void aggiungiLezione_programmazionePiena() throws Exception {
        // Corso con durata = 2, provo ad aggiungere 3 lezioni
        DescrizioneCorso descPiccolo = new DescrizioneCorso("MiniCorso", "Adulti", 5, 2, 0);
        gestore.aggiungiCorso("C6", descPiccolo);
        Corso corso = gestore.cercaCorso("C6");

        corso.aggiungiLezione(new Lezione("L1", "08:00", "09:00"));
        corso.aggiungiLezione(new Lezione("L2", "09:00", "10:00"));

        assertThrows(ProgrammazionePienaException.class, () ->
            corso.aggiungiLezione(new Lezione("L3", "10:00", "11:00"))
        );
    }

    @Test
    void eliminaLezione_presente() throws Exception {
        gestore.aggiungiCorso("C7", descr);
        Corso corso = gestore.cercaCorso("C7");

        Lezione l1 = new Lezione("L1", "08:00", "09:00");
        corso.aggiungiLezione(l1);
        corso.eliminaLezione("L1");

        assertFalse(corso.isLezionePresente("L1"));
    }

    @Test
    void eliminaLezione_nonPresente() throws Exception {
        gestore.aggiungiCorso("C8", descr);
        Corso corso = gestore.cercaCorso("C8");

        assertThrows(LezioneNonPresenteException.class, () -> corso.eliminaLezione("L1"));
    }
    
    @Test
    void aggiungiLezione_limiteDurata() throws Exception {
        DescrizioneCorso descr = new DescrizioneCorso("Nuoto", "Tutti", 10, 2, 0);
        gestore.aggiungiCorso("C1", descr);

        Corso corso = gestore.cercaCorso("C1");

        corso.aggiungiLezione(new Lezione("L1", "08:00", "09:00"));
        corso.aggiungiLezione(new Lezione("L2", "09:00", "10:00"));

        // Prossima lezione dovrebbe lanciare ProgrammazionePienaException
        assertThrows(ProgrammazionePienaException.class, () -> 
            corso.aggiungiLezione(new Lezione("L3", "10:00", "11:00"))
        );
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
    public void ModificaTipologiaClienti_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "Bambino";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaTipologiaClienti("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals("Bambino", c.getDescrizione().getTipologia_clienti());
        
    }
    
    @Test
    public void ModificaTipologiaClienti_not_ok() throws Exception{
        String input = "Bambino";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaTipologiaClienti("nessuno"));
    }
    
    @Test
    public void ModificaDurata_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaDurata("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals(9, c.getDescrizione().getDurata());
        
    }
    
    @Test
    public void ModificaDurata_not_ok() throws Exception{
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaDurata("nessuno"));
    }
    
    @Test
    public void ModificaNumeroPosti_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaNumeroPosti("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals(9, c.getDescrizione().getNum_posti());
        
    }
    
    @Test
    public void ModificaNumeroPosti_not_ok() throws Exception{
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaNumeroPosti("nessuno"));
    }
    
    @Test
    public void ModificaPostiOccupati_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaPostiOccupati("C1");
        Corso c = gestore.cercaCorso("C1");
        
        assertEquals(9, c.getDescrizione().getNum_posti_occupati());
        
    }
    
    @Test
    public void ModificaPostiOccupati_not_ok() throws Exception{
        String input = "9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaPostiOccupati("nessuno"));
    }
    
    @Test
    public void ModificaOraInizioLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Lezione lezione = new Lezione("L1", "10:00", "11:00");
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione(lezione);
        
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
        Lezione lezione = new Lezione("L1", "10:00", "11:00");
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione(lezione);
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraInizioLezione("C1", "L1"));
    }
    
    @Test
    public void ModificaOraFineLezione_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Lezione lezione = new Lezione("L1", "10:00", "11:00");
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione(lezione);
        
        String input = "12:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        gestore.ModificaOraFineLezione("C1", "L1");
        
        assertEquals("12:00", c.cercaLezione("L1").getOraFine());
        
    }
    
    @Test
    public void ModificaOraFineLezione_corso_not_ok() throws Exception{
        assertThrows(CorsoNonPresenteException.class, () -> gestore.ModificaOraFineLezione("C1", "L1"));
    }
    
    @Test 
    public void ModificaOraFineLezione_lezione_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Corso c = gestore.cercaCorso("C1");
        assertThrows(LezioneNonPresenteException.class, () -> gestore.ModificaOraFineLezione("C1", "nessuna"));
    }
    
    @Test
    public void ModificaOraFineLezione_lezione_orari_not_ok() throws Exception{
        gestore.aggiungiCorso("C1", descr);
        Lezione lezione = new Lezione("L1", "10:00", "11:00");
        Corso c = gestore.cercaCorso("C1");
        c.aggiungiLezione(lezione);
        
        String input = "9:00";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        assertThrows(LezioniConOrariNonValidiException.class, () -> gestore.ModificaOraFineLezione("C1", "L1"));
    }
    
    @Test
    void stampaTutto_nonLanciaEccezioni() throws Exception {
        gestore.aggiungiCorso("C1", new DescrizioneCorso("Nuoto", "Tutti", 10, 3, 0));
        assertDoesNotThrow(() -> gestore.stampaTutto());
    }
    
}
