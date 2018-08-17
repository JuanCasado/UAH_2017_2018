/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1Explicitos;

/**
 *
 * @author Lofer
 */
public class Valor {
    public static void main(String[] args){
        Secuencia sec=new Secuencia();
        for (int i = 0; i < 100; i++) {
            Hilo hilo=new Hilo(i, sec);
            hilo.start();
        }
    }
}
