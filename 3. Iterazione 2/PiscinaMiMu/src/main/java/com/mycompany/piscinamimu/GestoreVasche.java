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
public class GestoreVasche {
    
    private Map<String, Vasca> elencoVasche;
    
    public GestoreVasche() {
        this.elencoVasche = new HashMap<>();
    }
    
    public Vasca CercaVasca(String id_vasca) throws VascaNonPresenteException{
        Vasca v = this.elencoVasche.get(id_vasca);
        
        if(v == null) throw new VascaNonPresenteException(id_vasca);
        else return v;
    }
    
    public void aggiungiVasca(String tipo_vasca, String id_vasca) throws VascaGiaPresenteException{
        Vasca v = new Vasca(tipo_vasca, id_vasca);
        if(this.elencoVasche.containsKey(id_vasca)) throw new VascaGiaPresenteException(id_vasca);
        this.elencoVasche.put(id_vasca, v);
    }
    
    public void AssegnaCorsiaToVasca(String id_vasca, Corsia cr) throws VascaNonPresenteException, CorsiaGiaPresenteException, CorsiaGiaEsistenteException{
        Vasca v = this.CercaVasca(id_vasca);
        String id_corsia = ""+cr.getNumCorsia();
        this.CheckCorsiaNonEsistente(id_corsia);
        v.AggiungiCorsia(cr);
    }
    
    public void RemoveCorsiaToVasca(String id_vasca, String id_corsia) throws VascaNonPresenteException, CorsiaNonPresenteNellaVascaException{
        Vasca v = this.CercaVasca(id_vasca);
        Corsia cr = v.cercaCorsia(id_corsia);
        cr.RemoveAllLezioni();
        v.rimuoviCorsia(cr);
    }
    
    public void CaricaVasche(String FILE_VASCHE) throws VascaNonPresenteException, VascaGiaPresenteException{
        
        try{
            
            BufferedReader bf = new BufferedReader(new FileReader(FILE_VASCHE));
            String tipo_vasca, id_vasca;
            

            while((id_vasca = bf.readLine()) != null){
                System.out.println("idVasca letto: '" + id_vasca + "'");
                tipo_vasca=bf.readLine();
                this.aggiungiVasca(tipo_vasca, id_vasca);
            }
            System.out.println("Numero vasche caricate: " + elencoVasche.size());
            bf.close();
            
            
        
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public synchronized void CaricaCorsie(String FILE_CORSIE) throws VascaNonPresenteException, 
            CorsiaGiaPresenteException{
        try{
            
            BufferedReader bf = new BufferedReader(new FileReader(FILE_CORSIE));
            String num_corsia_string, id_vasca;

            Corsia cd;
            Vasca v;

            while((num_corsia_string = bf.readLine()) != null){
                
                int num_corsia = Integer.parseInt(num_corsia_string);
                id_vasca = bf.readLine();
                
                v = this.CercaVasca(id_vasca);
                cd = new Corsia(num_corsia);
                cd.setVasca(v);
                v.AggiungiCorsia(cd);
                
            }

            bf.close();
            
        
        }catch(IOException e){
            System.out.println("ERRORE IN FASE DI I/O");
            System.exit(-1);
        }
    }
    
    public Corsia CercaCorsia(String id_corsia) throws CorsiaNonEsistenteException{
        Corsia cr = null;
        for(Vasca v : this.elencoVasche.values()){
            cr = v.getElencoCorsie().get(id_corsia);
            if( cr != null) return cr;
        }
        if(cr == null) throw new CorsiaNonEsistenteException(id_corsia);
        return cr;
    }
    
    public boolean CheckCorsiaNonEsistente(String id_corsia)throws CorsiaGiaEsistenteException{
        for(Vasca v : this.elencoVasche.values()){
            if(v.getElencoCorsie().containsKey(id_corsia)) throw new CorsiaGiaEsistenteException(id_corsia);
        }
        return true;
    }
    
    
    
    public synchronized void StampaTutto(){
        
        
        for(Vasca v : elencoVasche.values()){
            if(v.getElencoCorsie() == null) System.out.println("Nessuna corsia associata alla vasca");
            System.out.print(v);
        
    
    }
    }
    
}
