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
public class Hilo extends Thread
{
    private final int identificador;
    
    public Hilo (int identificador)
    {
        this.identificador = identificador;
    }
    
    @Override
    public void run ()
    {
        System.out.println(identificador);
    }
    
}
