/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.concurrent.Callable;

/**
 *
 * @author mr.blissfulgrin
 */
public class Operario implements Callable <Integer>
{
    private final Integer id;
    private final Documento documento;
    
    public Operario (Integer id, Documento documento)
    {
        this.id = id;
        this.documento = documento;
    }
        
    @Override
    public Integer call()
    {
        System.out.println(this.toString() + " procesa " + documento.toString());
        
        try
        {
            Thread.sleep(documento.print());
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Terminado " + documento.toString());
        return this.id;
    }
    
    @Override
    public String toString()
    {
        return "Operario: " + id;
    }
}
