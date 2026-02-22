/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class CorsiaNonEsistenteException extends Exception{
    public CorsiaNonEsistenteException(String id_corsia){
        super("Corsia: "+id_corsia+" non esistente");
    }
}
