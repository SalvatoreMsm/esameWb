/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.piscinamimu;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

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
    void stampaTutto_nonLanciaEccezioni() throws Exception {
        
        String id_istruttore = "IS1";
        String nome = "Piero";
        g.AssumiIstruttore(nome, id_istruttore);
        assertDoesNotThrow(() -> g.StampaTutto());
        
    }
    
    
}
