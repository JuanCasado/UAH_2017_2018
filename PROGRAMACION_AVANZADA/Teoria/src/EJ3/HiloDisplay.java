/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ3;

/**
 *
 * @author Lofer
 */
public class HiloDisplay extends Thread{
    Buffer buf;
    String id;

    public HiloDisplay(Buffer buf, String id) {
        this.buf = buf;
        this.id = id;
    }
    
    @Override
    public void run(){
        try{
            buf.mostrarTemperaturas();
        }catch(InterruptedException e){}      
    }
}
