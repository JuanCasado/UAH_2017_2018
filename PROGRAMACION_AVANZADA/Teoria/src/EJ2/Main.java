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
public class Main {
    public static void main(String[] args){
        Compartida c = new Compartida();
        HiloSensor HS = new HiloSensor(1, c);
        HiloDisplay HD = new HiloDisplay(2, c);
        
        HS.start();
        HD.start();
        
        try{
            HS.join();
            HD.join();
        }catch (Exception e){}
    }
}
