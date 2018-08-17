/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1f;

import static java.lang.Thread.sleep;

/**
 *
 * @author Lofer
 */
public class Consumidor extends Thread{
    Buffer buf;
    String id;

    public Consumidor(Buffer buf, String id) {
        this.buf = buf;
        this.id = id;
    }
    
    public void run(){
        Object msg;
        for(int i=1; i<=20; i++){
            try{
                sleep(500+(int)(200*Math.random()));
                msg=(Object)(id+" - "+i);
                System.out.println("Produzco: "+msg);
            }catch (InterruptedException e){}
        }
    }
}
