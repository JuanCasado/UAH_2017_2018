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
public abstract class Wearpon 
{
    private String name;
    private int damage;
    
    public Wearpon (String name, int damage)
    {
        this.name = name;
        this.damage = damage;
    }
    
    protected void setName (String name)
    {
        this.name = name;
    }
    
    protected void setDamage (int damage)
    {
        this.damage = damage;
    }
    
    protected String getName ()
    {
        return this.name;
    }
    
    protected int getDamage()
    {
        return this.damage;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(name + ", daño: " + damage);
    }
    
    public abstract int atack ();
    
    public abstract boolean isUsable ();
}
