
package pecl3.screens;

import java.io.IOException;
import java.net.InetAddress;
import javax.swing.ImageIcon;
import pecl3.src.FuelStation;


/**
 *
 * @author mr.blissfulgrin
 */
public class VFuelStation extends javax.swing.JFrame implements FuelStationInterface
{

    boolean general;
    FuelStation fuelStation;
    VPump [] pump;
    VCarBuffer car;
    VWorkerBuffer worker;
    VICON icon;
    
    public VFuelStation(int workers, int cars)
    {
        initComponents();
        
        try
        {
            ip.setText("Connect to: " + InetAddress.getLocalHost().getHostAddress());
        }
        catch (IOException e){}
        
        fuelStation = new FuelStation(this, workers, cars);
        general = false; //Barrier Status
        barrier.setIcon(new ImageIcon("./src/img/BARRERA.png"));
        
        //Generate the BackGround
        VBackGroundPanel backGround = new VBackGroundPanel(this.getWidth(),this.getHeight(),"GASOLINERA",0,0);
        
        //Generate The Visual Pumps
        pump = new VPump [FuelStation.PUMPS];
        int j;
        for (int i = 0; i < pump.length; i++)
        {
            j = (i >= pump.length/2)? 1:0;
            pump[i] = new VPump(115+300*i-1200*j,495+205*j,i+1);
            this.add(pump[i]);
        }

        //Generate the Visual cars
        car = new VCarBuffer(130,305);
        this.add(car);
        
        //Generate the Visual Workers
        worker = new VWorkerBuffer(1215,500);
        for (int i = 0; i < fuelStation.getWorkers(); i++)
        {
            worker.newWorker(i+1);
        }
        this.add(worker);
        
        //Generate the end Icon
        icon = new VICON();
        this.add(icon);
        icon.setVisible(false);
        
        this.add(backGround);
        this.pack();
    }

    @Override
    public synchronized void carArrivesFuelStation(int number)
    {
        car.newCar(number);
        this.repaint();
    }
    @Override
    public synchronized void carLeavesFuelStationQueue(int number)
    {
        car.removeCar(number);
        this.repaint();
    }
    @Override
    public synchronized void carReachesPump (int carNumber, int pumpNumber)
    {
        pump[pumpNumber-1].newCar(carNumber);
        this.repaint();
    }
    @Override
    public synchronized void workerStartFueling (int workerNumber, int pumpNumber)
    {
        worker.removeWorker(workerNumber);
        pump[pumpNumber-1].newWorker(workerNumber);
        this.repaint();
    }
    @Override
    public synchronized void workerEndsFueling (int workerNumber, int pumpNumber)
    {
        pump[pumpNumber-1].removeWorker(workerNumber);
        worker.newWorker(workerNumber);
        this.repaint();
    }
    @Override
    public synchronized void carLeavesPump (int carNumber, int pumpNumber)
    {
        pump[pumpNumber-1].removeCar(carNumber);
        this.repaint();
    }
    @Override
    public synchronized void endSimulation ()
    {
        icon.setVisible(true);
        this.repaint();
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

        jLabel1 = new javax.swing.JLabel();
        barrier = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        timeSlider = new javax.swing.JSlider();
        timeTxt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        ip = new javax.swing.JLabel();

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SING.png"))); // NOI18N

        barrier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/BARRERA.png"))); // NOI18N
        barrier.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                barrierMouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        timeSlider.setMaximum(10000);
        timeSlider.setValue(5500);
        timeSlider.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                timeSliderStateChanged(evt);
            }
        });

        timeTxt.setFont(new java.awt.Font("Phosphate", 1, 48)); // NOI18N
        timeTxt.setForeground(new java.awt.Color(0, 0, 0));
        timeTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeTxt.setText("5500");

        txt.setEditable(false);
        txt.setColumns(20);
        txt.setFont(new java.awt.Font("Lucida Grande", 0, 9)); // NOI18N
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        ip.setFont(new java.awt.Font("Tahoma", 1, 26)); // NOI18N
        ip.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ip.setText("Conect to: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(timeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 910, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(425, 425, 425)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(barrier))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ip)))
                        .addContainerGap(88, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(timeTxt))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addComponent(timeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ip)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(427, 427, 427))
                            .addComponent(barrier))))
                .addContainerGap(479, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void barrierMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_barrierMouseClicked
    {//GEN-HEADEREND:event_barrierMouseClicked
        general = !general;
        if (general)
        {          
            barrier.setIcon(new ImageIcon("./src/img/BARRERA_ALTA.png"));
            fuelStation.resumeSimulation();
        }
        else
        {
            barrier.setIcon(new ImageIcon("./src/img/BARRERA.png"));
            fuelStation.pauseSimulation();
        }
        this.repaint();
    }//GEN-LAST:event_barrierMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        fuelStation.start();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing
        VLoad l = new VLoad();
        l.go("SAVING LOG...");
        fuelStation.end();
        VInicio i = new VInicio ();
        i.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void timeSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_timeSliderStateChanged
    {//GEN-HEADEREND:event_timeSliderStateChanged
        fuelStation.setTime(timeSlider.getValue());
        timeTxt.setText(String.valueOf(timeSlider.getValue()));
        this.repaint();
    }//GEN-LAST:event_timeSliderStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barrier;
    private javax.swing.JLabel ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider timeSlider;
    private javax.swing.JLabel timeTxt;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables

    @Override
    public synchronized void barrera(boolean state)
    {
        general = state;
        if (state)
        {          
            barrier.setIcon(new ImageIcon("./src/img/BARRERA_ALTA.png"));
        }
        else
        {
            barrier.setIcon(new ImageIcon("./src/img/BARRERA.png"));
        }
        this.repaint();
    }

    @Override
    public synchronized void reset()
    {
        car.reset();
        worker.reset();
        for (VPump p : pump)
        {
            p.reset();
        }
        this.repaint();
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
            car.newCar(Integer.parseInt(data[index].trim()));
        }
        times2 = Integer.parseInt(data[index].trim());
        index++;
        gap2 = index;
        for (; index < times2+gap2; index++)
        {
            worker.newWorker(Integer.parseInt(data[index].trim()));
        }
        for (VPump p : pump)
        {
            if (!data[index].trim().equals("N"))
            {
                p.newCar(Integer.parseInt(data[index].trim()));
            }
            index++;
            if (!data[index].trim().equals("N"))
            {
                p.newWorker(Integer.parseInt(data[index].trim()));
            }
            index++;
        }

        this.repaint();
    }
}
