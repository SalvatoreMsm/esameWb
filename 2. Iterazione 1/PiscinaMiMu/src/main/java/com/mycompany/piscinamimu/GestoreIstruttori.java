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
    
    public synchronized void caricaIstruttori(String nomeFile) throws IstruttoreGiaAssuntoException{
        
        try{
            
            BufferedReader bf = new BufferedReader(new FileReader(nomeFile));
            String idIstruttore, nome;

            Istruttore is;
            

            while((idIstruttore = bf.readLine()) != null){
                System.out.println("idIstruttore letto: '" + idIstruttore + "'");
                idIstruttore += "\n";
                nome=bf.readLine();
                nome += "\n";
                
                String input = idIstruttore + nome;
                System.setIn(new ByteArrayInputStream(input.getBytes()));
                AssumiIstruttore();
            }
            System.out.println("Numero istruttori caricati: " + elencoIstruttori.size());
            bf.close();
            
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
        
    }
    
    public synchronized void AssumiIstruttore() throws IstruttoreGiaAssuntoException, IOException{

            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Inserisci id istruttore --->");
            String id_istruttore = bf.readLine();
            if(elencoIstruttori.containsKey(id_istruttore)) throw new IstruttoreGiaAssuntoException(id_istruttore);
            System.out.println("Inserisci nome istruttore --->");
            String nome = bf.readLine();
            
            Istruttore is = new Istruttore(nome, id_istruttore);
            elencoIstruttori.put(is.getIdIstruttore(), is);

    
    }
    
    
    public synchronized Istruttore getIstruttore(String id_istruttore){
        return elencoIstruttori.get(id_istruttore);
    }
    
    
    public synchronized void StampaTutto(){
    
        for(Istruttore is: elencoIstruttori.values()){
            System.out.println(is);
            
            for(Corso c : is.getCorsi().values()){
                System.out.println(c.getIdCorso());
            }
            
    
    }
    }
    
}
