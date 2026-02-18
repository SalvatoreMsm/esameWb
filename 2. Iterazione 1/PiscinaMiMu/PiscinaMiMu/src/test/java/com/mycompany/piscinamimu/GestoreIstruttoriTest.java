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
        String input = "IS1\nPiero\n";   
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        g.AssumiIstruttore(); 
    
        Istruttore is = new Istruttore("Piero", "IS1");
        assertEquals(is.getIdIstruttore(), g.getIstruttore(is.getIdIstruttore()).getIdIstruttore());
    }
    
    @Test
    public void AssumiIstruttore_not_ok() throws Exception{
        String input = "IS1\nFranco\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        g.AssumiIstruttore();
        
        String second_input = "IS1\nGianni\n";
        System.setIn(new ByteArrayInputStream(second_input.getBytes()));

        assertThrows(IstruttoreGiaAssuntoException.class, () -> g.AssumiIstruttore());       
    }
    
    @Test
    void stampaTutto_nonLanciaEccezioni() throws Exception {
        String input = "IS1\nPiero\n";   
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        g.AssumiIstruttore();
        assertDoesNotThrow(() -> g.StampaTutto());
    }
    
    
}
