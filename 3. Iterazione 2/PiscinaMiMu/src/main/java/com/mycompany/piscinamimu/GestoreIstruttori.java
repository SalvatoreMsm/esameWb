/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.util.*;
import java.io.*;

/**
 *
 * @author salvatore
 */
public class GestoreIstruttori {
    
    private Map<String, Istruttore> elencoIstruttori; 
    
    public GestoreIstruttori(){
        this.elencoIstruttori = new HashMap<>();
    }
    
    public synchronized void caricaIstruttori(String nomeFile){
        
        try{
            
            BufferedReader bf = new BufferedReader(new FileReader(nomeFile));
            String idIstruttore, nome, cognome;

            Istruttore is;
            

            while((idIstruttore = bf.readLine()) != null){
                System.out.println("idIstruttore letto: '" + idIstruttore + "'");
                nome=bf.readLine();
                cognome=bf.readLine();
                AssumiIstruttore(nome, cognome, idIstruttore);
            }
            System.out.println("Numero istruttori caricati: " + elencoIstruttori.size());
            bf.close();
            
        }catch(IstruttoreGiaAssuntoException e){
            System.out.println(e.getMessage());
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void AssumiIstruttore(String nome, String cognome, String id_istruttore) throws IstruttoreGiaAssuntoException{
            
            if(elencoIstruttori.containsKey(id_istruttore)) throw new IstruttoreGiaAssuntoException(id_istruttore);
            
            Istruttore is = new Istruttore(nome, cognome, id_istruttore);
            elencoIstruttori.put(is.getIdIstruttore(), is);

    
    }
    
    
    public synchronized Istruttore getIstruttore(String id_istruttore) throws IstruttoreNonDisponibile{
        Istruttore is = elencoIstruttori.get(id_istruttore);
        if(is == null) throw new IstruttoreNonDisponibile(id_istruttore);
        return elencoIstruttori.get(id_istruttore);
    }
    
    public synchronized Map<String, Istruttore> mostraIstruttoriDisponibili(){
        
        Map<String, Istruttore> elencoIstruttoriDisponibili = new HashMap<>();
        for(Istruttore is: elencoIstruttori.values()){
        try{
            if(is.isDisponibile()){ 
                System.out.println(is);
                elencoIstruttoriDisponibili.put(is.getIdIstruttore(), is);
            } 
        }catch(IstruttoreNonDisponibile e){System.out.println(e.getMessage());}
        }        
        
        return elencoIstruttoriDisponibili;
    }
    
    
    public synchronized void mostraTuttiIstruttori() {
        for (Istruttore is : elencoIstruttori.values()) {
            is.stampaDettagli();
        }
    }
 
    public void mostraIstruttoriPerDisponibilita(boolean disponibile) {
        boolean trovato = false;

        for (Istruttore is : elencoIstruttori.values()) {
            boolean isDisp;
            try {
                isDisp = is.isDisponibile();
            } catch (IstruttoreNonDisponibile e) {
                isDisp = false;
            }

            if (isDisp == disponibile) {
                is.stampaDettagli();
                trovato = true;
            }
        }

        if (!trovato) {
            String stato = disponibile ? "disponibile" : "occupato";
            System.out.println("Nessun istruttore " + stato + " trovato.");
        }
    }
    
    public void mostraIstruttore(String idIstruttore) {
        Istruttore is = elencoIstruttori.get(idIstruttore);
        if (is == null) {
            System.out.println("Istruttore non trovato.");
            return;
        }
        is.stampaDettagli();
    }
}
