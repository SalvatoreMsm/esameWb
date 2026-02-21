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
public class Corsia {
    
    private String idCorsia;
    private Vasca vasca;
    private Map<String, Lezione> elencoLezioniSvolte; 

    // Costruttore
    
    public Corsia(String idCorsia) {
        this.idCorsia = idCorsia;
        elencoLezioniSvolte = new HashMap<>();
    }
    
    public String getIdCorsia(){
        return this.idCorsia;
    }

    public Vasca getVasca() {
        return vasca;
    }
    
    
    public void setVasca(Vasca v){
        this.vasca = v;
    }
    
    public void RemoveVasca(){
        this.vasca = null;
    }
    
    public Map<String, Lezione> getElencoLezioni(){
        return elencoLezioniSvolte;
    }
    
    public Lezione CercaLezione(String id_lezione) throws LezioneNonPresenteException{
        Lezione l = elencoLezioniSvolte.get(id_lezione);
        if(l == null) throw new LezioneNonPresenteException(id_lezione);
        return l;
    }
    
    public void addLezione(Lezione l){
        this.elencoLezioniSvolte.put(l.getIdLezione(), l);
    }
    
    public void RemoveLezione(String id_lezione) throws LezioneNonPresenteException{
        Lezione l = this.CercaLezione(id_lezione);
        l.RemoveCorsia();
        this.elencoLezioniSvolte.remove(id_lezione);
    }
    
    public void RemoveAllLezioni(){
        for(Lezione l : this.elencoLezioniSvolte.values()){
            l.RemoveCorsia();
            this.elencoLezioniSvolte.remove(l.getIdLezione());
        }
    }
    

    @Override
    public String toString(){
        if(vasca == null)
            return "Corsia " + idCorsia + " (vasca NON assegnata)";
        return "Corsia " + idCorsia + " - Vasca " + vasca.getIdVasca();
    }
    
    
}
