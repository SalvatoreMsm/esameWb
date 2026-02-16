/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package piscinamimu;

/**
 *
 * @author pmilo
 */
public class PiscinaMiMu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GestoreCorsi g = new GestoreCorsi();
        ThreadInterattivo ti = new ThreadInterattivo(g);
        //ThreadReport tr = new ThreadReport(g);
        
        //tr.setDaemon(true);
        
        ti.start();
       // tr.start();
        
        
    }
    
}
