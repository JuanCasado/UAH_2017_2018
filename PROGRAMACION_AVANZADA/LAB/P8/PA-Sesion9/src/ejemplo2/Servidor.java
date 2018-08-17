package ejemplo2;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
 
public class Servidor
{ 
    public static void main(String args[])
    {
        try
        {
            Saludador obj = new Saludador(); //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar 
            Registry registry = LocateRegistry.createRegistry(1099); //Arranca rmiregistry local en el puerto 1099
            Naming.rebind("//localhost/ObjetoSaluda",obj);   //rebind sólo funciona sobre una url del equipo local 
            System.out.println("El Objeto Saluda ha quedado registrado");
        }
        catch (Exception e)
        {
            System.out.println(" Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}