/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1Explicitos;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lofer
 */
public class Secuencia {
    private int valor=0;
    Lock lock = new ReentrantLock();
    
    public  int getSiguiente(){
        lock.lock();
        try{
            valor++;
        }finally{
            lock.unlock();
        }
        return valor;
    }
}
