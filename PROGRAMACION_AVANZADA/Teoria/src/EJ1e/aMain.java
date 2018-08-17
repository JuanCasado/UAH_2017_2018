/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1e;

import EJ1d.*;
import EJ1c.*;
import EJ1b.*;
import EJ1a.*;

/**
 *
 * @author Lofer
 */
public class aMain {
    public static void main(String[] args){
        Igloo4 ig[]=new Igloo4[2];
        ig[0]=new Igloo4(false);
        ig[1]=new Igloo4(false);
        Igloo1 igt=new Igloo1();
        Esquimal cero = new Esquimal(0, ig, igt);
        Esquimal uno = new Esquimal(1, ig, igt);
        cero.start();
        uno.start();
    }
}
