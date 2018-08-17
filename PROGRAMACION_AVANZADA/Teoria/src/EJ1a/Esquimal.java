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
public class Esquimal extends Thread{
    private int id;
    private Igloo ig;

    public Esquimal(int id, Igloo ig) {
        this.id = id;
        this.ig = ig;
    }
    
    public void run(){
        while(true){
            try{
                sleep(10+(int)(20.*Math.random()));
            }catch (Exception e){}
            System.out.println("Esq. "+id+" pescando");
            try{
                sleep(100+(int)(200.*Math.random()));
            }catch (Exception e){}
            ig.cambiaTurno(id);
        }
    }
}
