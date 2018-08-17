package CAL3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CAL3 extends javax.swing.JFrame {

    public static String users;
    
    public CAL3(String users) {
        initComponents();
        this.users = users;
        jLabelSaludo.setText("Hola "+users+", introduce la consulta: "); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Aceptar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_SQL = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_Datos = new javax.swing.JTable();
        jLabelSaludo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton_Aceptar.setText("Aceptar");
        jButton_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AceptarActionPerformed(evt);
            }
        });

        jTextArea_SQL.setColumns(20);
        jTextArea_SQL.setRows(5);
        jScrollPane1.setViewportView(jTextArea_SQL);

        jTable_Datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable_Datos);

        jLabelSaludo.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton_Aceptar)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)))
                    .addComponent(jLabelSaludo))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelSaludo)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Aceptar)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AceptarActionPerformed
        // TODO add your handling code here:
        try{
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException cnfe) {
                JOptionPane.showMessageDialog(this, "driver no disponible", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

            Connection c = null;

            int numero=0;
            ResultSet rs = null;
            Statement s = null;

            BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));

            try{

                if(users.compareTo("admin") == 0){
                    c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PECL2", "admin", "admin");
                }
                if(users.compareTo("manger") == 0){
                    c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PECL2","manger", "manger");
                }
                if(users.compareTo("cash") == 0){
                    c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PECL2","cash", "cash");
                }

            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "No se pudo realizar la conexión: " + se.toString(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }

            try {
                s = c.createStatement();
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "problema al crear la consulta", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }

            try {

                String variableSQL = this.jTextArea_SQL.getText().trim() + ";";
                rs =s.executeQuery(variableSQL) ;
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "Excepción al ejecutar consulta: error de sintaxis en el SQL", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }

            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable_Datos.setModel(modelo);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            Object[] etiquetas = new Object[numberOfColumns];
            Object[] BD = new Object[numberOfColumns];

            try {

                for (int i = 0; i < numberOfColumns; i++)
                {
                    // Nuevamente, para ResultSetMetaData la primera columna es la 1.
                    etiquetas[i] = rsmd.getColumnLabel(i + 1);
                }

            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "Error grave al recoger los resultados3", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }
            modelo.setColumnIdentifiers(etiquetas);
            int aux = 0;
            try {
                while (rs.next()) {
                    for (int i = 0; i < numberOfColumns; i++)
                    {
                        //                        Nuevamente, para ResultSetMetaData la primera columna es la 1.
                        BD[i] = rs.getString(i + 1);

                    }
                    modelo.addRow(BD);
                    aux++;
                }

            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "Error grave al recoger los resultados", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            }

            rs.close();
            s.close();
            c.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error grave1", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
        }
    }//GEN-LAST:event_jButton_AceptarActionPerformed

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
            java.util.logging.Logger.getLogger(CAL3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CAL3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CAL3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CAL3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CAL3(users).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Aceptar;
    private javax.swing.JLabel jLabelSaludo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Datos;
    private javax.swing.JTextArea jTextArea_SQL;
    // End of variables declaration//GEN-END:variables
}
