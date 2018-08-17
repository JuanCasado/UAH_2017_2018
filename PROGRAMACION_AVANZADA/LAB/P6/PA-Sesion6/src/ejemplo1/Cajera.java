
package ejemplo1;

public class Cajera extends Thread {

    private String nombre; 
    private Cliente cliente;

    public Cajera(String nombre, Cliente cliente) 
    {
        this.nombre=nombre;
        this.cliente = cliente;
    }

    public void run() 
    {
        System.out.println("La cajera " + nombre + " COMIENZA A PROCESAR LA COMPRA DEL CLIENTE " + this.cliente.getNombre());

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) 
        {
            // Se procesa el pedido 
            this.esperarXsegundos(cliente.getCarroCompra()[i]);
            System.out.println("Procesado el producto " + (i + 1) + " del " + this.cliente.getNombre());
        }
        System.out.println("La cajera " + nombre + " HA TERMINADO DE PROCESAR LA COMPRA DEL " + this.cliente.getNombre());
    }

    private void esperarXsegundos(int segundos) 
    {
        try 
        {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }
    }
    
}
