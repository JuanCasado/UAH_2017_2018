/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1f;

/**
 *
 * @author Lofer
 */
public class Main {
    public static void main(String[] args){
        Buffer buf=new Buffer(10);
        Productor prod = new Productor(buf, "Productor");
        Consumidor cons = new Consumidor(buf, "Consumidor");
        prod.start();
        cons.start();
    }
}
