/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

import java.util.*;
/**
 *
 * @author salvatore
 */
public class Istruttore {
    
    private String nome;
    private String id_istruttore;
    private Map<String, Corso> corsi_insegnati;
    
    public Istruttore(String nome, String id_istruttore){
    
        this.nome = nome;
        this.id_istruttore = id_istruttore;
        this.corsi_insegnati = new HashMap<>();
    
    };
    
    public String getIdIstruttore(){
        return this.id_istruttore;
    }
    
    public boolean isDisponibile() throws IstruttoreNonDisponibile{
    
        if(corsi_insegnati.size() < 3) return true;
        
        else throw new IstruttoreNonDisponibile(this.getIdIstruttore());
        
    }
    
    public Map<String, Corso> getCorsi(){
        return this.corsi_insegnati;
    }
    
    public void AssegnaCorso(Corso c) throws IstruttoreNonDisponibile{
    
        if(!this.isDisponibile()){
            System.out.println("L'allenatore ha raggiunto il numero massimo di corsi insegnabili");
            return;
        } //sostituire in questo punto con throw ed eccezione appositamente creata
        
        this.corsi_insegnati.put(c.getIdCorso(), c);
    
    }
    
    @Override
    public String toString(){
    
            String s = "Nome: " + this.nome + " ID_istruttore: " + this.id_istruttore;
            return s;
    
    }
    
}

    