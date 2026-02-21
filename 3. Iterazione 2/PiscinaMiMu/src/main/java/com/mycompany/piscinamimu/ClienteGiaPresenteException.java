/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class ClienteGiaPresenteException extends Exception{
    public ClienteGiaPresenteException(String idCliente) {
        super("Cliente già presente con ID: " + idCliente);
    }
}
