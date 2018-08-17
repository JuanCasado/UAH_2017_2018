/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugsimulator;

import java.util.Date;

/**
 *
 * @author mr.blissfulgrin
 */
public class Bicho extends Thread
{
    private final int generacion;
    private final String nombre;
    
    public Bicho(String nombre, int generacion)
    {
        this.nombre = nombre;
        this.generacion = generacion;
    }
    
    @Override
    public void run()
    {
        long t0 = (new Date()).getTime();
        System.out.println("NACE: "+nombre+" de generacion "+generacion);
        
        try
        {
            Thread.sleep((long)(Math.random()*500+500));
        }
        catch (Exception e)
        {
            System.out.println("ERROR "+ e.toString());
        }
        
        
        
        Bicho hijo = new Bicho(generarNombre(),generacion+1);
        
        if (generacion != 5)
        {
            hijo.start();
            try
            {
                hijo.join();
            }
            catch (Exception e)
            {
                System.out.println("ERROR "+e.toString());
            }
        }
        else
        {
            try
            {
                Thread.sleep((long)(Math.random()*1000+1000));
            }
            catch (Exception e)
            {
                System.out.println("ERROR "+ e.toString());
            }
        }
        
        long tf = (new Date()).getTime();
        
        System.out.println("MUERE: "+nombre+" de generacion "+generacion+" VIDA: "+(tf-t0));
    }
    
    private String generarNombre()
    {
        char [] data = new char[4];
        
        for (int x = 0; x < data.length; x++)
        {
            data[x] = (char) ((long)(Math.random()*25)+65);
        }
        
        return String.valueOf(data);
    }
}
