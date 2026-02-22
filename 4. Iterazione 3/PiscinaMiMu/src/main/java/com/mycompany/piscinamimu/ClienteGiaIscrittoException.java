/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class ClienteGiaIscrittoException extends Exception{
    public ClienteGiaIscrittoException(String id_cliente, String id_corso){
        super("Il cliente: "+id_cliente+" è già iscritto al corso: "+id_corso);
    }
}
