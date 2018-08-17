/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg_fantasia_medieval.armas;

/**
 *
 * @author mr.blissfulgrin
 */
public class Sword extends Wearpon
{
    private int strength;
    
    public Sword (String name,int damage)
    {
        super(name,damage);
        this.strength = 20;
    }
    
    protected int getStrength ()
    {
        return this.strength;
    }
    
    protected void setStrength (int strength)
    {
        this.strength = strength;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(super.toString() + " STRENGTH: " + strength);
    }
    
    @Override
    public int atack ()
    {
        strength --;
        return super.getDamage();
    }
    
    @Override
    public boolean isUsable ()
    {
        return strength > 0;
    }
}
