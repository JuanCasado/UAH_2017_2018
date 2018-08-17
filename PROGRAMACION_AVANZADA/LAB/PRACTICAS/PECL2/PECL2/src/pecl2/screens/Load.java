
package pecl2.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class Load extends javax.swing.JFrame {

    public Load() 
    {        
        initComponents();
    }
    
    public void go (String txt)
    {
        this.txt.setText(txt);
        this.setVisible(true);
        this.update(this.getGraphics());
        
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
        this.setVisible(false);
        this.dispose();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt = new javax.swing.JLabel();
        bar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PECL1");

        jPanel1.setLayout(new java.awt.CardLayout(20, 20));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt.setToolTipText("");
        jPanel2.add(txt);
        jPanel2.add(bar);

        jPanel1.add(jPanel2, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txt;
    // End of variables declaration//GEN-END:variables
}
