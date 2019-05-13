package ale.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JTextPane;

public class SourceCodePanel extends javax.swing.JPanel {

    public SourceCodePanel() {
        initComponents();
    }

    private int getLineCount() {
        return 10;
        //return ale.Utils.getLineCount(this.jTextPaneCode);
    }
    
    public JTextPane getTextPane() {
        return this.jTextPaneCode;
    }
    
    public void setCodeFont(Font font) {
        this.jTextPaneCode.setFont(font);
    }
    
    public void setCodeText(String text) {
        this.jTextPaneCode.setText(text);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int x = this.getWidth() / 2;
        int y = 10;
        int yStep = 10;
        
        int lines = getLineCount();
        g.setColor(Color.RED);
        for (int i = 1; i <= lines; i++) {
            g.drawString(String.valueOf(i), x, y);
            y += yStep;
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelLineCount = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneCode = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        jPanelLineCount.setBackground(new java.awt.Color(0, 0, 0));
        jPanelLineCount.setMaximumSize(new java.awt.Dimension(25, 25));
        jPanelLineCount.setMinimumSize(new java.awt.Dimension(25, 25));
        jPanelLineCount.setPreferredSize(new java.awt.Dimension(25, 25));

        javax.swing.GroupLayout jPanelLineCountLayout = new javax.swing.GroupLayout(jPanelLineCount);
        jPanelLineCount.setLayout(jPanelLineCountLayout);
        jPanelLineCountLayout.setHorizontalGroup(
            jPanelLineCountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jPanelLineCountLayout.setVerticalGroup(
            jPanelLineCountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(jPanelLineCount, java.awt.BorderLayout.LINE_START);

        jTextPaneCode.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTextPaneCode);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelLineCount;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneCode;
    // End of variables declaration//GEN-END:variables
}
