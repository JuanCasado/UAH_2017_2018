
package pecl2.screens;

/**
 *
 * @author mr.blissfulgrin
 */
public class Generator extends javax.swing.JFrame
{

    public Generator()
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        remote = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("PECL1");
        setMinimumSize(new java.awt.Dimension(500, 100));
        setSize(new java.awt.Dimension(500, 150));
        getContentPane().setLayout(new java.awt.CardLayout(20, 20));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel2.setLayout(new java.awt.CardLayout());

        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(10);
        jSlider1.setValue(99);
        jSlider1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                jSlider1StateChanged(evt);
            }
        });
        jPanel2.add(jSlider1, "card2");

        jPanel1.add(jPanel2);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Generar: 99 paquetes");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(jButton1, gridBagConstraints);

        remote.setText("Conexion Remota");
        remote.setToolTipText("");
        remote.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                remoteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(remote, gridBagConstraints);

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel3.add(jButton2, gridBagConstraints);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        PECL1 p = new PECL1(jSlider1.getValue());
        p.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_jSlider1StateChanged
    {//GEN-HEADEREND:event_jSlider1StateChanged
        jButton1.setText("Generate "+ jSlider1.getValue()+" packets");
    }//GEN-LAST:event_jSlider1StateChanged

    private void remoteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_remoteActionPerformed
    {//GEN-HEADEREND:event_remoteActionPerformed
        RemoteConexion c = new RemoteConexion();
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_remoteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Generator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() ->
        {
            new Generator().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JButton remote;
    // End of variables declaration//GEN-END:variables
}
