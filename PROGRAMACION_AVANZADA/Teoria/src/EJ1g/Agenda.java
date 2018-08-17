/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1g;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Lofer
 */
public class Agenda {
    private HashMap<String, String> agenda;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock r = lock.readLock();
    private Lock w = lock.writeLock();
    
    public Agenda(HashMap agenda){
        this.agenda=agenda;
    }
    
    public void escribir(String clave, String valor){
        w.lock();
        try{
            agenda.put(clave, valor);
        }finally{
            w.unlock();;
        }
    }
    
    public String leer(String clave){
        r.lock();
        try{
            String valor=agenda.get(clave);
            return valor;
        }finally{
            r.unlock();
        }
    }
}
