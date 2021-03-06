
package pecl3.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class VWorker extends VBackGroundPanel
{

    public VWorker()
    {
        super(60,60,"WORKER",50,133);
        initComponents();
        number.setText("");
    }
    
    public VWorker(int x, int y)
    {
        super(60,60,"WORKER",x,y);
        initComponents();
        number.setText("");
    }
    
    public void newWorker (int n)
    {
        this.number.setText(String.valueOf(n));
    }
    
    public int getNumber()
    {
        try
        {
            return Integer.parseInt(number.getText());
        }
        catch(NumberFormatException n)
        {
            return 0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        number = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        number.setFont(new java.awt.Font("Silom", 1, 22)); // NOI18N
        number.setForeground(new java.awt.Color(51, 0, 51));
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("NUMBER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 14, 0);
        add(number, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel number;
    // End of variables declaration//GEN-END:variables
}
