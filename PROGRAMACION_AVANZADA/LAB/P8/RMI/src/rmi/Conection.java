/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author mr.blissfulgrin
 */
public interface Conection extends Remote
{
    public int add (int [] numbers) throws RemoteException;
    public int mul (int [] numbers) throws RemoteException;
    public int num () throws RemoteException;
}
