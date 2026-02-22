/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class TipologiaNonCorrispondente extends Exception{
    public TipologiaNonCorrispondente(String id_cliente, Corso c){
        super("La tipologia del cliente: "+id_cliente+", e del corso: "+c.getIdCorso()+", non corrispondono!");
    }
}
