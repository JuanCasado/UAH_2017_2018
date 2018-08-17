/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg_fantasia_medieval;

import rpg_fantasia_medieval.Characters.*;

/**
 *
 * @author mr.blissfulgrin
 */
public class RPG_fantasia_medieval 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Warlock w = new Warlock ("Warlock", 10, 10);
        Thiev t = new Thiev ("Thiev", 10, 10);
        
        t.fight(w);
    }
    
}
