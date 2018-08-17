
package ejemplo1;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainExecutor {
    
    private static final int numCajeras = 4;

    public static void main(String[] args) 
    {        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente("Cliente 1", new int[] { 2, 2, 1, 5, 2 })); 
        clientes.add(new Cliente("Cliente 2", new int[] { 1, 1, 5, 1, 1 })); 
        clientes.add(new Cliente("Cliente 3", new int[] { 5, 3, 1, 5, 2 })); 
        clientes.add(new Cliente("Cliente 4", new int[] { 2, 4, 3, 2, 5 })); 
        clientes.add(new Cliente("Cliente 5", new int[] { 1, 3, 2, 2, 3 })); 
        clientes.add(new Cliente("Cliente 6", new int[] { 4, 2, 1, 3, 1 })); 
        clientes.add(new Cliente("Cliente 7", new int[] { 3, 3, 2, 4, 7 })); 
        clientes.add(new Cliente("Cliente 8", new int[] { 6, 1, 3, 1, 3 })); 
        
        ExecutorService executor = Executors.newFixedThreadPool(numCajeras);
        for (int i = 0; i < clientes.size(); i++) 
        {
            Cajera cajera = new Cajera("Cajera "+i,clientes.get(i));
            executor.execute(cajera);
        }
        executor.shutdown();	// Cierro el Executor
        try {
            // Espero a que terminen de ejecutarse todos los procesos
            // para pasar a las siguientes instrucciones
            executor.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException ex) {
            Logger.getLogger(MainExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
