/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1g;

import java.util.HashMap;

/**
 *
 * @author Lofer
 */
public class Main {
    public static void main(String[] args){
        HashMap a = new HashMap();
        Agenda agenda = new Agenda(a);
        Lector lector1 = new Lector(agenda, "lec1");
        Lector lector2 = new Lector(agenda, "lec2");
        Lector lector3 = new Lector(agenda, "lec3");
        Escritor escritor = new Escritor(agenda, "esc1");
        lector1.start();
        lector2.start();
        lector3.start();
        escritor.start();
    }
}
