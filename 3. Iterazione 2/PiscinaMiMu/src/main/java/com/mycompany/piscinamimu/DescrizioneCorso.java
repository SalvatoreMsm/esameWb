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
    private int num_posti;
    private int durata;
    private int num_posti_occupati;

    // Costruttore
    
    public DescrizioneCorso(String nome, String tipologia_clienti, int num_posti, int durata, int num_posti_occupati) {
        this.nome = nome;
        this.tipologia_clienti = tipologia_clienti;
        this.num_posti = num_posti;
        this.durata = durata;
        this.num_posti_occupati = num_posti_occupati;
    }

    public String getNome() {
        return nome;
    }

    public String getTipologia_clienti() {
        return tipologia_clienti;
    }

    public int getNum_posti() {
        return num_posti;
    }

    public int getDurata() {
        return durata;
    }

    public int getNum_posti_occupati() {
        return num_posti_occupati;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setTipologiaClienti(String tipologia_clienti){
        this.tipologia_clienti = tipologia_clienti;
    }
    public void setNumPosti(int num_posti){
        this.num_posti = num_posti;
    }
    public void setDurata(int durata){
        this.durata = durata;
    }
    public void setNumPostiOccupati(int num_posti_occupati){
        this.num_posti_occupati = num_posti_occupati;
    }
    
    
    
    @Override
    public String toString() {
    return "Nome: " + nome +
           ", Tipologia clienti: " + tipologia_clienti +
           ", Posti totali: " + num_posti +
           ", Durata: " + durata + " lezioni" +
           ", Posti occupati: " + num_posti_occupati;
    }
}
