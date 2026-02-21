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
    
    public enum TipologiaCliente {
    Donne,
    Uomini,
    Bambini,
    Riabilitazione
}
    
    private String nome, cognome, idCliente;
    private Map<String, Corso> corsiIscritti;
    private TipologiaCliente tipologia;


    public Cliente(String nome, String cognome, String idCliente, TipologiaCliente tipologia) {
        this.nome = nome;
        this.cognome = cognome;
        this.idCliente = idCliente;
        this.corsiIscritti = new HashMap<>();
        this.tipologia = tipologia;
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

    public TipologiaCliente getTipologia() {
        return tipologia;
    }
    
    
    public void stampaDettagli() {

        System.out.println("=================================");
        System.out.println(this);

        System.out.println("Corsi iscritti:");
        if (corsiIscritti == null || corsiIscritti.isEmpty()) {
            System.out.println("  Nessun corso");
        } else {
            for (Corso c : corsiIscritti.values()) {
                System.out.println("  - " + c.getIdCorso() +
                                   " (" + c.getDescrizione().getNome() + ")");
            }
        }

        System.out.println("=================================\n");
    }

    @Override
    public String toString() {
        return "Cliente [ID= " + idCliente + ", Nome= " + nome +
               " " + cognome +
               ", Tipologia= " + tipologia + "]";
    }
    
    
}
