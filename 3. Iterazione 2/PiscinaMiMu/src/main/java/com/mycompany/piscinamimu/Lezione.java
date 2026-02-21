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
    private Corsia cr;

    // Costruttore

    
    public Lezione(String idLezione, String ora_inizio, String ora_fine) {
        this.idLezione = idLezione;
        this.oraInizio = ora_inizio;
        this.oraFine = ora_fine;
        this.cr = null;
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
    
    public void setCorsia(Corsia cr){
        this.cr = cr;
    }
    
    public void addCorsia(Corsia cr) throws CorsiaGiaPresenteException{
        if(cr == null) throw new CorsiaGiaPresenteException(cr.getIdCorsia());
        if(cr == this.cr) throw new CorsiaGiaPresenteException(cr.getIdCorsia());
        this.setCorsia(cr);
    }
    
    
    public String getOraInizio(){
        return this.oraInizio;
    }
    
    public String getOraFine(){
        return this.oraFine;
    }
    
    public Corsia getCorsia(){
        return this.cr;
    }
    
    public void RemoveCorsia(){
        this.cr = null;
        System.out.println("Rimossa corsia: "+cr);
    }
    
    
    public static boolean checkValidTimeStatic(String ora_inizio, String ora_fine) {

        String[] start = ora_inizio.split(":");
        String[] end = ora_fine.split(":");

        int startMin = Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]);
        int endMin = Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1]);

        return endMin > startMin;
    }

    
    public String stampaBreve() {

        String s = idLezione + " (" + oraInizio + "-" + oraFine + ")";

        if (cr == null) {
            s += " - [Corsia NON assegnata]";
            return s;
        }

        s += " - Corsia " + cr.getIdCorsia();

        if (cr.getVasca() == null) {
            s += " - [Vasca NON assegnata]";
        } else {
            s += " - Vasca " + cr.getVasca().getIdVasca();
        }

        return s;
    }

    @Override
    public String toString() {
        return "Lezione [ID=" + idLezione +
               ", Orario=" + oraInizio + "-" + oraFine + "]";
    }
    
    
}
