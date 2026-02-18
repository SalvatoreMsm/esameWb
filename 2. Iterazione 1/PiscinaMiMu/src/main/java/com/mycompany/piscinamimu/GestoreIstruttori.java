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
    
    public synchronized void AssumiIstruttore() throws IstruttoreGiaAssuntoException{
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Inserisci id istruttore --->");
            String id_istruttore = bf.readLine();
            if(elencoIstruttori.get(id_istruttore) != null) throw new IstruttoreGiaAssuntoException(id_istruttore);
            System.out.println("Inserisci nome istruttore --->");
            String nome = bf.readLine();
            
            Istruttore is = new Istruttore(nome, id_istruttore);
            elencoIstruttori.put(is.getIdIstruttore(), is);
        }catch(Exception e){System.out.println(e.getMessage());}
    
    }
    
    public synchronized Map<String, Istruttore> VisualizzaIstruttoriDisponibili(){
        
        Map<String, Istruttore> elencoIstruttoriDisponibili = new HashMap<>();
        for(Istruttore is: elencoIstruttori.values()){
        
            if(is.isDisponibile()){ 
                System.out.println(is);
                elencoIstruttoriDisponibili.put(is.getIdIstruttore(), is);
            }                   
        }        
        
        return elencoIstruttoriDisponibili;
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
