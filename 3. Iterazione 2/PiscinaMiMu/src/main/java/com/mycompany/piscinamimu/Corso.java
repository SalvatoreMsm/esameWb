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

public class Corso {
    
    private String idCorso;
    private DescrizioneCorso descrizione;
    private Map<String, Lezione> elencoLezioni;
    private Map<String, Istruttore> elencoIstruttori;
    
    // Costruttore

    public Corso(String idCorso, DescrizioneCorso descrizione) {
        this.idCorso = idCorso;
        this.descrizione = descrizione;
        this.elencoLezioni = new HashMap<>();
        this.elencoIstruttori = new HashMap<>();
    }

    public String getIdCorso() {
        return idCorso;
    }

    public DescrizioneCorso getDescrizione() {
        return descrizione;
    }

    public Map<String, Lezione> getElencoLezioni() {
        return elencoLezioni;
    }
    
    public Map<String, Istruttore> getElencoIstruttori(){
        return this.elencoIstruttori;
    }
    
    public void aggiungiLezione(String idLezione, String oraInizio, String oraFine) throws ProgrammazionePienaException, LezioneGiaPresenteException {
        
        Lezione l;
        
        if((this.elencoLezioni.size() >= this.descrizione.getDurata())){
            throw new ProgrammazionePienaException(descrizione.getNome());
        }
        
        if (elencoLezioni.containsKey(idLezione)) {
            throw new LezioneGiaPresenteException(idLezione);
        }
        l = new Lezione(idLezione, oraInizio, oraFine);
        this.elencoLezioni.put(idLezione, l);
    }
    
    public void AggiungiIstruttore(Istruttore is){
        this.elencoIstruttori.put(is.getIdIstruttore(), is);
    }
    public boolean isSenzaLezioni() {
        return elencoLezioni.isEmpty();
    }

    public void eliminaLezione(String idLezione) throws LezioneNonPresenteException {
        Lezione rimossa;

        rimossa = elencoLezioni.remove(idLezione);

        if (rimossa == null) {
            throw new LezioneNonPresenteException(idLezione);
        }
    }
    
    public Lezione cercaLezione(String idLezione) throws LezioneNonPresenteException {
        Lezione l = elencoLezioni.get(idLezione);
        if (l == null) {
            throw new LezioneNonPresenteException(idLezione);
        }
        return l;
    }
    public boolean isLezionePresente(String idLezione) {
        return elencoLezioni.containsKey(idLezione);
    }
    
    
    
    
    
    @Override
    public String toString() {
        String s;
        s = "ID Corso: " + idCorso + "\n" +
                   descrizione + "\n";

        if (elencoLezioni != null && !elencoLezioni.isEmpty()) {
            s += "Lezioni:\n";
            for (Lezione l : elencoLezioni.values()) {
                s += l + "\n"; 
            }
        } else {
            s += "Nessuna lezione presente.\n";
        }

        if(elencoIstruttori != null && !elencoIstruttori.isEmpty()){

            s += "Istruttori\n";
            for(Istruttore is : elencoIstruttori.values()){
                s += is + "\n";
            }

        }
        else s += "Nessun istruttore presente";

        return s;
    }


    

    
    
    
    
}
