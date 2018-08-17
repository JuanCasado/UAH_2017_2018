/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tennis;

/**
 *
 * @author mr.blissfulgrin
 */
public class Tenista extends Thread
{
    private final int id;
    private final Pista pista;
    
    public Tenista (int id, Pista pista)
    {
        this.id = id;
        this.pista = pista;
    }
    
    public Boolean getSexo()
    {
        return id%2 == 0;
    }
    
    @Override
    public String toString ()
    {
        return String.valueOf("Tenista "+id);
    }
    
    @Override
    public void run ()
    {
        pista.jugar(this);
    }
}
