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
    
    public void aggiungiLezione(Lezione l) {
        this.elencoLezioni.put(l.getIdLezione(), l);
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
