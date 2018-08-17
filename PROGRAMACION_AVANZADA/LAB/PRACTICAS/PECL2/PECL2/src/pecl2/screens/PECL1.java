
package pecl2.screens;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import pecl2.classes.Stop;
import pecl2.classes.Buffer;
import pecl2.classes.Producer;
import pecl2.classes.Consumer;
import javax.swing.JProgressBar;
import pecl2.classes.Server;


/**
 *
 * @author mr.blissfulgrin
 */
public class PECL1 extends javax.swing.JFrame {

    private final Producer [] producer;
    private final Consumer [] consumer;
    private Buffer buffer;
    private final Stop [] stop;
    private final Server server;
    private final Semaphore semaphrore;
    private final AtomicBoolean finishd;
    
    public PECL1(int p) 
    {
        new Load().go("Starting...");
        initComponents();
        
        int numerOfPackets = p;
        String [] producer_id = {"A","B","C","D","E"};
        String [] consumer_id = {"Luis","Juan","Maria"};
        
        int packetsExpected = numerOfPackets*producer_id.length/consumer_id.length;
        
        producer0Txt.setText("Producer: "+producer_id[0]);
        producer1Txt.setText("Producer: "+producer_id[1]);
        producer2Txt.setText("Producer: "+producer_id[2]);
        producer3Txt.setText("Producer: "+producer_id[3]);
        producer4Txt.setText("Producer: "+producer_id[4]);
        consumer0Txt.setText("Consumer: "+consumer_id[0]);
        consumer1Txt.setText("Consumer: "+consumer_id[1]);
        consumer2Txt.setText("Consumer: "+consumer_id[2]);
        
        producer0Bar.setMaximum(numerOfPackets);
        producer1Bar.setMaximum(numerOfPackets);
        producer2Bar.setMaximum(numerOfPackets);
        producer3Bar.setMaximum(numerOfPackets);
        producer4Bar.setMaximum(numerOfPackets);
        consumer0Bar.setMaximum(packetsExpected);
        consumer1Bar.setMaximum(packetsExpected);
        consumer2Bar.setMaximum(packetsExpected);
        
        producer0Chk.setSelected(true);
        producer1Chk.setSelected(true);
        producer2Chk.setSelected(true);
        producer3Chk.setSelected(true);
        producer4Chk.setSelected(true);
        consumer0Chk.setSelected(true);
        consumer1Chk.setSelected(true);
        consumer2Chk.setSelected(true);

        producer0Chk.setText("Random Speed");
        producer1Chk.setText("Random Speed");
        producer2Chk.setText("Random Speed");
        producer3Chk.setText("Random Speed");
        producer4Chk.setText("Random Speed");
        consumer0Chk.setText("Random Speed");
        consumer1Chk.setText("Random Speed");
        consumer2Chk.setText("Random Speed");
        
        producer0Btt.setText("Stop");
        producer1Btt.setText("Stop");
        producer2Btt.setText("Stop");
        producer3Btt.setText("Stop");
        producer4Btt.setText("Stop");
        producerStop.setText("Stop");
        consumerStop.setText("Stop");
        consumer0Btt.setText("Stop");
        consumer1Btt.setText("Stop");
        consumer2Btt.setText("Stop");
        
        JProgressBar [] bp = {producer0Bar,producer1Bar,producer2Bar,producer3Bar,producer4Bar};
        JProgressBar [] bc = {consumer0Bar,consumer1Bar,consumer2Bar};
        
        producer = new Producer[producer_id.length];
        consumer = new Consumer[consumer_id.length];
        stop = new Stop [producer_id.length+consumer_id.length];
        for (int i = 0; i < stop.length; i++)
        {
            stop[i] = new Stop(buffer);
        }

        buffer = new Buffer(40, producer.length*numerOfPackets, bufferTxt, bufferBar, bufferDerivate,txt,this);
        
        //CREAR
        this.server = new Server (ip, this, txt);
        for (int x = 0; x<consumer.length; x++)
        {
            consumer[x] = new Consumer(consumer_id[x],buffer,packetsExpected,bc[x],txt,stop[x]);
        }
        for (int x = 0; x<producer.length; x++)
        {
            producer[x] = new Producer(producer_id[x],buffer,numerOfPackets,bp[x],txt,stop[x+consumer.length]);
        }
        
        String [] data = new String [producer_id.length+consumer_id.length+2];
        data[0] = "Log";
        data[1] = "Server";
        
        for (int i = 2; i < producer_id.length+2; i++)
        {
            data[i] = producer[i-2].toString();
        }
        for (int i = producer_id.length+2; i < producer_id.length+2+consumer_id.length; i++)
        {
            data[i] = consumer[(i-2)-producer_id.length].toString();
        }
        
        list.setListData(data);
        stopPrinting();
        buffer.beguinPrinting();
        
        this.semaphrore = new Semaphore (1);
        
        this.finishd = new AtomicBoolean(false);
        run();
    }
    
    private Boolean bttProducer ()
    {
        return producer0Btt.isSelected() && producer1Btt.isSelected() && producer2Btt.isSelected() && producer3Btt.isSelected() && producer4Btt.isSelected();
    }
    
    private Boolean bttConsumer ()
    {
        return consumer0Btt.isSelected() && consumer1Btt.isSelected() && consumer2Btt.isSelected();
    }
    
    private void run()
    {        
        //INICIAR
        for (Consumer c : consumer) 
        {
            c.start();
        }
        for (Producer p : producer) 
        {
            p.start();
        }
        server.start();
    }
    
    private void stopPrinting ()
    {
        for (Consumer c : consumer)
        {
            c.stopPrinting();
        }
        for (Producer p : producer)
        {
            p.stopPrinting();
        }
        buffer.stopPrinting();
    }
    
    public synchronized boolean [] getButtonState ()
    {
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        boolean [] state = new boolean [11];
        state [0] = producer0Btt.isSelected();
        state [1] = producer1Btt.isSelected();
        state [2] = producer2Btt.isSelected();
        state [3] = producer3Btt.isSelected();
        state [4] = producer4Btt.isSelected();
        state [5] = consumer0Btt.isSelected();
        state [6] = consumer1Btt.isSelected();
        state [7] = consumer2Btt.isSelected();
        state [8] = producerStop.isSelected();
        state [9] = consumerStop.isSelected();
        state[10] = finishd.get();      
        semaphrore.release(1);
        
        return state;
    }
    
    public synchronized void setButtonState (boolean [] state)
    {
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        producer0Btt.setSelected(state[10]?state[0]:state[8]);
        producer1Btt.setSelected(state[10]?state[1]:state[8]);
        producer2Btt.setSelected(state[10]?state[2]:state[8]);
        producer3Btt.setSelected(state[10]?state[3]:state[8]);
        producer4Btt.setSelected(state[10]?state[4]:state[8]);
        consumer0Btt.setSelected(state[10]?state[5]:state[9]);
        consumer1Btt.setSelected(state[10]?state[6]:state[9]);
        consumer2Btt.setSelected(state[10]?state[7]:state[9]);
        producerStop.setSelected(state[10]?(state[0]&&state[1]&&state[2]&&state[3]&&state[4]):state[8]);
        consumerStop.setSelected(state[10]?(state[5]&&state[6]&&state[7]):state[9]);
        
        if(state[10]?state[0]:state[8])
        {
            stop[consumer.length].close();
        }
        else
        {
            stop[consumer.length].open();
        }
        if(state[10]?state[1]:state[8])
        {
            stop[consumer.length+1].close();
        }
        else
        {
            stop[consumer.length+1].open();
        }
        if(state[10]?state[2]:state[8])
        {
            stop[consumer.length+2].close();
        }
        else
        {
            stop[consumer.length+2].open();
        }
        if(state[10]?state[3]:state[8])
        {
            stop[consumer.length+3].close();
        }
        else
        {
            stop[consumer.length+3].open();
        }
        if(state[10]?state[4]:state[8])
        {
            stop[consumer.length+4].close();
        }
        else
        {
            stop[consumer.length+4].open();
        }
        if(state[10]?state[5]:state[9])
        {
            stop[0].close();
        }
        else
        {
            stop[0].open();
        }
        if(state[10]?state[6]:state[9])
        {
            stop[1].close();
        }
        else
        {
            stop[1].open();
        }
        if(state[10]?state[7]:state[9])
        {
            stop[2].close();
        }
        else
        {
            stop[2].open();
        }
        
        semaphrore.release(1);
        
        server.send();
    }
    
    public void finish()
    {
        finishd.set(true);
        server.send();
        //server.end();                 
        /*
        Terminaría la ejecución del servidor (impidiendo nuevas conexiones)
        Ahora notifica a los clientes que la ejecución ha finalizado pero los mantiene activos cuando la 
        simulación finaliza de modo que siguen pudiendo modificar el estado de los botones
        */
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        producer0Txt = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        producer0Sld = new javax.swing.JSlider();
        producer0Bar = new javax.swing.JProgressBar();
        jPanel18 = new javax.swing.JPanel();
        producer0Btt = new javax.swing.JToggleButton();
        producer0Chk = new javax.swing.JCheckBox();
        jPanel19 = new javax.swing.JPanel();
        producer1Txt = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        producer1Sld = new javax.swing.JSlider();
        producer1Bar = new javax.swing.JProgressBar();
        jPanel22 = new javax.swing.JPanel();
        producer1Btt = new javax.swing.JToggleButton();
        producer1Chk = new javax.swing.JCheckBox();
        jPanel23 = new javax.swing.JPanel();
        producer2Txt = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        producer2Sld = new javax.swing.JSlider();
        producer2Bar = new javax.swing.JProgressBar();
        jPanel26 = new javax.swing.JPanel();
        producer2Btt = new javax.swing.JToggleButton();
        producer2Chk = new javax.swing.JCheckBox();
        jPanel43 = new javax.swing.JPanel();
        producer3Txt = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        producer3Sld = new javax.swing.JSlider();
        producer3Bar = new javax.swing.JProgressBar();
        jPanel46 = new javax.swing.JPanel();
        producer3Btt = new javax.swing.JToggleButton();
        producer3Chk = new javax.swing.JCheckBox();
        jPanel47 = new javax.swing.JPanel();
        producer4Txt = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        producer4Sld = new javax.swing.JSlider();
        producer4Bar = new javax.swing.JProgressBar();
        jPanel50 = new javax.swing.JPanel();
        producer4Btt = new javax.swing.JToggleButton();
        producer4Chk = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        producerStop = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        bufferBar = new javax.swing.JProgressBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        bufferTxt = new javax.swing.JTextArea();
        bufferDerivate = new javax.swing.JProgressBar();
        jPanel9 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        consumer0Txt = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        consumer0Sld = new javax.swing.JSlider();
        consumer0Bar = new javax.swing.JProgressBar();
        jPanel33 = new javax.swing.JPanel();
        consumer0Btt = new javax.swing.JToggleButton();
        consumer0Chk = new javax.swing.JCheckBox();
        jPanel34 = new javax.swing.JPanel();
        consumer1Txt = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        consumer1Sld = new javax.swing.JSlider();
        consumer1Bar = new javax.swing.JProgressBar();
        jPanel37 = new javax.swing.JPanel();
        consumer1Btt = new javax.swing.JToggleButton();
        consumer1Chk = new javax.swing.JCheckBox();
        jPanel38 = new javax.swing.JPanel();
        consumer2Txt = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        consumer2Sld = new javax.swing.JSlider();
        consumer2Bar = new javax.swing.JProgressBar();
        jPanel41 = new javax.swing.JPanel();
        consumer2Btt = new javax.swing.JToggleButton();
        consumer2Chk = new javax.swing.JCheckBox();
        jPanel42 = new javax.swing.JPanel();
        consumerStop = new javax.swing.JToggleButton();
        ip = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout(10, 10));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel4.setLayout(new java.awt.GridBagLayout());

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jPanel10.setLayout(new java.awt.CardLayout(5, 5));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.Y_AXIS));

        producer0Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producer0Txt.setText("producer0Txt");
        jPanel15.add(producer0Txt);

        jPanel17.setLayout(new java.awt.CardLayout());

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        producer0Sld.setMaximum(-100);
        producer0Sld.setMinimum(-2000);
        producer0Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        producer0Sld.setValue(-1050);
        producer0Sld.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        producer0Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                producer0SldStateChanged(evt);
            }
        });
        jPanel16.add(producer0Sld);

        producer0Bar.setOrientation(1);
        producer0Bar.setStringPainted(true);
        jPanel16.add(producer0Bar);

        jPanel17.add(jPanel16, "card2");

        jPanel15.add(jPanel17);

        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.Y_AXIS));

        producer0Btt.setText("producer0Btt");
        producer0Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer0BttActionPerformed(evt);
            }
        });
        jPanel18.add(producer0Btt);

        producer0Chk.setText("producer0Chk");
        producer0Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer0ChkActionPerformed(evt);
            }
        });
        jPanel18.add(producer0Chk);

        jPanel15.add(jPanel18);

        jPanel6.add(jPanel15);

        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.Y_AXIS));

        producer1Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producer1Txt.setText("producer1Txt");
        jPanel19.add(producer1Txt);

        jPanel20.setLayout(new java.awt.CardLayout());

        jPanel21.setLayout(new java.awt.GridLayout(1, 0));

        producer1Sld.setMaximum(-100);
        producer1Sld.setMinimum(-2000);
        producer1Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        producer1Sld.setValue(-1050);
        producer1Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                producer1SldStateChanged(evt);
            }
        });
        jPanel21.add(producer1Sld);

        producer1Bar.setOrientation(1);
        producer1Bar.setStringPainted(true);
        jPanel21.add(producer1Bar);

        jPanel20.add(jPanel21, "card2");

        jPanel19.add(jPanel20);

        jPanel22.setLayout(new javax.swing.BoxLayout(jPanel22, javax.swing.BoxLayout.Y_AXIS));

        producer1Btt.setText("producer1Btt");
        producer1Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer1BttActionPerformed(evt);
            }
        });
        jPanel22.add(producer1Btt);

        producer1Chk.setText("producer1Chk");
        producer1Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer1ChkActionPerformed(evt);
            }
        });
        jPanel22.add(producer1Chk);

        jPanel19.add(jPanel22);

        jPanel6.add(jPanel19);

        jPanel23.setLayout(new javax.swing.BoxLayout(jPanel23, javax.swing.BoxLayout.Y_AXIS));

        producer2Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producer2Txt.setText("producer2Txt");
        jPanel23.add(producer2Txt);

        jPanel24.setLayout(new java.awt.CardLayout());

        jPanel25.setLayout(new java.awt.GridLayout(1, 0));

        producer2Sld.setMaximum(-100);
        producer2Sld.setMinimum(-2000);
        producer2Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        producer2Sld.setValue(-1050);
        producer2Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                producer2SldStateChanged(evt);
            }
        });
        jPanel25.add(producer2Sld);

        producer2Bar.setOrientation(1);
        producer2Bar.setStringPainted(true);
        jPanel25.add(producer2Bar);

        jPanel24.add(jPanel25, "card2");

        jPanel23.add(jPanel24);

        jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.Y_AXIS));

        producer2Btt.setText("producer2Btt");
        producer2Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer2BttActionPerformed(evt);
            }
        });
        jPanel26.add(producer2Btt);

        producer2Chk.setText("producer2Chk");
        producer2Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer2ChkActionPerformed(evt);
            }
        });
        jPanel26.add(producer2Chk);

        jPanel23.add(jPanel26);

        jPanel6.add(jPanel23);

        jPanel43.setLayout(new javax.swing.BoxLayout(jPanel43, javax.swing.BoxLayout.Y_AXIS));

        producer3Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producer3Txt.setText("producer3Txt");
        jPanel43.add(producer3Txt);

        jPanel44.setLayout(new java.awt.CardLayout());

        jPanel45.setLayout(new java.awt.GridLayout(1, 0));

        producer3Sld.setMaximum(-100);
        producer3Sld.setMinimum(-2000);
        producer3Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        producer3Sld.setValue(-1050);
        producer3Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                producer3SldStateChanged(evt);
            }
        });
        jPanel45.add(producer3Sld);

        producer3Bar.setOrientation(1);
        producer3Bar.setStringPainted(true);
        jPanel45.add(producer3Bar);

        jPanel44.add(jPanel45, "card2");

        jPanel43.add(jPanel44);

        jPanel46.setLayout(new javax.swing.BoxLayout(jPanel46, javax.swing.BoxLayout.Y_AXIS));

        producer3Btt.setText("producer3Btt");
        producer3Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer3BttActionPerformed(evt);
            }
        });
        jPanel46.add(producer3Btt);

        producer3Chk.setText("producer3Chk");
        producer3Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer3ChkActionPerformed(evt);
            }
        });
        jPanel46.add(producer3Chk);

        jPanel43.add(jPanel46);

        jPanel6.add(jPanel43);

        jPanel47.setLayout(new javax.swing.BoxLayout(jPanel47, javax.swing.BoxLayout.Y_AXIS));

        producer4Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        producer4Txt.setText("producer4Txt");
        jPanel47.add(producer4Txt);

        jPanel48.setLayout(new java.awt.CardLayout());

        jPanel49.setLayout(new java.awt.GridLayout(1, 0));

        producer4Sld.setMaximum(-100);
        producer4Sld.setMinimum(-2000);
        producer4Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        producer4Sld.setValue(-1050);
        producer4Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                producer4SldStateChanged(evt);
            }
        });
        jPanel49.add(producer4Sld);

        producer4Bar.setOrientation(1);
        producer4Bar.setStringPainted(true);
        jPanel49.add(producer4Bar);

        jPanel48.add(jPanel49, "card2");

        jPanel47.add(jPanel48);

        jPanel50.setLayout(new javax.swing.BoxLayout(jPanel50, javax.swing.BoxLayout.Y_AXIS));

        producer4Btt.setText("producer4Btt");
        producer4Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer4BttActionPerformed(evt);
            }
        });
        jPanel50.add(producer4Btt);

        producer4Chk.setText("producer4Chk");
        producer4Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producer4ChkActionPerformed(evt);
            }
        });
        jPanel50.add(producer4Chk);

        jPanel47.add(jPanel50);

        jPanel6.add(jPanel47);

        jPanel10.add(jPanel6, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel7.add(jPanel10, gridBagConstraints);

        jPanel11.setLayout(new java.awt.CardLayout(20, 20));

        producerStop.setText("producerStop");
        producerStop.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                producerStopActionPerformed(evt);
            }
        });
        jPanel11.add(producerStop, "card3");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel7.add(jPanel11, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel7, gridBagConstraints);

        jPanel8.setLayout(new java.awt.CardLayout(10, 10));

        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.LINE_AXIS));

        bufferBar.setOrientation(1);
        bufferBar.setStringPainted(true);
        jPanel14.add(bufferBar);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        bufferTxt.setColumns(20);
        bufferTxt.setRows(5);
        bufferTxt.setAutoscrolls(false);
        bufferTxt.setDragEnabled(false);
        bufferTxt.setFocusable(false);
        bufferTxt.setMinimumSize(new java.awt.Dimension(0, 5));
        bufferTxt.setPreferredSize(new java.awt.Dimension(5, 5));
        bufferTxt.setRequestFocusEnabled(false);
        jScrollPane3.setViewportView(bufferTxt);

        jPanel14.add(jScrollPane3);

        bufferDerivate.setOrientation(1);
        bufferDerivate.setStringPainted(true);
        jPanel14.add(bufferDerivate);

        jPanel8.add(jPanel14, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 8.0;
        gridBagConstraints.weighty = 1.0;
        jPanel4.add(jPanel8, gridBagConstraints);

        jPanel27.setLayout(new java.awt.GridBagLayout());

        jPanel28.setLayout(new java.awt.CardLayout(5, 5));

        jPanel29.setLayout(new javax.swing.BoxLayout(jPanel29, javax.swing.BoxLayout.LINE_AXIS));

        jPanel30.setLayout(new javax.swing.BoxLayout(jPanel30, javax.swing.BoxLayout.Y_AXIS));

        consumer0Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consumer0Txt.setText("consumer0Txt");
        jPanel30.add(consumer0Txt);

        jPanel31.setLayout(new java.awt.CardLayout());

        jPanel32.setLayout(new java.awt.GridLayout(1, 0));

        consumer0Sld.setMaximum(-100);
        consumer0Sld.setMinimum(-2000);
        consumer0Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        consumer0Sld.setValue(-1050);
        consumer0Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                consumer0SldStateChanged(evt);
            }
        });
        jPanel32.add(consumer0Sld);

        consumer0Bar.setOrientation(1);
        consumer0Bar.setStringPainted(true);
        jPanel32.add(consumer0Bar);

        jPanel31.add(jPanel32, "card2");

        jPanel30.add(jPanel31);

        jPanel33.setLayout(new javax.swing.BoxLayout(jPanel33, javax.swing.BoxLayout.Y_AXIS));

        consumer0Btt.setText("consumer0Btt");
        consumer0Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer0BttActionPerformed(evt);
            }
        });
        jPanel33.add(consumer0Btt);

        consumer0Chk.setText("consumer0Chk");
        consumer0Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer0ChkActionPerformed(evt);
            }
        });
        jPanel33.add(consumer0Chk);

        jPanel30.add(jPanel33);

        jPanel29.add(jPanel30);

        jPanel34.setLayout(new javax.swing.BoxLayout(jPanel34, javax.swing.BoxLayout.Y_AXIS));

        consumer1Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consumer1Txt.setText("consumer1Txt");
        jPanel34.add(consumer1Txt);

        jPanel35.setLayout(new java.awt.CardLayout());

        jPanel36.setLayout(new java.awt.GridLayout(1, 0));

        consumer1Sld.setMaximum(-100);
        consumer1Sld.setMinimum(-2000);
        consumer1Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        consumer1Sld.setValue(-1050);
        consumer1Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                consumer1SldStateChanged(evt);
            }
        });
        jPanel36.add(consumer1Sld);

        consumer1Bar.setOrientation(1);
        consumer1Bar.setStringPainted(true);
        jPanel36.add(consumer1Bar);

        jPanel35.add(jPanel36, "card2");

        jPanel34.add(jPanel35);

        jPanel37.setLayout(new javax.swing.BoxLayout(jPanel37, javax.swing.BoxLayout.Y_AXIS));

        consumer1Btt.setText("consumer1Btt");
        consumer1Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer1BttActionPerformed(evt);
            }
        });
        jPanel37.add(consumer1Btt);

        consumer1Chk.setText("consumer1Chk");
        consumer1Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer1ChkActionPerformed(evt);
            }
        });
        jPanel37.add(consumer1Chk);

        jPanel34.add(jPanel37);

        jPanel29.add(jPanel34);

        jPanel38.setLayout(new javax.swing.BoxLayout(jPanel38, javax.swing.BoxLayout.Y_AXIS));

        consumer2Txt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consumer2Txt.setText("consumer2Txt");
        jPanel38.add(consumer2Txt);

        jPanel39.setLayout(new java.awt.CardLayout());

        jPanel40.setLayout(new java.awt.GridLayout(1, 0));

        consumer2Sld.setMaximum(-100);
        consumer2Sld.setMinimum(-2000);
        consumer2Sld.setOrientation(javax.swing.JSlider.VERTICAL);
        consumer2Sld.setValue(-1050);
        consumer2Sld.addChangeListener(new javax.swing.event.ChangeListener()
        {
            public void stateChanged(javax.swing.event.ChangeEvent evt)
            {
                consumer2SldStateChanged(evt);
            }
        });
        jPanel40.add(consumer2Sld);

        consumer2Bar.setOrientation(1);
        consumer2Bar.setStringPainted(true);
        jPanel40.add(consumer2Bar);

        jPanel39.add(jPanel40, "card2");

        jPanel38.add(jPanel39);

        jPanel41.setLayout(new javax.swing.BoxLayout(jPanel41, javax.swing.BoxLayout.Y_AXIS));

        consumer2Btt.setText("consumer2Btt");
        consumer2Btt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer2BttActionPerformed(evt);
            }
        });
        jPanel41.add(consumer2Btt);

        consumer2Chk.setText("consumer2Chk");
        consumer2Chk.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumer2ChkActionPerformed(evt);
            }
        });
        jPanel41.add(consumer2Chk);

        jPanel38.add(jPanel41);

        jPanel29.add(jPanel38);

        jPanel28.add(jPanel29, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 10.0;
        jPanel27.add(jPanel28, gridBagConstraints);

        jPanel42.setLayout(new java.awt.CardLayout(20, 20));

        consumerStop.setText("consumerStop");
        consumerStop.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                consumerStopActionPerformed(evt);
            }
        });
        jPanel42.add(consumerStop, "card3");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel27.add(jPanel42, gridBagConstraints);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 944, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.2;
        jPanel4.add(jPanel9, gridBagConstraints);

        ip.setText("IP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 0);
        jPanel4.add(ip, gridBagConstraints);

        jPanel2.add(jPanel4, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 3.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.CardLayout());

        jPanel5.setLayout(new java.awt.GridBagLayout());

        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt)
            {
                listValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(list);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane2, gridBagConstraints);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setAutoscrolls(true);

        txt.setEditable(false);
        txt.setColumns(20);
        txt.setRows(5);
        txt.setFocusable(false);
        jScrollPane1.setViewportView(txt);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.9;
        gridBagConstraints.weighty = 1.0;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jPanel3.add(jPanel5, "card2");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 14.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        new Load().go("Quiting.."); 
        server.end();
        Generator g = new Generator ();
        g.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void producer0BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer0BttActionPerformed
    {//GEN-HEADEREND:event_producer0BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(producer0Btt.isSelected())
        {
            stop[consumer.length].close();
        }
        else
        {
            stop[consumer.length].open();
        }
        if (bttProducer())
            producerStop.setSelected(true);
        else
            producerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producer0BttActionPerformed

    private void producer1BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer1BttActionPerformed
    {//GEN-HEADEREND:event_producer1BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(producer1Btt.isSelected())
        {
            stop[consumer.length+1].close();
        }
        else
        {
            stop[consumer.length+1].open();
        }
        if (bttProducer())
            producerStop.setSelected(true);
        else
            producerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producer1BttActionPerformed

    private void producer2BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer2BttActionPerformed
    {//GEN-HEADEREND:event_producer2BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(producer2Btt.isSelected())
        {
            stop[consumer.length+2].close();
        }
        else
        {
            stop[consumer.length+2].open();
        }
        if (bttProducer())
            producerStop.setSelected(true);
        else
            producerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producer2BttActionPerformed

    private void producer3BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer3BttActionPerformed
    {//GEN-HEADEREND:event_producer3BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(producer3Btt.isSelected())
        {
            stop[consumer.length+3].close();
        }
        else
        {
            stop[consumer.length+3].open();
        }
        if (bttProducer())
            producerStop.setSelected(true);
        else
            producerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producer3BttActionPerformed

    private void producer4BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer4BttActionPerformed
    {//GEN-HEADEREND:event_producer4BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(producer4Btt.isSelected())
        {
            stop[consumer.length+4].close();
        }
        else
        {
            stop[consumer.length+4].open();
        }
        if (bttProducer())
            producerStop.setSelected(true);
        else
            producerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producer4BttActionPerformed

    private void consumer0BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer0BttActionPerformed
    {//GEN-HEADEREND:event_consumer0BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(consumer0Btt.isSelected())
        {
            stop[0].close();
        }
        else
        {
            stop[0].open();
        }
        if (bttConsumer())
            consumerStop.setSelected(true);
        else
            consumerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_consumer0BttActionPerformed

    private void consumer1BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer1BttActionPerformed
    {//GEN-HEADEREND:event_consumer1BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(consumer1Btt.isSelected())
        {
            stop[1].close();
        }
        else
        {
            stop[1].open();
        }
        if (bttConsumer())
            consumerStop.setSelected(true);
        else
            consumerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_consumer1BttActionPerformed

    private void consumer2BttActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer2BttActionPerformed
    {//GEN-HEADEREND:event_consumer2BttActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if(consumer2Btt.isSelected())
        {
            stop[2].close();
        }
        else
        {
            stop[2].open();
        }
        if (bttConsumer())
            consumerStop.setSelected(true);
        else
            consumerStop.setSelected(false);
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_consumer2BttActionPerformed

    private void producerStopActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producerStopActionPerformed
    {//GEN-HEADEREND:event_producerStopActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if (producerStop.isSelected())
        {
            for (int i = consumer.length; i < consumer.length+producer.length; i++)
            {
                stop[i].close();
            }
            producer0Btt.setSelected(true);
            producer1Btt.setSelected(true);
            producer2Btt.setSelected(true);
            producer3Btt.setSelected(true);
            producer4Btt.setSelected(true);
        }
        else
        {
            for (int i = consumer.length; i < consumer.length+producer.length; i++)
            {
                stop[i].open();
            }
            producer0Btt.setSelected(false);
            producer1Btt.setSelected(false);
            producer2Btt.setSelected(false);
            producer3Btt.setSelected(false);
            producer4Btt.setSelected(false);
        }
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_producerStopActionPerformed

    private void consumerStopActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumerStopActionPerformed
    {//GEN-HEADEREND:event_consumerStopActionPerformed
        try
        {
            semaphrore.acquire(1);
        } 
        catch (InterruptedException ex){}
        
        if (consumerStop.isSelected())
        {
            for (int i = 0; i < consumer.length; i++)
            {
                stop[i].close();
            }
            consumer0Btt.setSelected(true);
            consumer1Btt.setSelected(true);
            consumer2Btt.setSelected(true);
        }
        else
        {
            for (int i = 0; i < consumer.length; i++)
            {
                stop[i].open();
            }
            consumer0Btt.setSelected(false);
            consumer1Btt.setSelected(false);
            consumer2Btt.setSelected(false);
        }
        
        semaphrore.release(1);
        
        server.send();
    }//GEN-LAST:event_consumerStopActionPerformed

    private void producer0ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer0ChkActionPerformed
    {//GEN-HEADEREND:event_producer0ChkActionPerformed
        producer0Chk.setSelected(true);
        producer[0].setRandomProductionRate();
    }//GEN-LAST:event_producer0ChkActionPerformed

    private void producer1ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer1ChkActionPerformed
    {//GEN-HEADEREND:event_producer1ChkActionPerformed
        producer1Chk.setSelected(true);
        producer[1].setRandomProductionRate();
    }//GEN-LAST:event_producer1ChkActionPerformed

    private void producer2ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer2ChkActionPerformed
    {//GEN-HEADEREND:event_producer2ChkActionPerformed
        producer2Chk.setSelected(true);
        producer[2].setRandomProductionRate();
    }//GEN-LAST:event_producer2ChkActionPerformed

    private void producer3ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer3ChkActionPerformed
    {//GEN-HEADEREND:event_producer3ChkActionPerformed
        producer3Chk.setSelected(true);
        producer[3].setRandomProductionRate();
    }//GEN-LAST:event_producer3ChkActionPerformed

    private void producer4ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_producer4ChkActionPerformed
    {//GEN-HEADEREND:event_producer4ChkActionPerformed
        producer4Chk.setSelected(true);
        producer[4].setRandomProductionRate();
    }//GEN-LAST:event_producer4ChkActionPerformed

    private void consumer0ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer0ChkActionPerformed
    {//GEN-HEADEREND:event_consumer0ChkActionPerformed
        consumer0Chk.setSelected(true);
        consumer[0].setRandomProductionRate();
    }//GEN-LAST:event_consumer0ChkActionPerformed

    private void consumer1ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer1ChkActionPerformed
    {//GEN-HEADEREND:event_consumer1ChkActionPerformed
        consumer1Chk.setSelected(true);
        consumer[1].setRandomProductionRate();
    }//GEN-LAST:event_consumer1ChkActionPerformed

    private void consumer2ChkActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_consumer2ChkActionPerformed
    {//GEN-HEADEREND:event_consumer2ChkActionPerformed
        consumer2Chk.setSelected(true);
        consumer[2].setRandomProductionRate();
    }//GEN-LAST:event_consumer2ChkActionPerformed

    private void producer0SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_producer0SldStateChanged
    {//GEN-HEADEREND:event_producer0SldStateChanged
        producer0Chk.setSelected(false);
        producer[0].setFixedProductionRate(-producer0Sld.getValue());
    }//GEN-LAST:event_producer0SldStateChanged

    private void producer1SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_producer1SldStateChanged
    {//GEN-HEADEREND:event_producer1SldStateChanged
        producer1Chk.setSelected(false);
        producer[1].setFixedProductionRate(-producer1Sld.getValue());
    }//GEN-LAST:event_producer1SldStateChanged

    private void producer2SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_producer2SldStateChanged
    {//GEN-HEADEREND:event_producer2SldStateChanged
        producer2Chk.setSelected(false);
        producer[2].setFixedProductionRate(-producer2Sld.getValue());
    }//GEN-LAST:event_producer2SldStateChanged

    private void producer3SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_producer3SldStateChanged
    {//GEN-HEADEREND:event_producer3SldStateChanged
        producer3Chk.setSelected(false);
        producer[3].setFixedProductionRate(-producer3Sld.getValue());
    }//GEN-LAST:event_producer3SldStateChanged

    private void producer4SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_producer4SldStateChanged
    {//GEN-HEADEREND:event_producer4SldStateChanged
        producer4Chk.setSelected(false);
        producer[4].setFixedProductionRate(-producer4Sld.getValue());
    }//GEN-LAST:event_producer4SldStateChanged

    private void consumer0SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_consumer0SldStateChanged
    {//GEN-HEADEREND:event_consumer0SldStateChanged
        consumer0Chk.setSelected(false);
        consumer[0].setFixedProductionRate(-consumer0Sld.getValue());
    }//GEN-LAST:event_consumer0SldStateChanged

    private void consumer1SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_consumer1SldStateChanged
    {//GEN-HEADEREND:event_consumer1SldStateChanged
        consumer1Chk.setSelected(false);
        consumer[1].setFixedProductionRate(-consumer1Sld.getValue());
    }//GEN-LAST:event_consumer1SldStateChanged

    private void consumer2SldStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_consumer2SldStateChanged
    {//GEN-HEADEREND:event_consumer2SldStateChanged
        consumer2Chk.setSelected(false);
        consumer[2].setFixedProductionRate(-consumer2Sld.getValue());
    }//GEN-LAST:event_consumer2SldStateChanged

    private void listValueChanged(javax.swing.event.ListSelectionEvent evt)//GEN-FIRST:event_listValueChanged
    {//GEN-HEADEREND:event_listValueChanged
        stopPrinting();
        switch(list.getSelectedIndex())
        {
            case 0:
                buffer.beguinPrinting();
            break;
            case 1:
                server.beguinPrinting();
            break;
            case 2:
                producer[0].beguinPrinting();
            break;
            case 3:
                producer[1].beguinPrinting();
            break;
            case 4:
                producer[2].beguinPrinting();
            break;
            case 5:
                producer[3].beguinPrinting();
            break;
            case 6:
                producer[4].beguinPrinting();
            break;
            case 7:
                consumer[0].beguinPrinting();
            break;
            case 8:
                consumer[1].beguinPrinting();
            break;
            case 9:
                consumer[2].beguinPrinting();
            break;
        }
    }//GEN-LAST:event_listValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar bufferBar;
    private javax.swing.JProgressBar bufferDerivate;
    private javax.swing.JTextArea bufferTxt;
    private javax.swing.JProgressBar consumer0Bar;
    private javax.swing.JToggleButton consumer0Btt;
    private javax.swing.JCheckBox consumer0Chk;
    private javax.swing.JSlider consumer0Sld;
    private javax.swing.JLabel consumer0Txt;
    private javax.swing.JProgressBar consumer1Bar;
    private javax.swing.JToggleButton consumer1Btt;
    private javax.swing.JCheckBox consumer1Chk;
    private javax.swing.JSlider consumer1Sld;
    private javax.swing.JLabel consumer1Txt;
    private javax.swing.JProgressBar consumer2Bar;
    private javax.swing.JToggleButton consumer2Btt;
    private javax.swing.JCheckBox consumer2Chk;
    private javax.swing.JSlider consumer2Sld;
    private javax.swing.JLabel consumer2Txt;
    private javax.swing.JToggleButton consumerStop;
    private javax.swing.JLabel ip;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> list;
    private javax.swing.JProgressBar producer0Bar;
    private javax.swing.JToggleButton producer0Btt;
    private javax.swing.JCheckBox producer0Chk;
    private javax.swing.JSlider producer0Sld;
    private javax.swing.JLabel producer0Txt;
    private javax.swing.JProgressBar producer1Bar;
    private javax.swing.JToggleButton producer1Btt;
    private javax.swing.JCheckBox producer1Chk;
    private javax.swing.JSlider producer1Sld;
    private javax.swing.JLabel producer1Txt;
    private javax.swing.JProgressBar producer2Bar;
    private javax.swing.JToggleButton producer2Btt;
    private javax.swing.JCheckBox producer2Chk;
    private javax.swing.JSlider producer2Sld;
    private javax.swing.JLabel producer2Txt;
    private javax.swing.JProgressBar producer3Bar;
    private javax.swing.JToggleButton producer3Btt;
    private javax.swing.JCheckBox producer3Chk;
    private javax.swing.JSlider producer3Sld;
    private javax.swing.JLabel producer3Txt;
    private javax.swing.JProgressBar producer4Bar;
    private javax.swing.JToggleButton producer4Btt;
    private javax.swing.JCheckBox producer4Chk;
    private javax.swing.JSlider producer4Sld;
    private javax.swing.JLabel producer4Txt;
    private javax.swing.JToggleButton producerStop;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables
}
