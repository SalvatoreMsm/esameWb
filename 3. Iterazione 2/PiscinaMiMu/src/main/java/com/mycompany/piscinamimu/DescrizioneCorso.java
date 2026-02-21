/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class DescrizioneCorso {
 
    private String nome;
    private String tipologia_clienti;
    private int numPosti;
    private int durata;
    private int numPostiOccupati;

    // Costruttore
    
    public DescrizioneCorso(String nome, String tipologia_clienti, int num_posti, int durata, int num_posti_occupati) {
        this.nome = nome;
        this.tipologia_clienti = tipologia_clienti;
        this.numPosti = num_posti;
        this.durata = durata;
        this.numPostiOccupati = num_posti_occupati;
    }

    public String getNome() {
        return nome;
    }

    public String getTipologia_clienti() {
        return tipologia_clienti;
    }

    public int getNumPosti() {
        return numPosti;
    }

    public int getDurata() {
        return durata;
    }

    public int getNumPostiOccupati() {
        return numPostiOccupati;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTipologiaClienti(String tipologia_clienti){
        this.tipologia_clienti = tipologia_clienti;
    }
    public void setNumPosti(int num_posti){
        this.numPosti = num_posti;
    }
    public void setDurata(int durata){
        this.durata = durata;
    }
    public void setNumPostiOccupati(int num_posti_occupati){
        this.numPostiOccupati = num_posti_occupati;
    }
    
    
    
    @Override
    public String toString() {
    return "Nome: " + nome +
           ", Tipologia clienti: " + tipologia_clienti +
           ", Posti totali: " + numPosti +
           ", Durata: " + durata + " lezioni" +
           ", Posti occupati: " + numPostiOccupati;
    }
}
