/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

/**
 *
 * @author mr.blissfulgrin
 */
public class Documento
{
    private final int id;
    private final int time;
    
    public Documento (int id)
    {
        this.id = id;
        this.time = (int)(Math.random()*1000+1000);
    }
    
    public int print ()
    {
        return time;
    }
    
    @Override
    public String toString()
    {
        return "Documento: " + id;
    }
}
