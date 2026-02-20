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
public class GestoreClienti {

    private Map<String, Cliente> elencoClienti;

    public GestoreClienti() {
        this.elencoClienti = new HashMap<>();
    }

    public void aggiungiCliente(Cliente cliente) throws ClienteGiaPresenteException {
        if (elencoClienti.containsKey(cliente.getIdCliente())) {
            throw new ClienteGiaPresenteException(cliente.getIdCliente());
        }
        elencoClienti.put(cliente.getIdCliente(), cliente);
    }

    // Caricamento clienti da file
    public synchronized void caricaClienti(String nomeFile) {
        try (BufferedReader bf = new BufferedReader(new FileReader(nomeFile))) {

            String idCliente, nome, cognome;

            while ((idCliente = bf.readLine()) != null) {
                nome = bf.readLine();
                cognome = bf.readLine();

                Cliente cliente = new Cliente(nome, cognome, idCliente);

                try {
                    aggiungiCliente(cliente);
                    System.out.println("Cliente caricato: " + cliente);
                } catch (ClienteGiaPresenteException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Numero clienti caricati: " + elencoClienti.size());

        } catch (IOException e) {
            System.out.println("ERRORE in fase di I/O: " + e.getMessage());
            System.exit(-1);
        }
    }

    public synchronized void stampaTuttoClienti() {
        for (Cliente cliente : elencoClienti.values()) {
            System.out.println("Cliente: " + cliente); // toString() di Cliente
            if (cliente.numCorsi() == 0) {
                System.out.println("NON iscritto a nessun corso");
            } else {
                System.out.println("  Corsi iscritti:");
                for (Corso corso : cliente.getCorsiIscritti().values()) {
                    // Stampa solo ID e nome del corso (nome dentro DescrizioneCorso)
                    System.out.println("    - ID Corso: " + corso.getIdCorso() +
                                       ", Nome: " + corso.getDescrizione().getNome());
                }
            
            }
            System.out.println("\n");
        }
    }

    
    public Cliente getCliente(String idCliente) {
        return elencoClienti.get(idCliente);
    }

    public Collection<Cliente> getElencoClienti() {
        return elencoClienti.values();
    }
}
