/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productor_consumidor;

/**
 *
 * @author mr.blissfulgrin
 */
public class Productor_Consumidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Buzon buzonX = new Buzon();
        Productor pedro = new Productor("Pedro ",5,buzonX);
        Productor juan = new Productor("Juan ",4,buzonX);
        Productor antonio = new Productor("Antonio ",6,buzonX);
        Productor luis = new Productor("Luis ",7,buzonX);
        Consumidor jose = new Consumidor(22,buzonX);

        pedro.start();
        juan.start();
        antonio.start();
        luis.start();
        jose.start();
    }
    
}
