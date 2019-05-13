package ale.ui.modules;

import ale.ui.components.Fonts;
import ale.ui.components.SourceCodePanel;
import ale.ui.frames.MainFrame;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class SourceCodeModule extends javax.swing.JPanel {

    private final Map<String, JTextPane> content = new HashMap<>();
    private final Map<String, JPanel> tabs = new HashMap<>();
    
    private final Map<String, SourceCodePanel> codePanels = new HashMap<>();
    
    private MainFrame app;
    
    public SourceCodeModule() {
        initComponents();
    }

    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void addCodeTab(String tabName, String fileContents) {
        SourceCodePanel panel = createSourceCodePanel(tabName, fileContents);
        this.jTabbedPane.addTab(tabName, panel);
        int index = this.jTabbedPane.getTabCount() - 1;
        this.jTabbedPane.setSelectedIndex(index);
    }
    
    public void addTab(String tabName) {
        JPanel panel;
        if (tabs.containsKey(tabName))
            panel = tabs.get(tabName);
        else {
            panel = createTextPanePanel(tabName, "");
            tabs.put(tabName, panel);
        }
        
        this.jTabbedPane.addTab(tabName, panel);
        
        int index = this.jTabbedPane.getTabCount() - 1;
        this.jTabbedPane.setSelectedIndex(index);
    }
    
    public void addTab(String tabName, String fileContents) {
        JPanel panel;
        if (tabs.containsKey(tabName))
            panel = tabs.get(tabName);
        else {
            panel = createTextPanePanel(tabName, fileContents);
            tabs.put(tabName, panel);
        }
        
        this.jTabbedPane.addTab(tabName, panel);
        
        int index = this.jTabbedPane.getTabCount() - 1;
        this.jTabbedPane.setSelectedIndex(index);
    }
    
    public void changeTab(String tabName) {
        // if tab is open, select it
        for (int i = 0; i < this.jTabbedPane.getTabCount(); i++) {
            String currentTabName = this.jTabbedPane.getTitleAt(i);
            if (currentTabName.equals(tabName)) {
                this.jTabbedPane.setSelectedIndex(i);
                return;
            }
        }
        
        // if tab is closed, open it
        String tabContent = content.get(tabName).getText();
        addTab(tabName, tabContent);
    }
    
    private JPanel createTextPanePanel(String tabName, String fileContent) {
        JTextPane textPane = new JTextPane();
        textPane.setText(fileContent);
        textPane.setFont(Fonts.getCustomFont());
        
        ale.Utils.setTabSize(textPane, 4);
        ale.Utils.addUndoRedo(textPane);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textPane);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        
        content.put(tabName, textPane);
        
        return panel;
    }
    
    private SourceCodePanel createSourceCodePanel(String tabName, String fileContent) {
        SourceCodePanel codePanel = new SourceCodePanel();
        codePanel.setCodeText(fileContent);
        codePanel.setCodeText(fileContent);
        codePanel.setCodeFont(Fonts.getCustomFont());
        
        ale.Utils.setTabSize(codePanel.getTextPane(), 4);
        ale.Utils.addUndoRedo(codePanel.getTextPane());
        
        return codePanel;
    }
    
    public void closeCurrentTab() {
        int index = this.jTabbedPane.getSelectedIndex();
        if (index == -1)
            return;
        
        String tabName = getCurrentTabName();
        
        //content.remove(tabName);
        this.jTabbedPane.remove(index);
    }
    
    public int getTabCount() {
        return this.jTabbedPane.getTabCount();
    }
    
    public int getCurrentLineCount() {
        String tabName = getCurrentTabName();
        System.out.println("Getting current line count of " + tabName);
        JTextPane textPane = content.get(tabName);
        return ale.Utils.getLineCount(textPane);
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
