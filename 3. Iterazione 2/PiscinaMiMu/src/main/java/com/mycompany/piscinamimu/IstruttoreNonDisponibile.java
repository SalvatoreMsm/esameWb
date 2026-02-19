/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class IstruttoreNonDisponibile extends Exception{
    public IstruttoreNonDisponibile(String id_istruttore){
        super("Istruttore: "+id_istruttore+" non disponibile");
    }
}
