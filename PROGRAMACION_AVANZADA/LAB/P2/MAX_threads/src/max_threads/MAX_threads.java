/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package max_threads;

import java.util.ArrayList;

/**
 *
 * @author mr.blissfulgrin
 */
public class MAX_threads {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ArrayList<Hilo> arr = new ArrayList<>();
        Hilo h;
        int x = 0;
        while(true)
        {
            
            h = new Hilo();
            h.start();
            arr.add(h);
            System.out.println(++x);            
        }
    }
}
