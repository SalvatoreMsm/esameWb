/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class CorsiaGiaPresenteException extends Exception{
    CorsiaGiaPresenteException(int id_corsia){
        super("E' gia presente la corsia: "+id_corsia+" assegnata a questa lezione");
    }
}
