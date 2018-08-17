/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pecl2.classes;

/**
 *
 * @author mr.blissfulgrin
 */
public class Conexion
{
    private final ServerReciver reciver;
    private final ServerSender sender;
    
    public Conexion (ServerReciver reciver, ServerSender sender)
    {
        this.reciver = reciver;
        this.sender = sender;
    }
    
    /*
    * Métodos desde los que controlar al emisor
    */
    public void send()
    {
        sender.send();
    }
    public void send(String s)
    {
        sender.send(s);
    }
    
    /**
     * Finaliza la comunicación
     */
    public void end()
    {
        sender.send("X");
        reciver.end();
        sender.end();
    }
}
