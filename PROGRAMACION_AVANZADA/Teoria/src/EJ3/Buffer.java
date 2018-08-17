/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lofer
 */
public class Buffer {
    private Object[] buf;
    private int maximo=0, numElem=0, in=0;
    private Lock control = new ReentrantLock();
    private Condition lleno = control.newCondition();
    private Condition vacio = control.newCondition();

    public Buffer(int max) {
        this.maximo=max;
        buf = new Object[max];
    }
    
    public void insertar(Object obj) throws InterruptedException{
        control.lock();
        while(numElem==maximo){
            lleno.await();
        }   
        try{
            buf[in] = obj;
            numElem++;
            in = (in+1)%maximo;
            vacio.signal();
        }finally{
            control.unlock();
        }
    }
    
    public void mostrarTemperaturas() throws InterruptedException{
        control.lock();
        while(numElem!=maximo){
            vacio.await();
        }
        try{
            for(int i=0; i<10; i++){
                System.out.println(i+"-Temperatura: "+buf[i]);
                lleno.signal();
            }
        }finally{
            control.unlock();
        }
    }
}
