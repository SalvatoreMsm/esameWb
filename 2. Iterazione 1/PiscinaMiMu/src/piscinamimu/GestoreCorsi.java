/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;
import java.io.*;
import java.util.*;
/**
 *
 * @author pmilo
 */

public class GestoreCorsi {
    
    private Map<String, Corso> elencoCorsi;

    //Costruttore
    
    public GestoreCorsi() {
        this.elencoCorsi = new HashMap<>();
    }
    
    public synchronized void caricaCorsi(String nomeFile){
        
        try{
            
            BufferedReader bf = new BufferedReader(new FileReader(nomeFile));
            String idCorso, nomeF, nome, tipologiaClienti;
            int numPosti, durata, numPostiOccupati;

            DescrizioneCorso descrizione;
            Corso corso;
            

            while((idCorso = bf.readLine()) != null){
                System.out.println("idCorso letto: '" + idCorso + "'");

                nome=bf.readLine();
                tipologiaClienti=bf.readLine();
                numPosti = Integer.parseInt(bf.readLine());
                durata = Integer.parseInt(bf.readLine());
                numPostiOccupati = Integer.parseInt(bf.readLine());
                
                descrizione = new DescrizioneCorso(nome, tipologiaClienti, numPosti, durata, numPostiOccupati);
                corso = new Corso(idCorso, descrizione);
                elencoCorsi.put(idCorso, corso);
                
            }
            System.out.println("Numero corsi caricati: " + elencoCorsi.size());
            bf.close();
            
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        
    }
    
    public synchronized void caricaLezioni(String nomeFile) {

        try {
            BufferedReader bf = new BufferedReader(new FileReader(nomeFile));
            String idCorso, idLezione, oraInizio, oraFine;
            Lezione lezione;
            Corso corso;

            // Leggendo il file guardo l'idCorso e capisco a quale corso appartiene
            while ((idCorso = bf.readLine()) != null) {
                idLezione = bf.readLine();
                oraInizio = bf.readLine();
                oraFine = bf.readLine();

                System.out.println("Lezione letta: id=" + idLezione + ", corso=" + idCorso);
                
                lezione = new Lezione(idLezione, oraInizio, oraFine);

                // Recupero il corso dalla Map dei corsi
                corso = elencoCorsi.get(idCorso);
                if (corso != null) {
                    
                    corso.aggiungiLezione(lezione);
                } else {
                    System.out.println("Corso con id " + idCorso + " non trovato!");
                }
            }

            bf.close();

        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void aggiungiLezione(String idNuovoCorso)throws ProgrammazionePienaException{ 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String idCorso = idNuovoCorso;
        String idLezione, oraInizio, oraFine;
        Corso corso;
        Lezione lez;
        try{
            
            if (idCorso == null || idCorso.isEmpty()) {
                System.out.println("Inserisci ID corso a cui si vuole aggiungere la lezione: ");
                idCorso = bf.readLine();
            }
            
            corso=elencoCorsi.get(idCorso);
            if (corso == null) {
                System.out.println("Corso con id " + idCorso + " non trovato!");
                return;
            }

            System.out.println("ID lezione: ");
            idLezione = bf.readLine();
            System.out.println("Ora inizio lezione: ");
            oraInizio = bf.readLine();
            System.out.println("Ora fine lezione: ");
            oraFine = bf.readLine();
            
            lez = new Lezione(idLezione, oraInizio, oraFine);
            corso.aggiungiLezione(lez);

            System.out.println("Lezione aggiunta al corso " + idCorso + " correttamente!");

            // DA AGGIUNGERE CODICE PER ECCEZIONE
            
            
        }catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }


    
    public synchronized String aggiungiCorso(){ 
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String idCorso, nome, tipologiaClienti;
        int numPosti, durata;
        Corso corso;
        DescrizioneCorso descrizione;
        
        try{
            
            System.out.println("ID Corso: ");
            idCorso = bf.readLine();
            if (elencoCorsi.containsKey(idCorso)) {
                System.out.println("Corso con ID " + idCorso + " già presente!");
                return null;
            }            
            //info per la descrizione
            System.out.println("Nome corso: ");
            nome = bf.readLine();
            System.out.println("Tipologia clienti: ");
            tipologiaClienti = bf.readLine();
            System.out.println("Numero posti: ");
            numPosti = Integer.parseInt(bf.readLine());
            System.out.println("Durata corso: ");
            durata = Integer.parseInt(bf.readLine());  
            
            descrizione = new DescrizioneCorso(nome, tipologiaClienti, numPosti, durata, numPosti);
            corso = new Corso(idCorso, descrizione);
            
            elencoCorsi.put(idCorso, corso);
            
            System.out.println("Corso " + idCorso + " aggiunto correttamente!");
            return idCorso;
        }catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
            return null;
        }
    }
    
    
    public synchronized void eliminaLezione(String idCorso){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Corso corso;
        String idLezione;
        try{
            if(!elencoCorsi.containsKey(idCorso)){
                System.out.println("Corso non trovato");
                return;
            }
            System.out.println("ID Lezione che si desidera eliminare: ");
            idLezione = bf.readLine();   
            corso=elencoCorsi.get(idCorso);

            if(!corso.getElencoLezioni().containsKey(idLezione)){
                System.out.println("Lezione non trovata ");
                return;
            }
            
            corso.getElencoLezioni().remove(idLezione);
            
            System.out.println("ID Lezione che si desidera eliminare: ");
                     
        }catch(IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void eliminaCorso(String idCorso){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        try{
            if(!elencoCorsi.containsKey(idCorso)){
                System.out.println("Corso non trovato");
                return;
            }

            // Rimuove il corso (e quindi tutte le sue lezioni)
            // ATTENZIONE questo è vero solo se collegata al corso, quando la si collegherà ad altro si dovrà modificare e rimuovere le lezioni
            elencoCorsi.remove(idCorso);

            System.out.println("Corso " + idCorso + " eliminato correttamente!");

        }catch(Exception e) {
            System.out.println("ERRORE IN FASE DI Eliminazione");
            System.exit(-1);
        }
    }

    
    
    public synchronized void stampaTutto(){
        for(Corso c: elencoCorsi.values()){
            System.out.println(c);
        }
    }
    
}
