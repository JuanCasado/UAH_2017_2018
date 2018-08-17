/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJ3;

/**
 *
 * @author Lofer
 */
public class HiloSensor extends Thread{
    Buffer buf;
    String id;

    public HiloSensor(Buffer buf, String id) {
        this.buf = buf;
        this.id = id;
    }
    
    @Override
    public void run(){
        Object msg;
        for (int i = 1; i <20;  i++) { //HAGO UN FOR DE 20, PERO SOLO PUEDEN ENTRAR 10 POR LA CONDICIÓN DEL LOCK
            try{
                int temp = (int) (Math.random()*(50 - (-20)) + (-20));
                msg = (Object)(id+": "+temp);
                buf.insertar(msg);
                //System.out.println(msg);
            }catch(InterruptedException e){}      
        }
    }
}
