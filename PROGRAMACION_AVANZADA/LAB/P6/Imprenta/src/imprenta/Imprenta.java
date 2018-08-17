/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imprenta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Imprenta
{

    public static void main(String[] args)
    {
        int operarios = 10;
        ArrayList <Documento> doc = new ArrayList <>();
        Queue <Future<Integer>> future = new LinkedList<>();
        Queue <Integer> cola = new LinkedList <>();
        for (int i = 0; i < operarios; i++)
        {
            cola.add(i);
        }
        
        for (int i = 0; i < 20; i++)
        {
            doc.add(new Documento(i));
        }
        
        ExecutorService ex = Executors.newFixedThreadPool(operarios);
        
        for (int i = 0; i < doc.size(); i++)
        {
            if (cola.peek() == null)
            {
                try
                {
                    cola.add(future.poll().get(40, TimeUnit.SECONDS));
                } catch (InterruptedException | ExecutionException | TimeoutException ex1)
                {
                    System.out.println("TIEMOUT 1");
                }
            }
            future.add(ex.submit(new Operario(cola.poll(),doc.get(i))));
        }
        
        ex.shutdown();
        
        try
        {
            ex.awaitTermination(100, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            System.out.println("TIMEOUT 2");
        }
    }
    
}
