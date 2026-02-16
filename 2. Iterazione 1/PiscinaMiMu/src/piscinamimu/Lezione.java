/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;

/**
 *
 * @author pmilo
 */
public class Lezione {

    private String idLezione;
    private String oraInizio;
    private String oraFine;

    // Costruttore

    
    public Lezione(String idLezione, String ora_inizio, String ora_fine) {
        this.idLezione = idLezione;
        this.oraInizio = ora_inizio;
        this.oraFine = ora_fine;
    }

    public String getIdLezione() {
        return idLezione;
    }
    
    @Override
    public String toString() {
        return idLezione + " " + oraInizio + "-" + oraFine;
    }

    
    
}
