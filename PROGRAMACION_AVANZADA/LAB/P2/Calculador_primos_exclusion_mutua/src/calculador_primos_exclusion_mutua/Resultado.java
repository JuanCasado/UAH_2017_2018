/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculador_primos_exclusion_mutua;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author mr.blissfulgrin
 */
public class Resultado 
{
    private BigInteger suma;
    private final Lock cerrojo;
    
    public Resultado()
    {
        suma = new BigInteger("0");
        cerrojo = new ReentrantLock();
    }
    
    public BigInteger getSuma()
    {
        return suma;
    }   


    public void sumar(long n)
    {
        cerrojo.lock();
        try 
        {
            BigInteger bn = new BigInteger(String.valueOf(n));
            suma=suma.add(bn);
            //System.out.println("sumar " + String.valueOf(n) +" = "+ suma.toString());
        } 
        finally 
        {
            cerrojo.unlock();
        }
    }
}
