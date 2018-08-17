/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrera_foto;

/**
 *
 * @author mr.blissfulgrin
 */
public class Carrera_foto 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Fotografo fotografo = new Fotografo();
        Corredor [] corredor = new Corredor [30];
        for (int x = 0; x < 30; x++)
        {
            corredor[x] = new Corredor(x,fotografo);
        }
        for (int x = 0; x < 30; x++)
        {
            corredor[x].start();
        }
        for (int x = 0; x < 30; x++)
        {
            try
            {              
            corredor[x].join();
            }
            catch(Exception e)
            {
                
            }
        }
        System.out.println("CARRERA TERMINADA");
    }
}
