
package pecl3.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class VCar extends VBackGroundPanel
{
    private static final int W = 115;
    private static final int H = 58;
    private int id;
    
    public VCar()
    {
        super(115,58,"CAR",22,70);
        initComponents();
        number.setText("");
    }
    
    public VCar(int x, int y)
    {
        super(115,58,"CAR",x,y);
        initComponents();
        number.setText("");
    }
    
    public void newCar (int n)
    {
        this.number.setText(String.valueOf(n));
        this.id = n;
    }
    
    public static int getW()
    {
        return W;
    }
    public static int getH()
    {
        return H;
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
    
    public int getID()
    {
        return id;
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
        number.setForeground(new java.awt.Color(255, 255, 255));
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText("NUMBER");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(9, 16, 0, 0);
        add(number, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel number;
    // End of variables declaration//GEN-END:variables
}
