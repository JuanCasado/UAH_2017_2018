/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exchanger;

/**
 *
 * @author mr.blissfulgrin
 */
public class Recolector extends Thread
{
    private Portador[] portador;
    
    public void setRecolector (Portador [] portador)
    {
        this.portador = portador;
    }
    
    @Override
    public void run()
    {
        for (Portador p : portador)
        {
            System.out.println(p.toString() +" Saltos: "+ p.getSaltos());
        }
    }
}
