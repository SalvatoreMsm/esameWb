/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author salvatore
 */
public class GestoreIstruttoriTest {
    
    private GestoreIstruttori g;
    
    @BeforeEach
    void setUp(){
        g = new GestoreIstruttori();
    }

    @Test
    public void AssumiIstruttore_ok() throws Exception{
        String nome = "Piero";
        String id_corso = "IS1";

        g.AssumiIstruttore(nome, id_corso); 
    
        Istruttore is = new Istruttore("Piero", "IS1");
        assertEquals(is.getIdIstruttore(), g.getIstruttore(is.getIdIstruttore()).getIdIstruttore());
    }
    
    @Test
    public void AssumiIstruttore_not_ok() throws Exception{
        String id_istruttore = "IS1";
        String nome = "Franco";
        g.AssumiIstruttore(nome, id_istruttore);
        
        String id_istruttore2 = "IS1";
        String nome2 = "Gianni";

        assertThrows(IstruttoreGiaAssuntoException.class, () -> g.AssumiIstruttore(nome2, id_istruttore2));       
    }
    
    @Test
    public void stampaTutto_nonLanciaEccezioni() throws Exception {
        
        String id_istruttore = "IS1";
        String nome = "Piero";
        g.AssumiIstruttore(nome, id_istruttore);
        assertDoesNotThrow(() -> g.mostraTuttiIstruttori());
        
    }
    @Test
    public void getIstruttore_not_ok() {
        assertThrows(IstruttoreNonDisponibile.class, () -> g.getIstruttore("NON_ESISTE"));
    }
    @Test
    public void visualizzaIstruttoriDisponibili_ok() throws Exception {
        g.AssumiIstruttore("Piero", "IS1");
        g.AssumiIstruttore("Franco", "IS2");

        // Recupera i2 dal gestore
        Istruttore i2 = g.getIstruttore("IS2");

        // Rendi i2 non disponibile
        DescrizioneCorso cd = new DescrizioneCorso("Nuoto", "Adulti", 10, 5, 0);
        i2.AssegnaCorso(new Corso("C1", cd));
        i2.AssegnaCorso(new Corso("C2", cd));
        i2.AssegnaCorso(new Corso("C3", cd));

        Map<String, Istruttore> disponibili = g.mostraIstruttoriDisponibili();

        assertTrue(disponibili.containsKey("IS1"));
        assertFalse(disponibili.containsKey("IS2"));
    }
    
}
