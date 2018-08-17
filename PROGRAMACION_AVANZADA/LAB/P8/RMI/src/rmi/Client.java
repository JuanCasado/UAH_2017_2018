/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.NotBoundException;

/**
 *
 * @author mr.blissfulgrin
 */
public class Client
{
    public int add (String data)
    {
        try
        {
            String [] leter = data.split(" ");
            int [] number = new int [leter.length];
            for (int x = 0; x<leter.length; x++)
            {
                number[x] = Integer.parseInt(leter[x]);
            }
            Conection conection = (Conection) Naming.lookup("//"+InetAddress.getLocalHost().getHostAddress()+"/Actions");
            return conection.add(number);
        }
        catch (IOException | NotBoundException e)
        {
            System.out.println("ERROR C "+ e.toString());
        }
        return 0;
    }
}
