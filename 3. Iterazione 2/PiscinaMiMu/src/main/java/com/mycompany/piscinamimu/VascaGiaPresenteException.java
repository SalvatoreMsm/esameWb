/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class VascaGiaPresenteException extends Exception{
    VascaGiaPresenteException(String id_vasca){
        super("Vasca: "+id_vasca+" gia presente");
    }
}
