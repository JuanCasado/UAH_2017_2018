
package pecl3.screens;


/**
 *
 * @author mr.blissfulgrin
 */
public class VPump extends VBackGroundPanel
{
    VWorker worker;
    VCar car;
    
    public VPump(int x, int y, int id)
    {
        super(200,200,"GASOLINE",x,y);
        initComponents();
        this.ID.setText("Pump: "+id);
    }
    
    public void reset()
    {
        if (car != null)
        {
            car.setVisible(false);
            car.remove(car);
            this.repaint();
        }
        if (worker != null)
        {
            worker.setVisible(false);
            worker.remove(worker);
            this.repaint();
        }
    }
    
    public void newCar (int number)
    {
        car = new VCar();
        car.newCar(number);
        this.add(car);
        this.repaint();
    }
    public void newWorker (int number)
    {
        worker = new VWorker();
        worker.newWorker(number);
        this.add(worker);
        this.repaint();
    }
    public void removeCar(int number)
    {
        if(car.getNumber() == number)
        {
            car.setVisible(false);
            car.remove(car);
        }
        this.repaint();
    }
    public void removeWorker(int number)
    {
        if(worker.getNumber() == number)
        {
            worker.setVisible(false);
            worker.remove(worker);
        }
        this.repaint();
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

        ID = new javax.swing.JLabel();

        ID.setFont(new java.awt.Font("Silom", 1, 20)); // NOI18N
        ID.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ID.setText("Pump: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(ID)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(ID)
                .addContainerGap(241, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    // End of variables declaration//GEN-END:variables
}
