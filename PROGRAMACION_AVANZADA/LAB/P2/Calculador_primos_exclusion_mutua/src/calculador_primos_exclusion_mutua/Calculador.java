/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculador_primos_exclusion_mutua;

/**
 *
 * @author mr.blissfulgrin
 */
public class Calculador extends Thread
{
    private final long desde;
    private final long hasta;
    private final Resultado resultado;
    
    public Calculador (long desde, long hasta, Resultado resultado)
    {
        this.desde = desde;
        this.hasta = hasta;
        this.resultado = resultado;
    }
    
    @Override
    public void run()
    {
        for (long i=desde; i<=hasta; i++)
        {
            if(esPrimo(i))
            {
                resultado.sumar(i);
            }
        }
    }

   

    private boolean esPrimo(long n)
    {
        long raiz = (long) Math.sqrt((double) n);
        boolean primo = true;
        long i=2;
        while(primo && i<=raiz)
        {
            if (n % i == 0)
                primo=false;
            i++;
        }
        return primo;
    }
}
