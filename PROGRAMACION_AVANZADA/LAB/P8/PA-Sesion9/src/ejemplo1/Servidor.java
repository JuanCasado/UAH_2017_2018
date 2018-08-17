package ejemplo1;
import java.rmi.*;

public class Servidor
{
    public static void main(String args[])
    {
        try
        {
            Saludador obj = new Saludador(); //Crea una instancia del objeto que implementa la interfaz, como objeto a registrar
            Naming.rebind("//127.0.0.1/ObjetoSaluda",obj);
            System.out.println("El objeto Saluda ha quedado registrado");
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
