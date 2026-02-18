/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class ProgrammazionePienaException extends Exception{
    public ProgrammazionePienaException(String nomeCorso) {
        super("Il corso " + nomeCorso + " ha raggiunto il numero massimo di lezioni.");
    }
}
