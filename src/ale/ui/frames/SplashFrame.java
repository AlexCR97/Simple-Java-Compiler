package ale.ui.frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class SplashFrame extends javax.swing.JFrame {

    private final String[] STATES = {
        "Creating GUI...",
        "Loading modules...",
        "Adding colors...",
        "Setting layout...",
        "Launching IDE..."
    };
    private final Timer timer;
    
    private int state = 0;
    
    public SplashFrame() {
        initComponents();
        
        timer = new Timer(0, timerListener);
        timer.setDelay(1500);
        timer.start();
    }
    
    private final ActionListener timerListener = (ActionEvent e) -> {
        state = ale.Utils.wrap(state, 0, STATES.length - 1);
        this.jLabelState.setText(STATES[state]);
        state++;
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splashPanel = new ale.ui.components.SplashPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelState = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(720, 480));
        setSize(new java.awt.Dimension(720, 480));

        splashPanel.setLayout(new javax.swing.BoxLayout(splashPanel, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/logo.png"))); // NOI18N
        jLabel1.setAlignmentX(0.5F);
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(50, 1, 1, 1));
        splashPanel.add(jLabel1);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/loading1.gif"))); // NOI18N
        jLabel2.setAlignmentX(0.5F);
        splashPanel.add(jLabel2);

        jLabelState.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelState.setText("Loading modules...");
        jLabelState.setAlignmentX(0.5F);
        jLabelState.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 10, 1));
        splashPanel.add(jLabelState);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Neo (c) 2019. CR Corporation");
        jLabel4.setAlignmentX(0.5F);
        splashPanel.add(jLabel4);

        getContentPane().add(splashPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelState;
    private ale.ui.components.SplashPanel splashPanel;
    // End of variables declaration//GEN-END:variables
}
