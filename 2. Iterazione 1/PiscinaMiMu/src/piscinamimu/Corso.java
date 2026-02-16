/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;
import java.util.*;

/**
 *
 * @author pmilo
 */

public class Corso {
    
    private String idCorso;
    private DescrizioneCorso descrizione;
    private Map<String, Lezione> elencoLezioni;
    
    // Costruttore

    public Corso(String idCorso, DescrizioneCorso descrizione) {
        this.idCorso = idCorso;
        this.descrizione = descrizione;
        this.elencoLezioni = new HashMap<>();
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
    
    public void aggiungiLezione(Lezione l) throws ProgrammazionePienaException, LezioneGiaPresenteException {
        if((this.elencoLezioni.size() >= this.descrizione.getDurata())){
            throw new ProgrammazionePienaException(descrizione.getNome());
        }
        
        if (elencoLezioni.containsKey(l.getIdLezione())) {
            throw new LezioneGiaPresenteException(l.getIdLezione());
        }
        
        this.elencoLezioni.put(l.getIdLezione(), l);
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

        return s;
    }


    

    
    
    
    
}
