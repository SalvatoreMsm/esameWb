/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class IstruttoreGiaAssegnatoAlCorsoException extends Exception{
    public IstruttoreGiaAssegnatoAlCorsoException(String id_istruttore){
        super("L'istruttore: " + id_istruttore + " è gia stato precedentemente assegnato al corso");
    }
}
