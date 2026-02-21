/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class CorsiaNonPresenteNellaVascaException extends Exception{
    CorsiaNonPresenteNellaVascaException(String id_corsia){
        super("La corsia: "+id_corsia+" non è presente nella vasca selezionata");
    }
}
