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
public class Thiev extends Character
{
    private int energy;
    private int power;
    
    public Thiev(){}
    public Thiev (String name, int pv, int skill)
    {
        super(name,pv,skill);
        Wearpon wearpon = new Bow("Basic bow",2);
        super.addWearpon(wearpon);
    }
    
    public void setEnergy (int energy)
    {
        this.energy = energy;
    }
    
    public int getEnergy()
    {
        return energy;
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
        return String.valueOf(super.toString() + " Power: " + power + " Energy: " + energy);
    }
    
    @Override
    protected void atack (Character enemy)
    {
        enemy.reciveDamage(1);
        System.out.println(enemy.toString());
    }
}
