
package slick2dtest;

import java.util.Date;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.gui.TextField;




public class Slick2DTest  extends BasicGame
{
    private Circle circulo1;  
    private Circle circulo2;
    private Circle c;
    private Line l;
    private static AppGameContainer appgc;
    private int x;
    private int e;
    private boolean direction2;
    private long time;
    private boolean direction = true;
    private TextField txt;
    private String str;
    
    public Slick2DTest(String gamename)
    {
            super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException 
    {
        this.circulo1 = new Circle(40, 150, 30);
        this.circulo2 = new Circle(40, 150, 30);
        this.c = new Circle(40,150,10);
        this.l = new Line(40,150,gc.getWidth(),120);
        //this.txt = new TextField(gc,new UnicodeFont(new String(),10,false,false),500,300,0,50);
        //font = new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.ITALIC, 26));
        
        /**
         * 4 en los bordes y 9 por letra
         */
        this.txt = new TextField(gc, gc.getDefaultFont(), 500, 300, 373, 50);
        this.txt.setAcceptingInput(false);
        this.txt.setText("aaa..............................7890!·$%&/()=?¿+<>{}][…–]‚@#¢∞¬÷“”≠§¶™ƒ∂∂ææ∫∑©√¥¥  øµ √∑∑ΩΩ∫åœæ€");
        e=x=40;
        str = "";
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException 
    {
        if ((time+ 20) < (new Date().getTime()))
        {
            if (direction)
                x++;
            else
                x--;
            
            if (direction2)
                e+=2;
            else
                e-=2;
            
            this.circulo1.setX(x);
            this.circulo2.setX((appgc.getWidth()-circulo1.getWidth())-x);
            this.l.set(circulo1.getCenter(), circulo2.getCenter());
            this.c.setX(e);
            
            
            if (circulo1.intersects(circulo2))
            {
                direction = false;
            }
            if (circulo1.getX() <= 0)
            {
                direction = true;
            }
            if (circulo2.getX() <= 0)
            {
                direction = true;
            }
            if (circulo1.getX() >= gc.getWidth()-circulo1.getWidth())
            {
                direction = true;
            }
            if (circulo2.getX() >= gc.getWidth()-circulo2.getWidth())
            {
                direction = true;
            }
            if (c.getX() <= circulo1.getCenterX())
            {
                direction2 = true;
            }
            if (c.getX() >= circulo2.getCenterX()-c.getWidth())
            {
                direction2 = false;
            }
            time = (new Date().getTime());
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setBackground(Color.darkGray);
        g.setColor(Color.lightGray);
        g.draw(this.l);
        g.setColor(Color.white);
        g.fill(this.c);
        g.setColor(Color.black);
        g.fill(this.circulo1);
        g.fill(this.circulo2);
        g.setColor(Color.white);
        txt.render(gc, g);
        txt.setBorderColor(Color.black);
        g.drawString(str, 600, 500);
    }

    public static void main(String[] args) throws SlickException
    {
        appgc = new AppGameContainer(new Slick2DTest("Simple Slick Game"));
        appgc.setDisplayMode(1200, 900, false);
        appgc.setShowFPS(true);
        appgc.start();
    }
    
    @Override
    public void keyPressed(int key, char c) 
    {
        if (key == Input.KEY_ENTER) 
        {
            str = txt.getText();
            txt.setText("");
        }
    }
}