/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ2;

/**
 *
 * @author Lofer
 */
public class HiloSensor extends Thread{
    private int id;
    private Compartida compartida;

    public HiloSensor(int id, Compartida compartida) {
        this.id = id;
        this.compartida = compartida;
    }
    
    
    public void run(){
        compartida.tomarTemperatura();
    }
}
