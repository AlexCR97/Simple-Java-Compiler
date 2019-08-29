package ale.ui.components;

import java.awt.Dimension;
import java.awt.FontMetrics;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SourceCodePanel extends javax.swing.JPanel {

    private final int LINE_MARGIN = 50;
    
    public SourceCodePanel() {
        initComponents();
        
        // set fonts
        this.jTextPaneCode.setFont(Fonts.getCustomFont());
        this.jTextPaneLines.setFont(Fonts.getCustomFont());
        
        // set text changed listener
        this.jTextPaneCode.getDocument().addDocumentListener(jTextPaneCodeListener);
    }

    private int getLineCount() {
        return ale.Utils.getLineCount(this.jTextPaneCode);
    }
    
    public String getCodeText() {
        return this.jTextPaneCode.getText();
    }
    
    public JTextPane getCodeTextPane() {
        return this.jTextPaneCode;
    }
    
    public void setCodeLines(int lineCount) {
        this.jTextPaneLines.setEditable(true);
        
        this.jTextPaneLines.setText("");
        for (int i = 1; i <= lineCount; i++) {
            String text = this.jTextPaneLines.getText() + String.valueOf(i) + "\n";
            this.jTextPaneLines.setText(text);
        }
        
        this.jTextPaneLines.setEditable(false);
    }
    
    public void setCodeText(String text) {
        this.jTextPaneCode.setText(text);
    }
    
    public void updateLineCount() {
        // update line count
        int lineCount = getLineCount();
        setCodeLines(lineCount);
        
        // update width depending on line count
        FontMetrics metrics = jTextPaneCode.getFontMetrics(jTextPaneCode.getFont());
        int textWidth = metrics.stringWidth(String.valueOf(lineCount));
        
        int width = textWidth + (LINE_MARGIN * 2);
        int height = this.jTextPaneCode.getPreferredSize().height;

        this.jTextPaneLines.setSize(new Dimension(width, height));
        
        //this.jTextPaneLines.setPreferredSize(new Dimension(width, height));
        this.repaint();
    }
    
    private final DocumentListener jTextPaneCodeListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLineCount();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLineCount();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateLineCount();
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneCode = new javax.swing.JTextPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPaneLines = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        jTextPaneCode.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(jTextPaneCode);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(30, 25));

        jTextPaneLines.setEditable(false);
        jTextPaneLines.setMinimumSize(new java.awt.Dimension(15, 15));
        jTextPaneLines.setPreferredSize(new java.awt.Dimension(15, 15));
        jScrollPane1.setViewportView(jTextPaneLines);

        add(jScrollPane1, java.awt.BorderLayout.LINE_START);
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneCode;
    private javax.swing.JTextPane jTextPaneLines;
    // End of variables declaration//GEN-END:variables
}
