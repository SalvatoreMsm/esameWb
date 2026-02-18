/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;

/**
 *
 * @author pmilo
 */
public class Lezione {

    private String idLezione;
    private String oraInizio;
    private String oraFine;

    // Costruttore

    
    public Lezione(String idLezione, String ora_inizio, String ora_fine) {
        this.idLezione = idLezione;
        this.oraInizio = ora_inizio;
        this.oraFine = ora_fine;
    }

    public String getIdLezione() {
        return idLezione;
    }
    
    public void setOraInizio(String ora_inizio){
        this.oraInizio = ora_inizio;
    }
    
    public void setOraFine(String ora_fine){
        this.oraFine = ora_fine;
    }
    
    public String getOraInizio(){
        return this.oraInizio;
    }
    
    public String getOraFine(){
        return this.oraFine;
    }
    
    public boolean checkValidTime(String ora_inizio, String ora_fine) {

        String[] start = ora_inizio.split(":");
        String[] end = ora_fine.split(":");

        int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        return endMin > startMin;
}

    
    @Override
    public String toString() {
        return idLezione + " " + oraInizio + "-" + oraFine;
    }

    
    
}
