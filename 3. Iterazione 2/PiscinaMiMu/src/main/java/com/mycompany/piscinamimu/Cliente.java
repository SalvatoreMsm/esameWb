/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.util.*;

/**
 *
 * @author pmilo
 */
public class Cliente {
    private String nome, cognome, idCliente;
    private Map<String, Corso> corsiIscritti;


    public Cliente(String nome, String cognome, String idCliente) {
        this.nome = nome;
        this.cognome = cognome;
        this.idCliente = idCliente;
        this.corsiIscritti = new HashMap<>();
    }
    
    public int numCorsi(){
        return corsiIscritti.size();
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public Map<String, Corso> getCorsiIscritti() {
        return corsiIscritti;
    }
    
    
    
    
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", idCliente='" + idCliente + '\'' +
                ", numeroCorsi=" + numCorsi() +
                '}';
    }
    
    
    
}
