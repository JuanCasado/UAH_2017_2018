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
public class Stick extends Wearpon
{
    private int magic;
    
    public Stick (String name,int damage)
    {
        super(name,damage);
        this.magic = 15;
    }
    
    protected int getMagic ()
    {
        return this.magic;
    }
    
    protected void setMagic (int magic)
    {
        this.magic = magic;
    }
    
    @Override
    public String toString()
    {
        return String.valueOf(super.toString() + " MAGIC: " + magic);
    }
    
    @Override
    public int atack ()
    {
        magic --;
        return super.getDamage();
    }
    
    @Override
    public boolean isUsable ()
    {
        return magic > 0;
    }
}
