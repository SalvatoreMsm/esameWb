/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;

/**
 *
 * @author pmilo
 */
public class DescrizioneCorso {
 
    private String nome;
    private String tipologia_clienti;
    private int num_posti;
    private int durata;
    private int num_posti_occupati;

    // Costruttore
    
    public DescrizioneCorso(String nome, String tipologia_clienti, int num_posti, int durata, int num_posti_occupati) {
        this.nome = nome;
        this.tipologia_clienti = tipologia_clienti;
        this.num_posti = num_posti;
        this.durata = durata;
        this.num_posti_occupati = num_posti_occupati;
    }
    @Override
    public String toString() {
    return "Nome: " + nome +
           ", Tipologia clienti: " + tipologia_clienti +
           ", Posti totali: " + num_posti +
           ", Durata: " + durata + " lezioni" +
           ", Posti occupati: " + num_posti_occupati;
}
}
