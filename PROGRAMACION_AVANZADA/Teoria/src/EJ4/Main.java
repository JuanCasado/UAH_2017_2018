/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ4;

import EJ3.*;

/**
 *
 * @author Lofer
 */
public class Main {
    public static void main(String[] args){     //FUNCIONA, PERO NO PARA
        Buffer buf = new Buffer(100);
        Cabina HC = new Cabina(buf, "Sensor");
        SistemaCentral HSD = new SistemaCentral(buf, "Display");

        HC.start();
        HSD.start();
        
        try{
            HC.join();
            HSD.join();
        }catch (Exception e){}
    }
}
