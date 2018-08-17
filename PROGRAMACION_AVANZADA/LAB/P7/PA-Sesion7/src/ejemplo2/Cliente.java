package ejemplo2;
import java.io.*;
import java.net.*;

public class Cliente
{
    public static void main(String args[])
    {
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String mensaje, respuesta;
        try
        {
            cliente = new Socket(InetAddress.getLocalHost(),5000);   //Creamos el socket para conectarnos al puerto 5000 del servidor
            entrada = new DataInputStream(cliente.getInputStream());  //Creamos los canales de entrada/salida
            salida = new DataOutputStream(cliente.getOutputStream());
            mensaje="Miguel Sánchez";
            salida.writeUTF(mensaje);                                 // Enviamos un mensaje al servidor
            respuesta = entrada.readUTF();                            // Leemos la respuesta
            System.out.println("Mi mensaje es: "+mensaje);
            System.out.println("Respuesta del servidor: "+respuesta);
            cliente.close();                                          // Cerramos la conexión
        }
        catch (IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
