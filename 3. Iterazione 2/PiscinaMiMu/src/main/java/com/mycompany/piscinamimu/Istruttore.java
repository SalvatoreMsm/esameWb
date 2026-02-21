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
    private String cognome;
    private String id_istruttore;
    private Map<String, Corso> corsi_insegnati;
    
    public Istruttore(String nome, String cognome, String id_istruttore){
    
        this.nome = nome;
        this.cognome = cognome;
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
    
        if(!this.isDisponibile()) throw new IstruttoreNonDisponibile(this.getIdIstruttore());
        
        this.corsi_insegnati.put(c.getIdCorso(), c);
    
    }
    
    public Corso getCorsoInsegnato(String id_corso) throws CorsoNonPresenteException{
    	Corso c = this.corsi_insegnati.get(id_corso);
        if(c == null) throw new CorsoNonPresenteException(id_corso);
        return c;
    }
    
    
    public void stampaDettagli() {
        boolean disponibile;

        System.out.println("=================================");
        System.out.println(this);

        try {
            disponibile = this.isDisponibile();
        } catch (IstruttoreNonDisponibile e) {
            disponibile = false;
        }
        System.out.println("Stato: " + (disponibile ? "Disponibile" : "Occupato"));

        System.out.println("Corsi insegnati:");

        if (corsi_insegnati == null || corsi_insegnati.isEmpty()) {
            System.out.println("Nessun corso assegnato");
        } else {
            for (Corso c : corsi_insegnati.values()) {
                System.out.println("  - " + c.getIdCorso() +
                                   " (" + c.getDescrizione().getNome() + ")");
            }
        }

        System.out.println("=================================\n");
    }
    
    @Override
    public String toString() {
        return "Istruttore [Nome=" + nome + " " + cognome +
               ", ID=" + id_istruttore + "]";
    }
    
}