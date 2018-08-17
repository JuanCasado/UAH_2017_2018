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
public class Bow extends Wearpon
{
    private int arrows;
    
    public Bow (String name,int damage)
    {
        super(name,damage);
        this.arrows = 10;
    }
    
    protected int getArrows ()
    {
        return this.arrows;
    }
    
    protected void setArrows(int arrows)
    {
        this.arrows = arrows;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(super.toString() + " ARROWS: " + arrows);
    }
    
    @Override
    public int atack ()
    {
        arrows --;
        return super.getDamage();
    }
    
    @Override
    public boolean isUsable ()
    {
        return arrows > 0;
    }
}
