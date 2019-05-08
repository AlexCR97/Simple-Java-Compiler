package ale.ui.modules;

import ale.ui.frames.MainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.SwingUtilities;

public class ExplorerModule extends javax.swing.JPanel {

    private MainFrame app;
    
    public ExplorerModule() {
        initComponents();
        
        // init list
        this.jListItems.setModel(new DefaultListModel<>());
        
        // init listeners
        this.jListItems.addMouseListener(jListItemsListener);
    }

    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void addItem(String item) {
        DefaultListModel<String> model = (DefaultListModel<String>) this.jListItems.getModel();
        model.addElement(item);
        
        int index = model.getSize() - 1;
        this.jListItems.setSelectedIndex(index);
    }
    
    public String removeSelectedItem() {
        int selectedIndex = this.jListItems.getSelectedIndex();
        DefaultListModel model = (DefaultListModel) this.jListItems.getModel();
        
        Object item = model.remove(selectedIndex);
        return item.toString();
    }
    
    private final MouseListener jListItemsListener = new MouseAdapter() {
        int doubleClickCount = 2;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == doubleClickCount) {
                if (jListItems.getSelectedIndex() != -1) {
                    String selectedItem = jListItems.getSelectedValue();
                    app.getSourceCodeModule().changeTab(selectedItem);
                    //int selectedIndex = jListItems.locationToIndex(e.getPoint());
                    //System.out.println("You double clicked item " + jListItems.getSelectedValue());
                    //removeSelectedItem();
                }
            }
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListItems = new javax.swing.JList<>();

        setBackground(new java.awt.Color(102, 102, 102));
        setLayout(new java.awt.BorderLayout());

        jPanelHeader.setBackground(new java.awt.Color(51, 51, 51));
        jPanelHeader.setPreferredSize(new java.awt.Dimension(303, 25));
        jPanelHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Explorer");
        jPanelHeader.add(jLabel1);

        add(jPanelHeader, java.awt.BorderLayout.PAGE_START);

        jListItems.setBackground(new java.awt.Color(102, 102, 102));
        jListItems.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jListItems);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListItems;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
