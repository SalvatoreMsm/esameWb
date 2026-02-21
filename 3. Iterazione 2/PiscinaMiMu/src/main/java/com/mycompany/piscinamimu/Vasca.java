/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.piscinamimu;
import java.util.*;

/**
 *
 * @author pmilo
 */
public class Vasca {
    
    private String tipo_vasca;
    private String id_vasca;
    private Map<String, Corsia> elenco_corsie;

    // Costruttore

    
    public Vasca(String tipo_vasca, String id_vasca) {
        this.tipo_vasca = tipo_vasca;
        this.id_vasca = id_vasca;
        elenco_corsie = new HashMap<>();
    }
    
    public String getTipoVasca(){
        return this.tipo_vasca;
    }
    
    public String getIdVasca(){
        return this.id_vasca;
    }
    
    public Map<String, Corsia> getElencoCorsie(){
        return this.elenco_corsie;
    }
    
    public Corsia cercaCorsia(String id_corsia) throws CorsiaNonPresenteNellaVascaException{
        Corsia cr = this.elenco_corsie.get(id_corsia);
        
        if(cr == null) throw new CorsiaNonPresenteNellaVascaException(id_corsia);
        
        return cr;
    }
    
    public void AggiungiCorsia(Corsia cr) throws CorsiaGiaPresenteException{
        String id_corsia = ""+cr.getNumCorsia();
        if(this.elenco_corsie.get(id_corsia) != null) throw new CorsiaGiaPresenteException(cr.getNumCorsia());
        this.elenco_corsie.put(id_corsia, cr);
        cr.setVasca(this);
        System.out.println(this.elenco_corsie.size());
    }
    
    public void rimuoviCorsia(Corsia cr) throws CorsiaNonPresenteNellaVascaException{
        String id_corsia = ""+cr.getNumCorsia();
        if(!this.elenco_corsie.containsKey(id_corsia)) throw new CorsiaNonPresenteNellaVascaException(id_corsia);
        
        this.elenco_corsie.remove(id_corsia);
        cr.RemoveVasca();
        cr = null;
    }
    
    @Override
    public String toString(){
        String s = "Vasca: "+this.id_vasca;
        String p = " contiene corsie:\n";
        if(this.elenco_corsie == null) {return s;}
        for(Corsia cr : this.elenco_corsie.values()){
            p += cr.toString();
        }
        return s += p;
    }
        
}
