/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author mr.blissfulgrin
 */
public class Cliente
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {        
        Boolean control;
        Scanner user;
        Socket client;
        DataInputStream input;
        DataOutputStream output; 
        String mensaje;
        byte [] IP;
        
        
        try
        {
            IP = new byte [] {(byte)(192),(byte)(168),(byte)(1),(byte)(1)};
            //IP = InetAddress.getLocalHost().getAddress();
            control = true;
            user = new Scanner (System.in);
            
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            
            client = new Socket(InetAddress.getByAddress(IP),80);
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream()); 
            
            while (control)
            {
                /*
                System.out.print("Input message = ");
                mensaje = user.nextLine();
                output.writeUTF(mensaje);
                System.out.print("sent... ");
                mensaje = input.readUTF();
                if (mensaje.equals("F"))
                    control = false;
                else
                    System.out.println("Recived: "+mensaje);
                */
                System.out.println(input.readUTF());
            }
            input.close();
            output.close();
            client.close();
            System.out.println("END CORRECTLY");
        }
        catch (IOException e){System.out.println("ERROR");}
    }
    
}
