/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1c;

import EJ1b.*;
import EJ1a.*;

/**
 *
 * @author Lofer
 */
public class Esquimal extends Thread{
    private int yo, tu;
    private Igloo[] ig=new Igloo[2];

    public Esquimal(int id, Igloo[] ig) {
        this.yo=id;
        tu=(id+1)%2;
        this.ig=ig;
    }

    public void run(){
        while(true){
            ig[yo].pescar();
            while(ig[tu].estaPescando()){
                try{
                    sleep(1+(int)(2.*Math.random()));
                }catch (Exception e){}
            }
                System.out.println("Esq."+yo+" pescando");
                try{
                    sleep(1+(int)(2.*Math.random()));
                    ig[yo].nopescar();
                }catch (Exception e){}
                try{
                    sleep(1+(int)(2.*Math.random()));
                }catch (Exception e){}
            
        }        
    }
}
