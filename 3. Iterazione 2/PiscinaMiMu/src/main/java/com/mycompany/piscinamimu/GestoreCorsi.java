/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
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
    
        
    public Corso cercaCorso(String idCorso) throws CorsoNonPresenteException{
        Corso corso;
        corso=elencoCorsi.get(idCorso);
        
        if (corso == null) {
            throw new CorsoNonPresenteException(idCorso);
        }

        return corso;
    }

    public Lezione cercaLezione(String idCorso, String idLezione) throws LezioneNonPresenteException, CorsoNonPresenteException{
        Lezione lezione;
        Corso corso;
        corso = cercaCorso(idCorso);

        return lezione=corso.cercaLezione(idLezione);

    }

    
    
    
    public void aggiungiCorso(String idCorso, DescrizioneCorso descrizione) throws CorsoGiaPresenteException {
        Corso corso;
        
        if (elencoCorsi.containsKey(idCorso)) {
            throw new CorsoGiaPresenteException(idCorso);
        }

        corso = new Corso(idCorso, descrizione);
        elencoCorsi.put(idCorso, corso);
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
                
                try{
                    aggiungiCorso(idCorso, descrizione);
                } catch (CorsoGiaPresenteException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Numero corsi caricati: " + elencoCorsi.size());
            bf.close();
            
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        
    }
    
    public synchronized void caricaLezioni(String nomeFile){

        try {
            BufferedReader bf = new BufferedReader(new FileReader(nomeFile));
            String idCorso, idLezione, oraInizio, oraFine;
            Lezione lezione;
            Corso corso;

            // Leggendo il file guardo l'idCorso e capisco a quale corso appartiene
            while ((idCorso = bf.readLine()) != null) {
                try{
                    corso = cercaCorso(idCorso);
                    idLezione = bf.readLine();
                    oraInizio = bf.readLine();
                    oraFine = bf.readLine();

                    System.out.println("Lezione letta: id=" + idLezione + ", corso=" + idCorso);
                    
                    corso.aggiungiLezione(idLezione, oraInizio, oraFine);
                    
                }catch (CorsoNonPresenteException e) {
                System.out.println("Corso non trovato: " + idCorso + ", salto questa lezione.");
                } catch (ProgrammazionePienaException e) {
                    System.out.println("Programmazione piena per il corso " + idCorso + ", salto questa lezione.");
                } catch (LezioneGiaPresenteException e) {
                    System.out.println("Lezione già presente: " + e.getMessage() + ", salto questa lezione.");
                }
                
            }
        
        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }

    
    
    public synchronized void aggiungiLezione(String idNuovoCorso) throws LezioniConOrariNonValidiException{ 
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
            
            corso=cercaCorso(idCorso);

            System.out.println("ID lezione: ");
            idLezione = bf.readLine();
            
            if (corso.isLezionePresente(idLezione)) {
               throw new LezioneGiaPresenteException(idLezione);
            }
            
            System.out.println("Ora inizio lezione: ");
            oraInizio = bf.readLine();
            System.out.println("Ora fine lezione: ");
            oraFine = bf.readLine();
            
            try {
                if(!Lezione.checkValidTimeStatic(oraInizio, oraFine)) 
                    throw new LezioniConOrariNonValidiException(idLezione);
                corso.aggiungiLezione(idLezione, oraInizio, oraFine);
                System.out.println("Lezione aggiunta al corso " + idCorso + " correttamente!");
            } catch (ProgrammazionePienaException e) {
                System.out.println("Errore: corso pieno (" + idCorso + "). Lezione non aggiunta.");
            }
        } catch (LezioneGiaPresenteException e) { 
                System.out.println("Errore: " + e.getMessage() + ". Lezione non aggiunta.");
        }catch(CorsoNonPresenteException e){
            System.out.println("Corso " + idCorso + " non presente");
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
            if (numPosti <= 0) {
                System.out.println("Errore: il numero di posti deve essere maggiore di 0.");
                return null;
            }
            System.out.println("Durata corso: ");
            durata = Integer.parseInt(bf.readLine()); 
            if (durata <= 0) {
                System.out.println("Errore: la durata deve essere maggiore di 0.");
                return null;
            }
            
            descrizione = new DescrizioneCorso(nome, tipologiaClienti, numPosti, durata, numPosti);
            try {
                aggiungiCorso(idCorso, descrizione);
                System.out.println("Corso " + idCorso + " aggiunto correttamente!");
                return idCorso;

            } catch (CorsoGiaPresenteException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
            return null;
        }
    }
    
    
    public synchronized void eliminaLezione(String idCorso)throws CorsoNonPresenteException, IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Corso corso;
        String idLezione;
        try{
            corso=cercaCorso(idCorso);
            
            if (corso.isSenzaLezioni()) {
                throw new CorsoSenzaLezioniException(idCorso);
            }
            System.out.println("ID Lezione che si desidera eliminare: ");
            idLezione = bf.readLine();   
            
            corso.eliminaLezione(idLezione);
            System.out.println("Lezione eliminata con successo");
        }catch (CorsoSenzaLezioniException e) {
        System.out.println("Errore: il corso non contiene lezioni.");
        }catch (CorsoNonPresenteException | LezioneNonPresenteException e){
            System.out.println(e.getMessage());                     
        }catch(IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void eliminaCorso(String idCorso) throws CorsoNonPresenteException{
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Corso corso;
            corso = cercaCorso(idCorso);

            // Rimuove il corso (e quindi tutte le sue lezioni)
            // ATTENZIONE questo è vero solo se collegata al corso, quando la si collegherà ad altro si dovrà modificare e rimuovere le lezioni
            elencoCorsi.remove(idCorso);
            System.out.println("Corso " + idCorso + " eliminato correttamente!");
        
    }

    public synchronized void ModificaNome(String id_corso) throws CorsoNonPresenteException, IOException{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in) );
    
            Corso corso;
            DescrizioneCorso cd;
            corso = cercaCorso(id_corso);
            
            System.out.println("Inserisci nuovo nome per il corso --->");

            cd = corso.getDescrizione();
            cd.setNome(bf.readLine());
    }
    
    public synchronized void ModificaTipologiaClienti(String id_corso) throws CorsoNonPresenteException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Corso corso;
        DescrizioneCorso cd;
        corso = cercaCorso(id_corso);

        System.out.println("Inserisci la nuova tipologia clienti --->");

        cd = corso.getDescrizione();
        cd.setTipologiaClienti(bf.readLine());
    }

    
    public synchronized void ModificaDurata(String id_corso) 
            throws CorsoNonPresenteException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Corso corso;
        DescrizioneCorso cd;
        corso = cercaCorso(id_corso);

        System.out.println("Inserisci la nuova durata del corso --->");
        int durata = Integer.parseInt(bf.readLine());

        cd = corso.getDescrizione();
        cd.setDurata(durata);
    }

    
    public synchronized void ModificaNumeroPosti(String id_corso) throws CorsoNonPresenteException, IOException{
    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num_posti;
        Corso corso;
        DescrizioneCorso cd;
        
        corso=cercaCorso(id_corso);
        System.out.println("Inserisci il nuovo numero di posti disponibili nel corso --->");
        num_posti = Integer.parseInt(bf.readLine());

        cd = corso.getDescrizione();
        cd.setNumPosti(num_posti);
    }
    
    public synchronized void ModificaPostiOccupati(String id_corso) throws CorsoNonPresenteException, IOException{
    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num_posti_occupati;
        Corso corso;
        DescrizioneCorso cd;
        
        corso=cercaCorso(id_corso);
        System.out.println("Inserisci il nuovo numero di posti disponibili nel corso --->");
        num_posti_occupati = Integer.parseInt(bf.readLine());

        cd = corso.getDescrizione();
        cd.setNumPostiOccupati(num_posti_occupati);    
    }
    
    public synchronized void ModificaOraInizioLezione(String id_corso, String id_lezione) throws CorsoNonPresenteException, LezioneNonPresenteException,
            LezioniConOrariNonValidiException{
    
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Lezione l;
        String ora_inizio;
        try{
            l = cercaLezione(id_corso, id_lezione);

            System.out.println("Inserire nuovo orario inizio lezione --->");
            ora_inizio = bf.readLine();
            l.setOraInizio(ora_inizio);

            if(!l.checkValidTimeStatic(l.getOraInizio(), l.getOraFine())) 
                throw new LezioniConOrariNonValidiException(id_lezione);

        }catch(IOException e){System.out.println("Errore nell'IO");}
    
    }
    
    public synchronized void ModificaOraFineLezione(String id_corso, String id_lezione) throws CorsoNonPresenteException, LezioneNonPresenteException,
            LezioniConOrariNonValidiException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Lezione l;
        String ora_fine;
        try{
            l=cercaLezione(id_corso, id_lezione);
            if(l == null) throw new LezioneNonPresenteException(id_lezione);

            System.out.println("Inserire nuovo orario fine lezione --->");
            ora_fine = bf.readLine();
            l.setOraFine(ora_fine);

            if(!l.checkValidTimeStatic(l.getOraInizio(), l.getOraFine())) 
                throw new LezioniConOrariNonValidiException(id_lezione);
        
        }catch(IOException e){System.out.println("Errore nell'IO");}
    
    }
    
    public synchronized void AggiungiIstruttore(Istruttore is, String id_corso) throws IstruttoreGiaAssegnatoAlCorsoException{
        try{
            Corso c = this.cercaCorso(id_corso);
            if(c.getElencoIstruttori().containsKey(is.getIdIstruttore())) throw new IstruttoreGiaAssegnatoAlCorsoException(is.getIdIstruttore());
            c.AggiungiIstruttore(is);
            is.AssegnaCorso(c);
            }
            catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
            catch(IstruttoreNonDisponibile e){System.out.println(e.getMessage());}
    
    }
    
    
    
    public synchronized void stampaTutto(){
        for(Corso c: elencoCorsi.values()){
            System.out.println(c);
        }
    }
    
}
