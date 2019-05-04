package ale.ui.modules;

import ale.compiler.TokenColor;
import ale.ui.components.Fonts;
import ale.ui.frames.MainFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class SourceCodeModule extends javax.swing.JPanel {

    private Map<String, JTextPane> content = new HashMap<>();
    private MainFrame app;
    
    public SourceCodeModule() {
        initComponents();
    }

    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void addTab(String tabName) {
        JPanel panel = createTextPanePanel(tabName, "");
        this.jTabbedPane.addTab(tabName, panel);
        
        int index = this.jTabbedPane.getTabCount() - 1;
        this.jTabbedPane.setSelectedIndex(index);
    }
    
    public void addTab(String tabName, String fileContents) {
        JPanel panel = createTextPanePanel(tabName, fileContents);
        this.jTabbedPane.addTab(tabName, panel);
        
        int index = this.jTabbedPane.getTabCount() - 1;
        this.jTabbedPane.setSelectedIndex(index);
    }
    
    public JPanel createTextPanePanel(String tabName, String fileContent) {
        JTextPane textPane = new JTextPane();
        textPane.setText(fileContent);
        textPane.setFont(Fonts.getCustomFont());
        //appendToPaneColors(textPane, fileContent);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        
        content.put(tabName, textPane);
        
        return panel;
    }
    
    private void appendToPaneColors(JTextPane textPane, String text) {
        for (String s : text.split(" "))
            appendToPane(textPane, s, TokenColor.getColor(s));
    }
    
    private void appendToPane(JTextPane textpane, String text, Color color) {
        StyleContext style = StyleContext.getDefaultStyleContext();
        
        AttributeSet attributes;
        attributes = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attributes = style.addAttribute(attributes, StyleConstants.FontFamily, "Consolas");
        attributes = style.addAttribute(attributes, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int length = textpane.getDocument().getLength();
        
        textpane.setCaretPosition(length);
        textpane.setCharacterAttributes(attributes, false);
        textpane.replaceSelection(text);
    }
    
    public void closeCurrentTab() {
        int index = this.jTabbedPane.getSelectedIndex();
        if (index == -1)
            return;
        
        String tabName = getCurrentTabName();
        
        content.remove(tabName);
        this.jTabbedPane.remove(index);
    }
    
    public int getTabCount() {
        return this.jTabbedPane.getTabCount();
    }
    
    public String getCurrentTabName() {
        int index = this.jTabbedPane.getSelectedIndex();
        String tabName = this.jTabbedPane.getTitleAt(index);
        return tabName;
    }
    
    public String getCurrentTabContent() {
        String tabName = getCurrentTabName();
        JTextPane textPane = content.get(tabName);
        return textPane.getText();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());
        add(jTabbedPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane;
    // End of variables declaration//GEN-END:variables
}
