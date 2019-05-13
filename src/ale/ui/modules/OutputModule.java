package ale.ui.modules;

import ale.ui.frames.MainFrame;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class OutputModule extends javax.swing.JPanel {
    
    public static final int OUTPUT_TYPE_PLAIN = 0;
    public static final int OUTPUT_TYPE_SUCCESS = 1;
    public static final int OUTPUT_TYPE_ERROR = 2;
    
    private static final Map<Integer, Color> COLORS = new HashMap<Integer, Color>();
    static {
        COLORS.put(OUTPUT_TYPE_PLAIN, Color.BLACK);
        COLORS.put(OUTPUT_TYPE_SUCCESS, Color.GREEN.darker());
        COLORS.put(OUTPUT_TYPE_ERROR, Color.RED.darker());
    }
    
    private MainFrame app;
    
    public OutputModule() {
        initComponents();
    }
    
    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void output(String text, int outputType, boolean overwrite) {
        Color color = COLORS.get(outputType);
        
        if (overwrite)
            this.jTextPaneOutput.setText("");
        
        this.jTextPaneOutput.setEditable(true);
        ale.Utils.appendToPane(jTextPaneOutput, text, color);
        this.jTextPaneOutput.setEditable(false);
    }
    
    public void outputln(String text, int outputType, boolean overwrite) {
        Color color = COLORS.get(outputType);
        
        if (overwrite)
            this.jTextPaneOutput.setText("");
            
        this.jTextPaneOutput.setEditable(true);
        ale.Utils.appendToPane(jTextPaneOutput, text + "\n", color);
        this.jTextPaneOutput.setEditable(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneOutput = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(46, 200));
        setName(""); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanelHeader.setBackground(new java.awt.Color(51, 51, 51));
        jPanelHeader.setPreferredSize(new java.awt.Dimension(713, 25));
        jPanelHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Output");
        jPanelHeader.add(jLabel1);

        add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jTextPaneOutput.setEditable(false);
        jTextPaneOutput.setBackground(new java.awt.Color(153, 153, 153));
        jTextPaneOutput.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(jTextPaneOutput);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPaneOutput;
    // End of variables declaration//GEN-END:variables
}
