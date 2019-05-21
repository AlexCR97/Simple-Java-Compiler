package ale.ui.modules;

import ale.ui.components.Fonts;
import ale.ui.components.SourceCodePanel;
import ale.ui.frames.MainFrame;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;

public class SourceCodeModule extends javax.swing.JPanel {

    private final Map<String, SourceCodePanel> codePanels = new HashMap<>();
    private final Map<String, JPanel> tabs = new HashMap<>();
    
    private MainFrame app;
    
    public SourceCodeModule() {
        initComponents();
    }

    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void addTab(String tabName) {
        addTab(tabName, "");
    }
    
    public void addTab(String tabName, String fileContents) {
        JPanel panel;
        if (tabs.containsKey(tabName))
            panel = tabs.get(tabName);
        else {
            panel = createSourceCodePanel(tabName, fileContents);
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
        String tabContent = codePanels.get(tabName).getCodeText();
        addTab(tabName, tabContent);
    }
    
    private SourceCodePanel createSourceCodePanel(String tabName, String fileContent) {
        SourceCodePanel codePanel = new SourceCodePanel();
        codePanel.setCodeText(fileContent);
        codePanel.updateLineCount();
        
        ale.Utils.setTabSize(codePanel.getCodeTextPane(), 4);
        ale.Utils.addUndoRedo(codePanel.getCodeTextPane());
        
        codePanels.put(tabName, codePanel);
        
        return codePanel;
    }
    
    public void closeTab(String tabName) {
        changeTab(tabName);
        closeCurrentTab();
        codePanels.remove(tabName);
        tabs.remove(tabName);
    }
    
    public void closeCurrentTab() {
        int index = this.jTabbedPane.getSelectedIndex();
        if (index == -1)
            return;
        
        //content.remove(tabName);
        this.jTabbedPane.remove(index);
    }
    
    public int getTabCount() {
        return this.jTabbedPane.getTabCount();
    }
    
    public int getCurrentLineCount() {
        String tabName = getCurrentTabName();
        SourceCodePanel codePanel = codePanels.get(tabName);
        return ale.Utils.getLineCount(codePanel.getCodeTextPane());
    }
    
    public String getCurrentTabName() {
        int index = this.jTabbedPane.getSelectedIndex();
        String tabName = this.jTabbedPane.getTitleAt(index);
        return tabName;
    }
    
    public String getCurrentTabContent() {
        String tabName = getCurrentTabName();
        SourceCodePanel codePanel = codePanels.get(tabName);
        return codePanel.getCodeText();
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
