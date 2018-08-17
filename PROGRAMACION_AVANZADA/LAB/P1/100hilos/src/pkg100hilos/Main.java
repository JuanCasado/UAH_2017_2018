/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg100hilos;

/**
 *
 * @author mr.blissfulgrin
 */
public class Main 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Hilo [] hilos = new Hilo[100];
        for (int x = 0; x < hilos.length; x++)
        {
            hilos[x] = new Hilo(x);
        }
        for (Hilo hilo : hilos) 
        {
            hilo.start();
        }
        
    }
    
}
