/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1b;

import EJ1a.*;

/**
 *
 * @author Lofer
 */
public class aMain {
    public static void main(String[] args){
        Igloo ig[]=new Igloo[2];
        ig[0]=new Igloo(false);
        ig[1]=new Igloo(false);
        Esquimal cero = new Esquimal(0, ig);
        Esquimal uno = new Esquimal(1, ig);
        cero.start();
        uno.start();
    }
}
