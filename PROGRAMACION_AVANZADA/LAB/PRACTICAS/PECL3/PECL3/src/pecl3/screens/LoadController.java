
package pecl3.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class LoadController extends Thread
{

    private final VLoad vLoad;
    private final javax.swing.JProgressBar bar;
    
    public LoadController (VLoad vLoad, javax.swing.JProgressBar bar)
    {
        this.vLoad = vLoad;
        this.bar = bar;
    }
    @Override
    public void run()
    {
        for(int x = 0; x<100; x++)
        {
            try 
            {
                Thread.sleep(5);
            } 
            catch (InterruptedException i) 
            {
                System.out.println("ERROR LOADING!!! " + i.toString());
            }
            bar.setValue(x);
            bar.setString(x + "%");
            bar.update(bar.getGraphics());
        }
        vLoad.setVisible(false);
        vLoad.dispose();
    }

}
