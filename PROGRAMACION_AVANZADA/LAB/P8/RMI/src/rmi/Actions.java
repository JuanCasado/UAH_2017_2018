/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author mr.blissfulgrin
 */
public class Actions extends UnicastRemoteObject implements Conection
{
    public int num = 0;
    public Actions () throws RemoteException {}
    @Override
    public int add (int [] numbers) throws RemoteException
    {
        int result = 0;
        
        for (int number : numbers)
            result += number;
        
        return result;
    }
    @Override
    public int mul (int [] numbers) throws RemoteException
    {
        int result = 0;
        
        for (int number : numbers)
            result *= number;
        
        return result;
    }
    @Override
    public int num () throws RemoteException
    {
        return num++;
    }
}
