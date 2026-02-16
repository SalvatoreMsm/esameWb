/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;
import java.io.*;
/**
 *
 * @author pmilo
 */
public class ThreadInterattivo extends Thread{
    
    private GestoreCorsi g;
    
    public ThreadInterattivo(GestoreCorsi g){
        this.g = g;
    }
    
    public int menu(BufferedReader bf){
        try{
            //System.out.println("1. Carica corsi da file.");
            System.out.println("1. Inserisci nuovo corso.");
            System.out.println("2. Inserisci nuova lezione.");
            System.out.println("3. Modifica un corso.");
            System.out.println("10. Stampa Corsi.");

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
    
    
    public void run(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String nomeFileCorsi, nomeFileLezioni;
        int scelta = 0;
        int sceltaModifica = 0;

        try{
            
            System.out.println("Nome file corsi: ");
            nomeFileCorsi = bf.readLine();
            g.caricaCorsi(nomeFileCorsi);
            System.out.println("Nome file lezioni: ");
            nomeFileLezioni = bf.readLine();
            g.caricaLezioni(nomeFileLezioni);
            
            while(scelta != 20){
                scelta = menu(bf);
                switch(scelta){
                    case 1: // Nuovo Corso
                        try{
                            String idCorso, risposta;
                            idCorso = g.aggiungiCorso();
                            do{
                                System.out.println("Vuoi aggiungere una lezione? (Si/No)");
                                risposta=bf.readLine();
                                if(risposta.equalsIgnoreCase("Si"))
                                    g.aggiungiLezione(idCorso);
                            }while(risposta.equalsIgnoreCase("Si"));

                        }catch(Exception e){
                            System.out.println("OPS: qualcosa è andato storto!");
                        }
                        break;
                    case 2: // Elimina Corso
                        try{
                            String idCorso="";
                            g.aggiungiLezione(idCorso);
                        }catch(Exception e){
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
                            g.cercaCorso(idCorso);

                            if(sceltaModifica != 20){
                                sceltaModifica=menuModificaCorso(bf);


                                switch(sceltaModifica){                                                         
                                    case 1: // Elimina Lezione
                                        try{
                                            String risposta;
                                            do{
                                                g.eliminaLezione(idCorso);
                                                System.out.println("Vuoi eliminare un'altra lezione? (Si/No)");
                                                risposta=bf.readLine();
                                            }while(risposta.equalsIgnoreCase("Si"));
                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }                                    
                                        break;
                                    case 2: //Elimina Corso
                                        try{
                                            g.eliminaCorso(idCorso);
                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }
                                        break;
                                    case 3: //Aggiungi Lezione
                                        try{
                                            String risposta;
                                            do{
                                                g.aggiungiLezione(idCorso);
                                                System.out.println("Vuoi aggiungere un'altra lezione? (Si/No)");
                                                risposta=bf.readLine();
                                            }while(risposta.equalsIgnoreCase("Si"));

                                        }catch(Exception e){
                                            System.out.println("OPS: qualcosa è andato storto!");
                                        }
                                        break;
                                    case 10:
                                        g.stampaTutto(); // funzione di utilità
                                        break;
                                }
                            }
                        }catch(CorsoNonPresenteException e) {
                            System.out.println("Errore: " + e.getMessage() + ". Torna al menu principale.");
                        }
                        break;
                    case 10:
                        g.stampaTutto(); // funzione di utilità
                        break;
                }
            }
            
        
        } catch (IOException e) {
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);  
        }
        
    }
    
}
