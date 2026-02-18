/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class LezioniConOrariNonValidiException extends Exception{
    public LezioniConOrariNonValidiException(String id_lezione){
        super("L'orario di fine lezione anticipa quello di inizio lezione nella lezione: " + id_lezione);
    }
    
}
