/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colores;

/**
 *
 * @author mr.blissfulgrin
 */
public class Paleta extends javax.swing.JFrame {

    /**
     * Creates new form Paleta
     */
    
    private final Paso paso = new Paso();

    private boolean botonPulsado = false;
    
    public Paleta() 
    {
        initComponents();
        draw();
    }
    
    private void draw()
    {
        setVisible(true);
        Colores c = new Colores();
        Pintor p1 = new Pintor(jButton1,c, paso);
        Pintor p2 = new Pintor(jButton2,c, paso);
        Pintor p3 = new Pintor(jButton3,c, paso);
        Pintor p4 = new Pintor(jButton4,c, paso);
        Pintor p5 = new Pintor(jButton5,c, paso);
        Pintor p6 = new Pintor(jButton6,c, paso);
        Pintor p7 = new Pintor(jButton7,c, paso);
        Pintor p8 = new Pintor(jButton8,c, paso);
        Pintor p9 = new Pintor(jButton9,c, paso);
        Pintor p10 = new Pintor(jButton10,c, paso);
        
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
        p8.start();
        p9.start();
        p10.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setLayout(new java.awt.CardLayout(30, 40));

        jPanel2.setLayout(new java.awt.GridLayout(0, 1, 10, 30));

        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jPanel1.add(jButton1);

        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jPanel1.add(jButton2);

        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jPanel1.add(jButton3);

        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jPanel1.add(jButton4);

        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jPanel1.add(jButton5);

        jPanel2.add(jPanel1);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

        jButton10.setBorderPainted(false);
        jButton10.setFocusable(false);
        jPanel3.add(jButton10);

        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jPanel3.add(jButton9);

        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jPanel3.add(jButton8);

        jButton7.setBorderPainted(false);
        jButton7.setFocusable(false);
        jPanel3.add(jButton7);

        jButton6.setBorderPainted(false);
        jButton6.setFocusable(false);
        jPanel3.add(jButton6);

        jPanel2.add(jPanel3);

        jButton11.setText("Parar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11);

        jPanel4.add(jPanel2, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(!botonPulsado) //Si no se ha pulsado
        {                
            botonPulsado=true;              //lo cambiamos a pulsado
            jButton11.setText("Reanudar");  //y cambiamos el texto
            paso.cerrar();                  //Cerramos el paso para que los pintores se detengan
        }
        else //Si ya se había pulsado
        {                          
            botonPulsado=false;             //lo cambiamos
            jButton11.setText("Detener");   //y cambiamos el texto
            paso.abrir();                   //Abrimos el paso para que los pintores sigan trabajando
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Paleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Paleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Paleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Paleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Paleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
