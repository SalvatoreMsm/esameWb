/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.io.*;
/**
 *
 * @author pmilo
 */
public class InterazioneUtente{
    
    
    private static final String FILE_CORSI = "elencoCorsi.txt";
    private static final String FILE_LEZIONI = "elencoLezioni.txt";
    private static final String FILE_ISTRUTTORI = "elencoIstruttori.txt";
    private static final String FILE_CLIENTI = "elencoClienti.txt";
    private static final String FILE_VASCHE = "elencoVasche.txt";
    private static final String FILE_CORSIE = "elencoCorsie.txt";
    
    private GestoreCorsi gc;
    private GestoreIstruttori gi;
    private GestoreClienti gcl;
    private GestoreVasche gv;
      
    public InterazioneUtente(GestoreCorsi gc, GestoreIstruttori gi, GestoreClienti gcl,  GestoreVasche gv){
        this.gc = gc;
        this.gi = gi;
        this.gcl = gcl;
        this.gv = gv;
    }
    
    public int menu(BufferedReader bf){
        try{
            //System.out.println("1. Carica corsi da file.");
            System.out.println("1. Inserisci nuovo corso.");
            System.out.println("2. Inserisci nuova lezione.");
            System.out.println("3. Modifica un corso.");
            System.out.println("4. Assumi Istruttore");
            System.out.println("5. Assegna Istruttore Ad Un Corso");
            System.out.println("6. Iscrizione Cliente alla Piscina");
            System.out.println("7. Inserisci Nuova Vasca");
            System.out.println("8. Associa una Lezione ad una Corsia");
            System.out.println("9. Rimuovi Corsia da una Vasca");
            System.out.println("10. Aggiungi Corsia ad una Vasca");
            System.out.println("11. Stampa Corsi.");
            System.out.println("12. Stampa Istruttori Disponibili");
            System.out.println("13. Stampa Clienti");
            System.out.println("14. Stampa Vasche");
            System.out.println("15. Stampa Lezioni");

            System.out.println("20. EXIT.");
            System.out.println("\n30. CARICA TUTTI DA FILE.");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
    
    public int menuModificaCorso(BufferedReader bf){
        try{
            System.out.println("1. Elimina Lezione.");
            System.out.println("2. Elimina Corso.");
            System.out.println("3. Aggiungi Lezione.");
            System.out.println("4. Modifica Attributi Corso");            
            System.out.println("10 Stampa Corsi.");           

            System.out.println("20. EXIT.");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
        
    public int menuModificaAttributi(BufferedReader bf){
        try{
            System.out.println("1. Modifica nome.");
            System.out.println("2. Modifica Tipologia Clienti.");
            System.out.println("3. Modifica Numero Posti Totali.");
            System.out.println("4. Modifica Durata.");
            System.out.println("5. Modifica Numero Posti Occupati");
            System.out.println("6. Modifica Attributi Delle Lezioni Del Corso");

            System.out.println("20. EXIT.");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }  
        
    public int menuModificaAttributiLezione(BufferedReader bf){
        try{
            System.out.println("1. Modifica Ora Inizio.");
            System.out.println("2. Modifica Ora Fine.");
            
            System.out.println("20. EXIT.");
            System.out.println("INSERIRE LA SCELTA --->");
            return Integer.parseInt(bf.readLine());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        return 0;
    }
    
    // FUNZIONI CHE VERRANNO UTILIZZATE NEI CASE
    
    private void gestisciNuovoCorso(BufferedReader bf) throws IOException {
        try {
            String idCorso, risposta; 
            idCorso = gc.aggiungiCorso();
            do {
                System.out.println("Vuoi aggiungere una lezione? (Si/No)");
                risposta = bf.readLine();
                if (risposta.equalsIgnoreCase("Si")) 
                    gc.aggiungiLezione(idCorso);
                
            } while (risposta.equalsIgnoreCase("Si"));
        } catch (Exception e) {
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }
    
    private void gestisciModificaCorso(BufferedReader bf) throws IOException {
        String idCorso;
        
        System.out.print("Inserire ID Corso da modificare: ");
        idCorso = bf.readLine();
        try {
            gc.cercaCorso(idCorso);

            int sceltaModifica;
            do {
                sceltaModifica = menuModificaCorso(bf);
                switch (sceltaModifica) {
                    case 1: 
                            gestisciEliminaLezione(bf, idCorso);
                            break;
                    case 2: // Da pulire
                            try {
                                 gc.eliminaCorso(idCorso);
                                 System.out.println("Corso eliminato con successo.");
                             } catch (CorsoNonPresenteException e) {
                                 System.out.println("Errore: " + e.getMessage());
                             } catch (Exception e) {
                                 System.out.println("OPS: qualcosa è andato storto!");
                             }
                            break;
                    case 3: 
                            gestisciAggiungiLezione(bf, idCorso);
                            break;
                    case 4:     
                            gestisciModificaAttributiCorso(bf, idCorso);
                            break;
                    case 10:
                            gc.stampaTutto();
                            break;
                }
            } while (sceltaModifica != 20);

        } catch (CorsoNonPresenteException e) {
            System.out.println("Errore: " + e.getMessage() + ". Torna al menu principale.");
        }
    }
    
    
    private void gestisciEliminaLezione(BufferedReader bf, String idCorso) throws IOException {
        String risposta;
        do {
            try {
                gc.eliminaLezione(idCorso);
            }catch(Exception e) {
                System.out.println("OPS: qualcosa è andato storto!");
            }
            System.out.println("Vuoi eliminare un'altra lezione? (Si/No)");
            risposta = bf.readLine();
        } while (risposta.equalsIgnoreCase("Si"));
    }
    
    
    private void gestisciAggiungiLezione(BufferedReader bf, String idCorso) throws IOException {
        String risposta;
        do {
            try {
                gc.aggiungiLezione(idCorso);
            } catch (Exception e) {
                System.out.println("OPS: qualcosa è andato storto!");
            }
            System.out.println("Vuoi aggiungere un'altra lezione? (Si/No)");
            risposta = bf.readLine();
        } while (risposta.equalsIgnoreCase("Si"));
    }
    
    private void gestisciModificaAttributiCorso(BufferedReader bf, String idCorso) throws IOException {
        int scelta;
        do {
            scelta = menuModificaAttributi(bf);
            switch (scelta) {
                case 1:
                        try { gc.ModificaNome(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 2:
                        try { gc.ModificaTipologiaClienti(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 3:
                        try { gc.ModificaNumeroPosti(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                case 4:
                        try { gc.ModificaDurata(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                
                case 5:
                        try { gc.ModificaPostiOccupati(idCorso); }
                        catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                        catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                        break;
                
                case 6:
                        gestisciModificaLezione(bf, idCorso);
                        break;

            }
        } while (scelta != 20);
    }
    
    private void gestisciModificaLezione(BufferedReader bf, String idCorso) throws IOException {
        String idLezione; 
        
        System.out.print("Inserire ID Lezione: ");
        idLezione = bf.readLine();

        int scelta;
        do {
            scelta = menuModificaAttributiLezione(bf);
            switch (scelta) {
                case 1:
                    try { gc.ModificaOraInizioLezione(idCorso, idLezione); }
                    catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                    scelta = 20;}
                    catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                    catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                    break;
                
                case 2:
                    try { gc.ModificaOraFineLezione(idCorso, idLezione); }
                    catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                    scelta = 20;}
                    catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                    catch(Exception e){ System.out.println("OPS: qualcosa è andato storto!"); }
                    break;
                
            }
        } while (scelta != 20);
    }
    private void gestisciAssumiIstruttore(BufferedReader bf){
        try {
            System.out.println("Inserisci ID istruttore --->");
            String id_istruttore = bf.readLine();
            System.out.println("Inserisci Nome istruttore --->");
            String nome = bf.readLine();
            gi.AssumiIstruttore(nome, id_istruttore);
        } catch (IstruttoreGiaAssuntoException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }
    private void gestisciAssegnaIstruttore(BufferedReader bf){
        try{
            System.out.println("Scegli Corso A Cui Assegnare Istruttore");
            String id_corso = bf.readLine();

            gi.VisualizzaIstruttoriDisponibili();

            System.out.println("Scegli ID Istruttore Da Assegnare al corso: " + id_corso + " --->");
            String id_istruttore = bf.readLine();

            Istruttore is = gi.getIstruttore(id_istruttore);

            gc.AggiungiIstruttore(is, id_corso);

        }catch(IstruttoreGiaAssegnatoAlCorsoException e){
            System.out.println(e.getMessage());
        }catch(IstruttoreNonDisponibile e){
            System.out.println(e.getMessage());
        }    
        catch(Exception e){
            System.out.println("OPS: qualcosa è andato storto!");
        }
    }

    private void gestisciIscrizionePiscina(BufferedReader bf){
        try {
            String idCliente, nome, cognome;

            System.out.print("Inserisci ID Cliente: ");
            idCliente = bf.readLine();
            System.out.print("Inserisci Nome Cliente: ");
            nome = bf.readLine();
            System.out.print("Inserisci Cognome Cliente: ");
            cognome = bf.readLine();

            Cliente nuovoCliente = new Cliente(nome, cognome, idCliente);
            try {
                gcl.aggiungiCliente(nuovoCliente);
                System.out.println("Cliente aggiunto correttamente!");
            } catch (ClienteGiaPresenteException e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("OPS: errore in lettura input!");
        }
    }
    /* Se si vogliono inserire i nomi dei file manualmente
    
    private void caricaDaFile(){
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            try{
                String nomeFileCorsi, nomeFileLezioni, nomeFileIstruttori, nomeFileClienti;

                System.out.print("Nome file corsi: ");
                nomeFileCorsi = bf.readLine();
                gc.caricaCorsi(nomeFileCorsi);

                System.out.print("Nome file lezioni: ");
                nomeFileLezioni = bf.readLine();
                gc.caricaLezioni(nomeFileLezioni);

                System.out.print("Nome file Istruttori: ");
                nomeFileIstruttori = bf.readLine();
                gi.caricaIstruttori(nomeFileIstruttori);
                
                System.out.print("Nome file Clienti: ");
                nomeFileClienti = bf.readLine();
                gcl.caricaClienti(nomeFileClienti);
                
            }catch (IOException e) {
                System.out.println("ERRORE IN FASE DI I/O nel caricamento da file");
                System.exit(-1);
            }

    }
    */
    
    private void caricaDaFile() {
        try {
            gc.caricaCorsi(FILE_CORSI);
            gc.caricaLezioni(FILE_LEZIONI);
            gi.caricaIstruttori(FILE_ISTRUTTORI);
            gcl.caricaClienti(FILE_CLIENTI);
            gv.CaricaVasche(FILE_VASCHE);
            gv.CaricaCorsie(FILE_CORSIE);

            System.out.println("Caricamento completato!");

        }
        catch(VascaNonPresenteException e){System.out.println(e);}
        catch(CorsiaGiaPresenteException e){System.out.println(e);}
        catch (Exception e) {
            System.out.println("ERRORE IN FASE DEL CARICAMENTO DA FILE");
            System.exit(-1);
        }
    }
    
    
    
    
    
    public void avvia() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            
            int scelta;
            do {
                scelta = menu(bf); 
                switch (scelta) {
                    case 1: // NUOVO CORSO
                            gestisciNuovoCorso(bf);  
                            break;
                    case 2: // NUOVA LEZIONE
                            String idCorso;
                            System.out.print("Inserire ID del corso a cui aggiungere una lezione: ");
                            idCorso = bf.readLine(); 
                            gestisciAggiungiLezione(bf, idCorso);        
                            break;
                    case 3: // MODIFICA CORSO
                            gestisciModificaCorso(bf);       
                            break;
                    case 4: // ASSUMI ISTRUTTORE                     
                            gestisciAssumiIstruttore(bf);
                            break;
                    case 5: // ASSEGNA ISTRUTTORE
                            gestisciAssegnaIstruttore(bf);
                            break;
                        
                    case 6: gestisciIscrizionePiscina(bf);
                            break;
                    case 7:
                            try{
                            System.out.println("Inserisci id nuova vasca --->");
                            String id_vasca = bf.readLine();
                            System.out.println("Inserisci tipologia nuova vasca --->");
                            String tipologia_vasca = bf.readLine();
                            
                            gv.aggiungiVasca(tipologia_vasca, id_vasca);
                            }catch(VascaGiaPresenteException e){System.out.println(e);}
                            break;
                    case 8:
                            try{
                            System.out.println("Inserisci id corso --->");
                            String id_corso = bf.readLine();
                            System.out.println("Inserisci id lezione --->");
                            String id_lezione = bf.readLine();
                            System.out.println("Inserisci id corsia --->");
                            String id_corsia = bf.readLine();
                            
                            Corsia cr = gv.CercaCorsia(id_corsia);
                            gc.AggiungiCorsia(id_corso, id_lezione, cr);
                            
                            }
                            catch(CorsoNonPresenteException e){System.out.println(e);}
                            catch(LezioneNonPresenteException e){System.out.println(e);}
                            catch(CorsiaGiaPresenteException e){System.out.println(e);}
                            catch(CorsiaNonEsistenteException e){System.out.println(e);}
                            break;
                    case 9:
                            try{
                                System.out.println("Inserisci Id Vasca da cui rimuovere la corsia --->");
                                String id_vasca = bf.readLine();
                                
                                System.out.println("Inserisci Id Corsia da rimuovere --->");
                                String id_corsia = bf.readLine();
                                

                                gv.RemoveCorsiaToVasca(id_vasca, id_corsia);
                                
                                
                            }
                            catch(VascaNonPresenteException e){System.out.println(e);}
                            catch(CorsiaNonPresenteNellaVascaException e){System.out.println(e);}
                            break;
                    case 10:
                            try{
                            System.out.println("Inserire Id della vasca a cui aggiungere corsia --->");
                            String id_vasca = bf.readLine();
                            System.out.println("Inserire nuova corsia --->");
                            String id_corsia = bf.readLine();
                            
                            Corsia cr = new Corsia(Integer.parseInt(id_corsia));
                            gv.AssegnaCorsiaToVasca(id_vasca, cr);
                            }
                            catch(CorsiaGiaEsistenteException e){System.out.println(e);}
                            catch(VascaNonPresenteException e){System.out.println(e);}
                            catch(CorsiaGiaPresenteException e){System.out.println(e);}
                            break;
                            
                    case 11: // STAMPA TUTTO DEI CORSI (UTILITY)
                            gc.stampaTutto();     
                            break;
                    case 12: // STAMPA TUTTO DEGLI ISTRUTTORI (UTILITY)
                            gi.StampaTutto();               
                            break;
                    case 13: // STAMPA TUTTO DEI CLIENTI (UTILITY)
                            gcl.stampaTuttoClienti();               
                            break;
                    case 14: // STAMPA TUTTO DELLE VASCHE (UTILITY)
                            gv.StampaTutto();               
                            break;
                    case 15: //STAMPA TUTTO DELLE LEZIONI (UTILITY)
                            gc.stampaLezioni();
                            break;
                    case 30: // CARICA DA FILE (Al momento carica tutti i file, potrei implementare un menu per far caricare solo quelli che si vogliono)
                            caricaDaFile();
                            break;
                }
            } while (scelta != 20);

        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
}
