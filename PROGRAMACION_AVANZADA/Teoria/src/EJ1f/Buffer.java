/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1f;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Lofer
 */
public class Buffer {
    private Object[] buf;
    private int in=0, out=0, numElem=0, maximo=0;
    private Lock control = new ReentrantLock();
    private Condition lleno = control.newCondition();
    private Condition vacio = control.newCondition();
    
    public Buffer(int max){
        buf=new Object[max];
    }
    
    public void insertar(Object obj) throws InterruptedException{
        control.lock();
        while(numElem==maximo){
            lleno.await();
        }
        try{
            buf[in]=obj;
            numElem++;
            in=(in+1)%maximo;
            vacio.signal();
        }finally{
            control.unlock();
        }
    }
    
    public Object extraer(Object obj) throws InterruptedException{
        control.lock();
        while(numElem==0){
            vacio.await();
        }
        try{
            obj = buf[out];
            buf[out] = null;
            numElem = numElem-1;
            out=(out+1)%maximo;
            lleno.signal();
        }finally{
            control.unlock();
        }
        return (obj);
    }
}
