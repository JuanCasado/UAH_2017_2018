/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ4;

/**
 *
 * @author Lofer
 */
public class Cabina extends Thread{
    Buffer buf;
    String id;

    public Cabina(Buffer buf, String id) {
        this.buf = buf;
        this.id = id;
    }
    
    public void run(){
        Object msg;
        String temp;
        for (int i = 1; i <800;  i++) {
            try{
                int tipo = (int) (Math.random()*(1));
                if(tipo==1){
                    temp="A FAVOR";
                }else{
                    temp="EN CONTRA";
                }
                msg = (Object)(id+": "+temp);
                buf.insertar(msg);
                //System.out.println(msg);
            }catch(InterruptedException e){}      
        }
    }
}
