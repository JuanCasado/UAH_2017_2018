
package pecl3.screens;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import javax.swing.JTextField;
import pecl3.src.FuelStation;

/**
 *
 * @author mr.blissfulgrin
 */
public class SFuelStation extends javax.swing.JFrame implements FuelStationInterface
{
    private final FuelStation fuelStation;
    private final JTextField [] worker;
    private final JTextField [] car;
    private final ArrayList <String> buffer;
    private final ArrayList <String> wbuffer;
    
    public SFuelStation(int workers, int cars)
    {
        initComponents();
        fuelStation = new FuelStation(this, workers, cars);
        barier.setSelected(true);
        
        try
        {
            ip.setText("Connect to: " + InetAddress.getLocalHost().getHostAddress());
        }
        catch (IOException e){}
        
        worker = new JTextField[8];
        worker[0] = worker0;
        worker[1] = worker1;
        worker[2] = worker2;
        worker[3] = worker3;
        worker[4] = worker4;
        worker[5] = worker5;
        worker[6] = worker6;
        worker[7] = worker7;
        
        wbuffer = new ArrayList <>();
        workerTxt.setText("");
        for (int i = 0; i < fuelStation.WORKERS; i++)
        {
            wbuffer.add("Worker "+(i+1));
        }
        wbuffer.forEach((s) ->
        {
            workerTxt.setText(workerTxt.getText() + s+"\n");
        });
        
        car = new JTextField [8];
        car [0] = car0;
        car [1] = car1;
        car [2] = car2;
        car [3] = car3;
        car [4] = car4;
        car [5] = car5;
        car [6] = car6;
        car [7] = car7;
        buffer = new ArrayList <>();
        
        for (JTextField w : worker)
        {
            w.setText("");
        }
        for (JTextField c : car)
        {
            c.setText("");
        }
        buff.setText("");
    }
    
    
    @Override
    public synchronized void carArrivesFuelStation(int number)
    {
        buffer.add(String.valueOf(number));
        buff.setText("");
        for (int i = buffer.size()-1; i >= 0; i--)
        {
            buff.setText(buff.getText() + buffer.get(i) + " | ");
        }
    }
    @Override
    public synchronized void carLeavesFuelStationQueue(int number)
    {
        buffer.remove(String.valueOf(number));
        buff.setText("");
        for (int i = buffer.size()-1; i >= 0; i--)
        {
            buff.setText(buff.getText() + buffer.get(i)+" | ");
        }
    }
    @Override
    public synchronized void carReachesPump(int carNumber, int pumpNumber)
    {
        car[pumpNumber-1].setText("Car "+carNumber);
    }
    @Override
    public synchronized void workerStartFueling(int workerNumber, int pumpNumber)
    {
        worker[pumpNumber-1].setText("Worker "+workerNumber);
        wbuffer.remove("Worker "+workerNumber);
        workerTxt.setText("");
        wbuffer.forEach((s) ->
        {
            workerTxt.setText(workerTxt.getText() + s+"\n");
        });
    }
    @Override
    public synchronized void workerEndsFueling(int workerNumber, int pumpNumber)
    {
        worker[pumpNumber-1].setText("");
        wbuffer.add("Worker "+workerNumber);
        workerTxt.setText("");
        wbuffer.forEach((s) ->
        {
            workerTxt.setText(workerTxt.getText() + s+"\n");
        });
    }
    @Override
    public synchronized void carLeavesPump(int carNumber, int pumpNumber)
    {
        car[pumpNumber-1].setText("");
    }
    @Override
    public synchronized void endSimulation()
    {
        buff.setText("SIMULATION ENDED, SAVING DATA");
    }
    @Override
    public synchronized void write(String texto)
    {
        txt.setText(texto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        car0 = new javax.swing.JTextField();
        worker0 = new javax.swing.JTextField();
        timeSlider = new javax.swing.JSlider();
        timeTxt = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        car1 = new javax.swing.JTextField();
        worker1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        car2 = new javax.swing.JTextField();
        worker2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        car3 = new javax.swing.JTextField();
        worker3 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        car4 = new javax.swing.JTextField();
        worker4 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        car5 = new javax.swing.JTextField();
        worker5 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        car6 = new javax.swing.JTextField();
        worker6 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        car7 = new javax.swing.JTextField();
        worker7 = new javax.swing.JTextField();
        buff = new javax.swing.JTextField();
        barier = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        workerTxt = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        ip = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FuelStation");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pump 1");
        jLabel1.setIconTextGap(20);
        jPanel2.add(jLabel1);

        car0.setEditable(false);
        car0.setText("car0");
        car0.setMaximumSize(new java.awt.Dimension(41, 24));
        car0.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel2.add(car0);

        worker0.setEditable(false);
        worker0.setText("worker0");
        worker0.setMaximumSize(new java.awt.Dimension(41, 24));
        worker0.setMinimumSize(new java.awt.Dimension(41, 24));
        worker0.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel2.add(worker0);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 53, 49, 81);
        jPanel1.add(jPanel2, gridBagConstraints);

        timeSlider.setMaximum(10000);
        timeSlider.setValue(5500);
        timeSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                timeSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 154;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 43, 10, 43);
        jPanel1.add(timeSlider, gridBagConstraints);

        timeTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeTxt.setText("5500");
        timeTxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        timeTxt.setIconTextGap(10);
        timeTxt.setMaximumSize(new java.awt.Dimension(41, 24));
        timeTxt.setMinimumSize(new java.awt.Dimension(41, 24));
        timeTxt.setPreferredSize(new java.awt.Dimension(41, 24));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 13);
        jPanel1.add(timeTxt, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridLayout(0, 1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pump 2");
        jPanel3.add(jLabel3);

        car1.setEditable(false);
        car1.setText("car1");
        car1.setMaximumSize(new java.awt.Dimension(41, 24));
        car1.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel3.add(car1);

        worker1.setEditable(false);
        worker1.setText("worker1");
        worker1.setMaximumSize(new java.awt.Dimension(41, 24));
        worker1.setMinimumSize(new java.awt.Dimension(41, 24));
        worker1.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel3.add(worker1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 81);
        jPanel1.add(jPanel3, gridBagConstraints);

        jPanel4.setLayout(new java.awt.GridLayout(0, 1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Pump 3");
        jPanel4.add(jLabel4);

        car2.setEditable(false);
        car2.setText("car2");
        car2.setMaximumSize(new java.awt.Dimension(41, 24));
        car2.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel4.add(car2);

        worker2.setEditable(false);
        worker2.setText("worker2");
        worker2.setMaximumSize(new java.awt.Dimension(41, 24));
        worker2.setMinimumSize(new java.awt.Dimension(41, 24));
        worker2.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel4.add(worker2);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 81);
        jPanel1.add(jPanel4, gridBagConstraints);

        jPanel5.setLayout(new java.awt.GridLayout(0, 1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Pump 4");
        jPanel5.add(jLabel5);

        car3.setEditable(false);
        car3.setText("car3");
        car3.setMaximumSize(new java.awt.Dimension(41, 24));
        car3.setMinimumSize(new java.awt.Dimension(41, 24));
        car3.setRequestFocusEnabled(false);
        jPanel5.add(car3);

        worker3.setEditable(false);
        worker3.setText("worker3");
        worker3.setMaximumSize(new java.awt.Dimension(41, 24));
        worker3.setMinimumSize(new java.awt.Dimension(41, 24));
        worker3.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel5.add(worker3);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 52);
        jPanel1.add(jPanel5, gridBagConstraints);

        jPanel6.setLayout(new java.awt.GridLayout(0, 1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Pump 5");
        jPanel6.add(jLabel6);

        car4.setEditable(false);
        car4.setText("car4");
        car4.setMaximumSize(new java.awt.Dimension(41, 24));
        car4.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel6.add(car4);

        worker4.setEditable(false);
        worker4.setText("worker4");
        worker4.setMaximumSize(new java.awt.Dimension(41, 24));
        worker4.setMinimumSize(new java.awt.Dimension(41, 24));
        worker4.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel6.add(worker4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 53, 49, 81);
        jPanel1.add(jPanel6, gridBagConstraints);

        jPanel7.setLayout(new java.awt.GridLayout(0, 1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Pump 6");
        jPanel7.add(jLabel7);

        car5.setEditable(false);
        car5.setText("car5");
        car5.setMaximumSize(new java.awt.Dimension(41, 24));
        car5.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel7.add(car5);

        worker5.setEditable(false);
        worker5.setText("worker5");
        worker5.setMaximumSize(new java.awt.Dimension(41, 24));
        worker5.setMinimumSize(new java.awt.Dimension(41, 24));
        worker5.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel7.add(worker5);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 81);
        jPanel1.add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.GridLayout(0, 1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Pump 7");
        jPanel8.add(jLabel8);

        car6.setEditable(false);
        car6.setText("car6");
        car6.setMaximumSize(new java.awt.Dimension(41, 24));
        car6.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel8.add(car6);

        worker6.setEditable(false);
        worker6.setText("worker6");
        jPanel8.add(worker6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 68;
        gridBagConstraints.ipady = 57;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 81);
        jPanel1.add(jPanel8, gridBagConstraints);

        jPanel9.setLayout(new java.awt.GridLayout(0, 1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Pump 8");
        jPanel9.add(jLabel9);

        car7.setEditable(false);
        car7.setText("car7");
        car7.setMaximumSize(new java.awt.Dimension(41, 24));
        car7.setMinimumSize(new java.awt.Dimension(41, 24));
        jPanel9.add(car7);

        worker7.setEditable(false);
        worker7.setText("worker7");
        worker7.setMaximumSize(new java.awt.Dimension(41, 24));
        worker7.setMinimumSize(new java.awt.Dimension(41, 24));
        worker7.setPreferredSize(new java.awt.Dimension(41, 24));
        jPanel9.add(worker7);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 56;
        gridBagConstraints.ipady = 45;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(49, 81, 49, 52);
        jPanel1.add(jPanel9, gridBagConstraints);

        buff.setEditable(false);
        buff.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        buff.setText("buff");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(42, 19, 12, 18);
        jPanel1.add(buff, gridBagConstraints);

        barier.setText("OFF");
        barier.setMaximumSize(new java.awt.Dimension(41, 24));
        barier.setMinimumSize(new java.awt.Dimension(41, 24));
        barier.setPreferredSize(new java.awt.Dimension(41, 24));
        barier.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                barierActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 7, 7, 12);
        jPanel1.add(barier, gridBagConstraints);

        workerTxt.setEditable(false);
        workerTxt.setColumns(20);
        workerTxt.setRows(5);
        jScrollPane1.setViewportView(workerTxt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(109, 26, 109, 26);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SING.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.insets = new java.awt.Insets(41, 46, 41, 46);
        jPanel1.add(jLabel2, gridBagConstraints);

        ip.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        ip.setText("Conect to: 255.255.255.255");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        jPanel1.add(ip, gridBagConstraints);

        txt.setColumns(20);
        txt.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        txt.setRows(5);
        jScrollPane3.setViewportView(txt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 4.2;
        gridBagConstraints.insets = new java.awt.Insets(15, 25, 19, 7);
        jPanel1.add(jScrollPane3, gridBagConstraints);

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        fuelStation.end();
        VInicio i = new VInicio();
        i.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void timeSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_timeSliderStateChanged
    {//GEN-HEADEREND:event_timeSliderStateChanged
        timeTxt.setText(String.valueOf(timeSlider.getValue()));
        fuelStation.setTime(timeSlider.getValue());
    }//GEN-LAST:event_timeSliderStateChanged

    private void barierActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_barierActionPerformed
    {//GEN-HEADEREND:event_barierActionPerformed
        if (barier.isSelected())
        {
            barier.setText("OFF");
            fuelStation.pauseSimulation();
        }
        else
        {
            barier.setText("ON");
            fuelStation.resumeSimulation();
        }
    }//GEN-LAST:event_barierActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
       fuelStation.start();
    }//GEN-LAST:event_formWindowOpened
    @Override
    public synchronized void barrera(boolean state)
    {
        if (state)
        {
            barier.setText("ON");
        }
        else
        {
            barier.setText("OFF");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton barier;
    private javax.swing.JTextField buff;
    private javax.swing.JTextField car0;
    private javax.swing.JTextField car1;
    private javax.swing.JTextField car2;
    private javax.swing.JTextField car3;
    private javax.swing.JTextField car4;
    private javax.swing.JTextField car5;
    private javax.swing.JTextField car6;
    private javax.swing.JTextField car7;
    private javax.swing.JLabel ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider timeSlider;
    private javax.swing.JLabel timeTxt;
    private javax.swing.JTextArea txt;
    private javax.swing.JTextField worker0;
    private javax.swing.JTextField worker1;
    private javax.swing.JTextField worker2;
    private javax.swing.JTextField worker3;
    private javax.swing.JTextField worker4;
    private javax.swing.JTextField worker5;
    private javax.swing.JTextField worker6;
    private javax.swing.JTextField worker7;
    private javax.swing.JTextArea workerTxt;
    // End of variables declaration//GEN-END:variables

    @Override
    public synchronized void reset()
    {
        for (int x = 0; x < worker.length; x++)
        {
            worker[x].setText("");
            car[x].setText("");
        }
        buff.setText("");
        workerTxt.setText("");
        buffer.clear();
        wbuffer.clear();
    }

    @Override
    public synchronized void setData(String[] data)
    {
        int index = 1;
        int times1;
        int times2;
        int gap1;
        int gap2;
        barrera(data[0].trim().equals("1"));
        times1 = Integer.parseInt(data[index].trim());
        index++;
        gap1 = index;
        for (; index < times1+gap1; index++)
        {
            carArrivesFuelStation(Integer.parseInt(data[index].trim()));
        }
        times2 = Integer.parseInt(data[index].trim());
        index++;
        gap2 = index;
        for (; index < times2+gap2; index++)
        {
            wbuffer.add("Worker "+Integer.parseInt(data[index].trim()));
            workerTxt.setText("");
        }
        wbuffer.forEach((s) ->
        {
            workerTxt.setText(workerTxt.getText() + s+"\n");
        });
        for (int x = 0; x < worker.length; x++)
        {
            if (!data[index].trim().equals("N"))
            {
                car[x].setText("Car: "+data[index].trim());
            }
            index++;
            if (!data[index].trim().equals("N"))
            {
                worker[x].setText("Worker: "+data[index].trim());
            }
            index++;
        }
    }
}
