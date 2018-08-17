/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ3;

/**
 *
 * @author Lofer
 */
public class Main {
    public static void main(String[] args){     //FUNCIONA, PERO NO PARA
        Buffer buf = new Buffer(10);
        HiloSensor HS = new HiloSensor(buf, "Sensor");
        HiloDisplay HD = new HiloDisplay(buf, "Display");

        HS.start();
        HD.start();
        
        try{
            HS.join();
            HD.join();
        }catch (Exception e){}
    }
}
