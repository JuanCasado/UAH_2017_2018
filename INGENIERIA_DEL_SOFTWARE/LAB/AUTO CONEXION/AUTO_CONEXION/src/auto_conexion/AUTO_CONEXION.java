/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto_conexion;

/**
 *
 * @author mr.blissfulgrin
 */
public class AUTO_CONEXION
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SenderUPD send = new SenderUPD();
        ReciverUDP reciver = new ReciverUDP(send);
        send.start();
        reciver.start();
    }
    
}
