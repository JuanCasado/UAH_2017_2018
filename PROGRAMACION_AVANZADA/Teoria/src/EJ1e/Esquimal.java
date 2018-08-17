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
public class Esquimal extends Thread{
    private int yo, tu;
    private Igloo4[] ig=new Igloo4[2];
    private Igloo1 igt;

    public Esquimal(int id, Igloo4[] ig, Igloo1 igt) {
        this.yo=id;
        tu=(id+1)%2;
        this.ig=ig;
        this.igt=igt;
    }

    public void run(){
        while(true){
            ig[yo].pescar();
            while(ig[tu].estaPescando()){
                ig[yo].nopescar();
                while(igt.miraTurno()==tu)
                try{
                    sleep(100+(int)(200.*Math.random()));
                }catch (Exception e){}
                ig[yo].pescar();
            }
            System.out.println("Esq."+yo+" pescando");
            try{
                sleep(100+(int)(200.*Math.random()));
                ig[yo].nopescar();
                igt.cambiaTurno(yo);
            }catch (Exception e){}
            try{
                sleep(100+(int)(200.*Math.random()));
            }catch (Exception e){}
        }        
    }
}
