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
public class Escritor extends Thread{
    Agenda agenda;
    String id;

    public Escritor(Agenda agenda, String id) {
        this.agenda = agenda;
        this.id = id;
    }
    
    public void run(){
        for(int i=1; i<=20; i++){
            try{
                sleep(100+(int)(200*Math.random()));
                agenda.escribir(id+"", id+i);
                System.out.println("Lectura: "+i+"->"+id+i);
            }catch (InterruptedException e){}
        }
    }
}
