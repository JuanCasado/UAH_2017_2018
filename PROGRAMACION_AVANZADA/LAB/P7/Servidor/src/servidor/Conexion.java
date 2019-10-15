/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mr.blissfulgrin
 */
public class Conexion extends Thread
{ 
    private final Socket conexion;
    private final DataInputStream input;
    private final DataOutputStream output; 
    private String mensaje;
    private int id;
    private final Server server;
    private AtomicBoolean control;
    
    public Conexion (Socket conexion, int x,Server server) throws IOException {
        input = new DataInputStream(conexion.getInputStream());
        output = new DataOutputStream(conexion.getOutputStream()); 
        this.id = x;
        this .conexion = conexion;
        this.server = server;
        this.control = new AtomicBoolean(true);
    }
    
    @Override
    public void run () {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("START "+id);
            while (control.get()) {
                mensaje = input.readUTF();
                System.out.println(mensaje);
                System.out.print(">");
                output.writeUTF(keyboard.nextLine());
                System.out.println("SENT");
            }
            input.close();
            output.close();
            conexion.close();
            System.out.println("END CORRECTLY " + id);
        }
        catch (IOException e) {
            System.out.println("ERROR IN " + id);
        }
        finally {
            server.eliminar(id);
        }
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void parar () {
        control.set(false);
        try {
            input.close();
            output.close();
            conexion.close();
        } catch (IOException ex) { }
    }
}
