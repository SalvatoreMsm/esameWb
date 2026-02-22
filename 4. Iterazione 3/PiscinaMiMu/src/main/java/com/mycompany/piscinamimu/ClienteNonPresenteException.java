/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class ClienteNonPresenteException extends Exception{
    public ClienteNonPresenteException(String id_cliente){
        super("Cliente: "+id_cliente+" inesistente");
    }
}
