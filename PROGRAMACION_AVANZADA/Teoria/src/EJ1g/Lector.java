/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ1g;

/**
 *
 * @author Lofer
 */
public class Lector extends Thread{
    Agenda agenda;
    String id;

    public Lector(Agenda agenda, String id) {
        this.agenda = agenda;
        this.id = id;
    }
    
    public void run(){
        for(int i=1; i<=20; i++){
            try{
                sleep(100+(int)(200*Math.random()));
                String valor=agenda.leer(i+"");
                System.out.println("Lectura: "+i+"->"+valor);
            }catch (InterruptedException e){}
        }
    }
}
