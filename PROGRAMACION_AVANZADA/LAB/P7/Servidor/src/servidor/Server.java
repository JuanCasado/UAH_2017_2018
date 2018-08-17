/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mr.blissfulgrin
 */
public class Server extends Thread
{
    private ServerSocket server;
    private Socket conexion;
    private AtomicInteger conexion_id;
    private final ArrayList <Conexion> conexiones = new ArrayList <>();
    private Boolean control;
    
    public Server ()
    {
        control = true;
        conexion_id = new AtomicInteger(0);
    }
    
    @Override
    public void run()
    {
        try
        {
            server = new ServerSocket(6666);
            while (control)
            { 
                System.out.println("WAITING...");
                conexion = server.accept();
                conexiones.add(new Conexion (conexion,conexion_id.get(),this));
                conexiones.get(conexion_id.get()).start();
                conexion_id.incrementAndGet();
            } 
            System.out.println("NO MORE CONEXIONS");
        }
        catch (IOException e){System.out.println("ERROR");}
    }
    
    public synchronized void eliminar (int id)
    {
        for (int x = id+1; x < conexiones.size(); x++)
        {
            conexiones.get(x).setID(x-1);
        }
        conexiones.remove(id);
        conexion_id.decrementAndGet();
    }
    
    public synchronized void parar ()
    {
        control = false;
        try
        {
            server.close();
            conexiones.forEach((c) ->
            {
                c.parar();
            });
        } 
        catch (IOException ex)
        {
            System.out.println("ERROR");
        }
    }
}
