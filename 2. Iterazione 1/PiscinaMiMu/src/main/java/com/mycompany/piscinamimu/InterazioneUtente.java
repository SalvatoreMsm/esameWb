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
    
    private GestoreCorsi gc;
    private GestoreIstruttori i = new GestoreIstruttori(); //da cambiare con costruttori
    
    public InterazioneUtente(GestoreCorsi g){
        this.gc = g;
    }
    
    public int menu(BufferedReader bf){
        try{
            //System.out.println("1. Carica corsi da file.");
            System.out.println("1. Inserisci nuovo corso.");
            System.out.println("2. Inserisci nuova lezione.");
            System.out.println("3. Modifica un corso.");
            System.out.println("4. Assumi Istruttore");
            System.out.println("10. Stampa Corsi.");
            System.out.println("11. Stampa Istruttori");

            System.out.println("20. EXIT.");
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
            System.out.println("3. Modifica Numero Posti Disponibili.");
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
    
    
    public void avvia(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nomeFileCorsi, nomeFileLezioni;
        int scelta = 0;
        int sceltaModifica = 0;
        int sceltaModificaAttributo = 0;
        int sceltaModificaAttributoLezione = 0;

        try{
            
            System.out.println("Nome file corsi: ");
            nomeFileCorsi = bf.readLine();
            gc.caricaCorsi(nomeFileCorsi);
            System.out.println("Nome file lezioni: ");
            nomeFileLezioni = bf.readLine();
            gc.caricaLezioni(nomeFileLezioni);
            
            while(scelta != 20){
                scelta = menu(bf);
                switch(scelta){
                    case 1: // Nuovo Corso
                        try{
                            String idCorso, risposta;
                            idCorso = gc.aggiungiCorso();
                            do{
                                System.out.println("Vuoi aggiungere una lezione? (Si/No)");
                                risposta=bf.readLine();
                                if(risposta.equalsIgnoreCase("Si"))
                                    gc.aggiungiLezione(idCorso);
                            }while(risposta.equalsIgnoreCase("Si"));

                        }catch(Exception e){
                            System.out.println("OPS: qualcosa è andato storto!");
                        }
                        break;
                    case 2: // Elimina Corso
                        try{
                            String idCorso="";
                            gc.aggiungiLezione(idCorso);
                        }catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                        catch(Exception e){
                            System.out.println("OPS: qualcosa è andato storto!");
                        }
                        /* Da inserire dopo aver sviluppato il codice dell'eccezione in GestoreCorsi
                        catch(ProgrammazionePienaException e){
                            System.out.println("Programmazione lezioni del corso piena!");
                        }*/
                        break;
                    case 3: // Modifica Corso
                        System.out.println("Inserire ID Corso da modificare: ");
                        String idCorso = bf.readLine();
                        try{
                            gc.cercaCorso(idCorso); //questo non è superfluo?

                            if(sceltaModifica != 20){
                                sceltaModifica=menuModificaCorso(bf);


                                switch(sceltaModifica){                                                         
                                    case 1: // Elimina Lezione
                                        try{
                                            String risposta;
                                            do{
                                                gc.eliminaLezione(idCorso);
                                                System.out.println("Vuoi eliminare un'altra lezione? (Si/No)");
                                                risposta=bf.readLine();
                                            }while(risposta.equalsIgnoreCase("Si"));
                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }                                    
                                        break;
                                    case 2: //Elimina Corso
                                        try{
                                            gc.eliminaCorso(idCorso);
                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }
                                        break;
                                    case 3: //Aggiungi Lezione
                                        try{
                                            String risposta;
                                            do{
                                                gc.aggiungiLezione(idCorso);
                                                System.out.println("Vuoi aggiungere un'altra lezione? (Si/No)");
                                                risposta=bf.readLine();
                                            }while(risposta.equalsIgnoreCase("Si"));

                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }
                                        break;
                                    case 4: //Modifica Attributi corso
                                       
                                        do{    
                                            sceltaModificaAttributo = menuModificaAttributi(bf);
                                        
                                        switch(sceltaModificaAttributo){
                                        
                                            case 1: //modifica nome corso
                                                try{
                                                     //inserire tutta questa parte di messaggi a schermo e bf.readLine() 
                                                    gc.ModificaNome(idCorso);             //all'interno di modificaNome stesso e aggiungere le exception con 
                                                }catch(CorsoNonPresenteException e){                                               //la classe apposita 
                                                    System.out.println(e.getMessage());
                                                }catch(Exception e){System.out.println("OPS: qualcosa è andato storto!");}
                                                    break;
                                                    
                                            case 2:
                                                try{
                                                    
                                                    gc.ModificaTipologiaClienti(idCorso);
                                                }catch(CorsoNonPresenteException e){                                               //la classe apposita 
                                                    System.out.println(e.getMessage());
                                                }
                                                catch(Exception e){
                                                    System.out.println("OPS: qualcosa è andato storto!");
                                                }
                                                    break;
                                                    
                                            case 3:
                                                try{
                                                    gc.ModificaNumeroPosti(idCorso);
                                                }catch(CorsoNonPresenteException e){                                               //la classe apposita 
                                                    System.out.println(e.getMessage());
                                                }
                                                catch(Exception e){
                                                    System.out.println("OPS: qualcosa è andato storto!");
                                                }
                                                    break;
                                            
                                            case 4:
                                                try{
                                                    gc.ModificaDurata(idCorso);
                                                }catch(CorsoNonPresenteException e){                                               //la classe apposita 
                                                    System.out.println(e.getMessage());
                                                }catch(Exception e){
                                                    System.out.println("OPS: qualcosa è andato storto!");
                                                }
                                                    break;
                                            
                                            case 5:
                                                try{
                                                    gc.ModificaPostiOccupati(idCorso);
                                                }catch(CorsoNonPresenteException e){                                               //la classe apposita 
                                                    System.out.println(e.getMessage());
                                                }
                                                catch(Exception e){
                                                    System.out.println("OPS: qualcosa è andato storto!");
                                                }
                                                    break;
                                            case 6: 
                                                
                                                System.out.println("Inserire ID Lezione");
                                                String idLezione = bf.readLine();
                                                
                                                do{
                                                
                                                   sceltaModificaAttributoLezione = menuModificaAttributiLezione(bf); 
                                                   
                                                   switch(sceltaModificaAttributoLezione){
                                                   
                                                       case 1:
                                                           try{
                                                               gc.ModificaOraInizioLezione(idCorso, idLezione);
                                                               
                                                           }catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                                                           catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                                                           catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                                                           sceltaModificaAttributoLezione = 20;}
                                                           catch(Exception e){
                                                           System.out.println("OPS: qualcosa è andato storto!");
                                                           }
                                                        break;
                                                        
                                                        case 2:
                                                           try{
                                                               gc.ModificaOraFineLezione(idCorso, idLezione);
                                                               
                                                           }catch(LezioniConOrariNonValidiException e){System.out.println(e.getMessage());}
                                                           catch(CorsoNonPresenteException e){System.out.println(e.getMessage());}
                                                           catch(LezioneNonPresenteException e){System.out.println(e.getMessage());
                                                            sceltaModificaAttributoLezione = 20;}
                                                           catch(Exception e){
                                                           System.out.println("OPS: qualcosa è andato storto!");
                                                           }
                                                        break;
                                                   
                                                   }
                                                
                                                }while(sceltaModificaAttributoLezione != 20);
                                                
                                                break;
                                                
                                            
                                        }
                                        }while(sceltaModificaAttributo != 20);
                                        break;
                                    case 10:
                                        gc.stampaTutto(); // funzione di utilità
                                        break;
                                }
                            }
                        }catch(CorsoNonPresenteException e) {
                            System.out.println("Errore: " + e.getMessage() + ". Torna al menu principale.");
                        }
                        break;
                    case 4: //Assumi Istruttore    
                        try{                                                
                            i.AssumiIstruttore();
                        }catch(IstruttoreGiaAssuntoException e){System.out.println(e.getMessage());}
                        catch(Exception e){
                          System.out.println("OPS: qualcosa è andato storto!");
                          }
                        break;
   
                    case 10:
                        gc.stampaTutto(); // funzione di utilità
                        break;
                    case 11: //Stampa Lista Istruttori
                        i.StampaTutto();
                        break;
                }
            }
            
        
        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);  
        }
        
    }
    
}
