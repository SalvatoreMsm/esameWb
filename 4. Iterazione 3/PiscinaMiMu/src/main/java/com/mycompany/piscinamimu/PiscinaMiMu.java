/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class PiscinaMiMu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GestoreCorsi gc = new GestoreCorsi();
        GestoreIstruttori gi = new GestoreIstruttori();
        GestoreClienti gcl = new GestoreClienti();
        GestoreVasche gv = new GestoreVasche();
        
        InterazioneUtente ti = new InterazioneUtente(gc, gi, gcl, gv);

        ti.avvia();
    }
    
}
