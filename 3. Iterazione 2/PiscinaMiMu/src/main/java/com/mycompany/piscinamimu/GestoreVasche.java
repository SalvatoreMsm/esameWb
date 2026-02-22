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
    
    public Vasca cercaVasca(String id_vasca) throws VascaNonPresenteException{
        Vasca v = this.elencoVasche.get(id_vasca);
        
        if(v == null) throw new VascaNonPresenteException(id_vasca);
        else return v;
    }
    
    
    private Vasca creaVasca(Vasca.TipoVasca tipo, String id) {

        switch (tipo) {
            case DONNE:
                return new VascaDonne(id);
            case UOMINI:
                return new VascaUomini(id);
            case BAMBINI:
                return new VascaBambini(id);
            case MISTA:
                return new VascaMista(id);
            case RIABILITAZIONE:
                return new VascaRiabilitazione(id);
            default:
                throw new IllegalStateException("Tipo non gestito");
        }
    }
    
    
    public void aggiungiVasca(String tipo_vasca, String id_vasca)
            throws VascaGiaPresenteException, TipologiaVascaNonEsistenteException {

        if (elencoVasche.containsKey(id_vasca)) {
            throw new VascaGiaPresenteException(id_vasca);
        }

        Vasca.TipoVasca tipo = Vasca.TipoVasca.fromString(tipo_vasca);
        Vasca v = creaVasca(tipo, id_vasca);

        elencoVasche.put(id_vasca, v);
    }
    
    
    public void AssegnaCorsiaToVasca(String id_vasca, Corsia cr) throws VascaNonPresenteException, CorsiaGiaPresenteException, CorsiaGiaEsistenteException{
        Vasca v = this.cercaVasca(id_vasca);
        String id_corsia = ""+cr.getIdCorsia();
        this.CheckCorsiaNonEsistente(id_corsia);
        v.AggiungiCorsia(cr);
    }
    
    public void RemoveCorsiaToVasca(String id_vasca, String id_corsia) throws VascaNonPresenteException, CorsiaNonPresenteNellaVascaException{
        Vasca v = this.cercaVasca(id_vasca);
        Corsia cr = v.cercaCorsia(id_corsia);
        cr.RemoveAllLezioni();
        v.rimuoviCorsia(cr);
    }
    
    public void CaricaVasche(String FILE_VASCHE) throws VascaNonPresenteException, VascaGiaPresenteException, TipologiaVascaNonEsistenteException{
        
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
            String num_corsia, id_vasca;

            Corsia cd;
            Vasca v;

            while((num_corsia = bf.readLine()) != null){
                
                id_vasca = bf.readLine();
                
                v = this.cercaVasca(id_vasca);
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
    
    
    
    public synchronized void mostraTutteVasche() {
        for (Vasca v : elencoVasche.values()) {
            v.stampaDettagli();
        }
    }
    public void mostraVaschePerTipologia(String tipo) throws TipologiaVascaNonEsistenteException {

        boolean trovato = false;

        Vasca.TipoVasca tipoEnum = Vasca.TipoVasca.fromString(tipo);

        for (Vasca v : elencoVasche.values()) {
            if (v.getTipo() == tipoEnum) {
                v.stampaDettagli();
                trovato = true;
            }
        }

        if (!trovato) {
            System.out.println("Nessuna vasca trovata per il tipo: " + tipo);
        }
    }
}
