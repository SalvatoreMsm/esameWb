/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class LezioneGiaPresenteException extends Exception{
    public LezioneGiaPresenteException(String idLezione) {
        super("Lezione con ID " + idLezione + " già presente nel corso.");
    }
}
