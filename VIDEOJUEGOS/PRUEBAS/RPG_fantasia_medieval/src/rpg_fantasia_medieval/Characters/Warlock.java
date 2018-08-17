/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg_fantasia_medieval.Characters;

import rpg_fantasia_medieval.armas.*;

/**
 *
 * @author mr.blissfulgrin
 */
public class Warlock extends Character 
{
    private int power;
    
    public Warlock (){}
    public Warlock (String name, int pv, int skill)
    {
        super(name,pv,skill);
        Wearpon wearpon = new Stick("Basic stick",2);
        super.addWearpon(wearpon);
    }
    
    public void setPower(int power)
    {
        this.power = power;
    }
    
    public int getPower()
    {
        return power;
    }
    
    @Override
    public String toString ()
    {
        return String.valueOf(super.toString() + " Power: " + power);
    }
    
    @Override
    protected void atack (Character enemy)
    {
        enemy.reciveDamage(enemy.getWearpon().atack()+enemy.getSkill());
        
        System.out.println(enemy.toString());
    }
}
