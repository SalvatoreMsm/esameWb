/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class TipologiaVascaNonEsistenteException extends Exception{
    public TipologiaVascaNonEsistenteException(String tipo_vasca){
        super("Non esistono vasche di tipologia: "+tipo_vasca);
    }
}
