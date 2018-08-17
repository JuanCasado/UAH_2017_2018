/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_examen;

/**
 *
 * @author mr.blissfulgrin
 */
public class Coche extends Thread
{
    private final int id;
    private final boolean sentido;
    private final Cruce cruce;
    
    public Coche (int id, Cruce cruce)
    {
        this.id = id;
        this.cruce = cruce;
        this.sentido = id%2 == 0;
    }
    
    public boolean getSentido()
    {
        return sentido;
    }
    
    @Override
    public void run()
    {
        cruce.cruzar(this);
    }
    
    @Override
    public String toString()
    {
        return "Coche " + id;
    }
}
