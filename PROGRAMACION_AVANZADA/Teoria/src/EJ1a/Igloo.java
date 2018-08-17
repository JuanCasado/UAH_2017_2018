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
public class Igloo {
    private int turno=0;
    public synchronized int miraTurno(){
        return turno;
    }
    
    public synchronized void cambiaTurno(int id){
        turno=(id+1)%2;
    }
}
