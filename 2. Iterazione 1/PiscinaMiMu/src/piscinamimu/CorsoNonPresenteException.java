/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package piscinamimu;

/**
 *
 * @author pmilo
 */
public class CorsoNonPresenteException extends Exception{
    
    public CorsoNonPresenteException(String idCorso) {
        super("Il corso con id " + idCorso + " non è presente.");
    }
    
}
