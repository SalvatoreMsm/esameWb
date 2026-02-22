/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author salvatore
 */
public class VascaBambini extends Vasca{
    public VascaBambini(String id_vasca) {
        super(id_vasca);
    }
     
    @Override
    public TipoVasca getTipo() {
        return TipoVasca.BAMBINI;
    }
}
