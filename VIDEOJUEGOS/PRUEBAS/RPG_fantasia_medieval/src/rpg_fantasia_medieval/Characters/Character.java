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
public abstract class Character 
{
    private Wearpon wearpon;
    private int pv;
    private String name;
    private int skill;
    
    public Character(){}
    public Character(String name, int pv, int skill)
    {
        this.name = name;
        this.pv = pv;
        this.skill = skill;
    }
    
    public void addWearpon (Wearpon wearpon)
    {
        this.wearpon = wearpon;
    }
    
    public void removeWearpon (Wearpon wearpon)
    {
        this.wearpon = wearpon;
    }
    
    public Wearpon getWearpon ()
    {
        return wearpon;
    }
    
    protected void setPv (int pv)
    {
        this.pv = pv;
    }
    
    protected int getPv ()
    {
        return pv;
    }
    
    protected void setName (String name)
    {
        this.name = name;
    }
    
    protected String getName ()
    {
        return name;
    }
    
    protected void setSkill (int skill)
    {
        this.skill = skill;        
    }
    
    protected int getSkill ()
    {
        return skill;
    }
    
    protected boolean isDead ()
    {
        return pv <= 0;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(name + " PV: " + pv);
    }
    
    protected void reciveDamage (int damage)
    {
        this.pv -= damage;
    }
      
    public void fight (Character enemy)
    {
        boolean turn = true;
        
        do
        {
            if (turn)
            {
                this.atack(enemy);
            }
            else
            {
                enemy.atack(this);
            }
            turn = !turn;
        }
        while (!(this.isDead() || enemy.isDead()));
        
        if (this.isDead())
        {
            System.out.println(enemy.toString() + "HAS WON!!!");
            System.out.println(this.toString() + "HAS LOOSE!!!");
        }
        else
        {
            System.out.println(this.toString() + "HAS WON!!!");
            System.out.println(enemy.toString() + "HAS LOOSE!!!");            
        }
    }        
    
    protected abstract void atack (Character enemy);
}
