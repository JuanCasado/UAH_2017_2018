/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mr.blissfulgrin
 */
public class SWTest
{
    private final SW sw1;
    private final SW sw2;
    private final SW sw3;
    private final SW sw4;
    
    
    public SWTest()
    {
        this.sw1 = new SW();
        this.sw2 = new SW();
        this.sw3 = new SW();
        this.sw4 = new SW();
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of init method, of class SW.
     */
    @Test
    public void testInit()
    {
        System.out.println("init");
        SW instance = new SW();
        instance.init();
    }

    /**
     * Test of main method, of class SW.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        SW.main(args);
    }

    /**
     * Test of visuliazaFrame method, of class SW.
     */
    @Test
    public void testVisuliazaFrame()
    {
        System.out.println("visuliazaFrame");
        SW instance = new SW();
        instance.visuliazaFrame();
    }

    /**
     * Test of muevePuntos method, of class SW.
     */
    @Test
    public void testMuevePuntos()
    {
        System.out.println("muevePuntos");
        SW instance = new SW();
        instance.muevePuntos();
    }
    
}
