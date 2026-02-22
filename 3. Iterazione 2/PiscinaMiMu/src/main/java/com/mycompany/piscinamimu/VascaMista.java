/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class VascaMista extends Vasca{
    public VascaMista(String id_corso){
        super(id_corso);
    }
        
    @Override
    public TipoVasca getTipo() {
        return TipoVasca.MISTA;
    }
}
