/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1Explicitos;

/**
 *
 * @author Lofer
 */
public class Hilo extends Thread{
    private Secuencia sec;
    private int id;
    Hilo(int id, Secuencia sec){
        this.id=id;
        this.sec=sec;
    }
    
    public void run(){
        System.out.println("Hilo "+id+": secuencia="+sec.getSiguiente());
    }
}
