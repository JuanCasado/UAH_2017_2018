/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1a;

/**
 *
 * @author Lofer
 */
public class aMain {
    public static void main(String[] args){
        Igloo igloo= new Igloo();
        Esquimal cero = new Esquimal(0, igloo);
        Esquimal uno = new Esquimal(1, igloo);
        cero.start();
        uno.start();
    }
}
