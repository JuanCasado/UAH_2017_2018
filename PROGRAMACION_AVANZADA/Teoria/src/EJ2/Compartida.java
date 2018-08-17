/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lofer
 */
public class Compartida {
    private int temperatura=0;
    Lock candado = new ReentrantLock();
    
    void tomarTemperatura(){
        candado.lock();
        try{          
            int temp = (int) (Math.random()*(50 - (-20)) + (-20));
            temperatura=temp;
        }finally{
            candado.unlock();
        }
    }
    
    void leerTemperatura(){
        candado.lock();
        try{    
            System.out.println("La temperatura es de: "+temperatura+"ºC");
        }finally{
            candado.unlock();
        }
    }
}
