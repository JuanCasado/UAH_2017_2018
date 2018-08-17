/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author mr.blissfulgrin
 */
public class Server 
{
    public void makePublic ()
    {
        try
        {
            Actions actions = new Actions();
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("//"+InetAddress.getLoopbackAddress().getHostAddress()+"/Actions", actions);
        }
        catch (IOException e)
        {
            System.out.println("ERROR S "+e.toString());
        }
    }
}
