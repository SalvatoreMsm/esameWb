/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class CorsoGiaPresenteException extends Exception{
    public CorsoGiaPresenteException(String idCorso) {
        super("Il corso con id " + idCorso + " è già presente.");
    }
}
