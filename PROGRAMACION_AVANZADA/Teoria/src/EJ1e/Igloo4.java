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
public class Igloo4 {
    private boolean pescando;
    public Igloo4(boolean b){
        pescando=b;
    }
    public synchronized void pescar(){
        pescando=true;
    }
    
    public synchronized void nopescar(){
        pescando=false;
    }
    
    public synchronized boolean estaPescando(){
        return pescando;
    }
}
