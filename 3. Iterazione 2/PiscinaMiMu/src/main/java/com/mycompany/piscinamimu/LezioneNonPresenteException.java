/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class LezioneNonPresenteException extends Exception{
    public LezioneNonPresenteException(String idLezione) {
        super("La lezione con id " + idLezione + " non è presente nel corso.");
    }
}
