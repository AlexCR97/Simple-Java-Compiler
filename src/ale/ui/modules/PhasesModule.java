package ale.ui.modules;

import ale.ui.frames.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class PhasesModule extends javax.swing.JPanel {

    private MainFrame app;
    
    public PhasesModule() {
        initComponents();
        
        // set listeners
        this.jButtonClearTokensTable.addActionListener(clearTokensTableListener);
    }

    public void setApp(MainFrame app) {
        this.app = app;
    }
    
    public void clearTokensTable() {
        DefaultTableModel model = (DefaultTableModel) this.jTableTokens.getModel();
        model.setRowCount(0);
    }
    
    public void fillTokensTable(Map<String, String> tokensTable) {
        clearTokensTable();
        
        for (Map.Entry<String, String> i : tokensTable.entrySet()) {
            String line = "0";
            String token = i.getKey();
            String lexeme = i.getValue();
            
            DefaultTableModel model = (DefaultTableModel) this.jTableTokens.getModel();
            model.addRow(new String[] {line, lexeme, token});
        }
    }
    
    private final ActionListener clearTokensTableListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearTokensTable();
        }
    };
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelLexical = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTokens = new javax.swing.JTable();
        jButtonClearTokensTable = new javax.swing.JButton();
        jPanelSyntax = new javax.swing.JPanel();
        jPanelSemantic = new javax.swing.JPanel();

        setBackground(new java.awt.Color(102, 102, 102));
        setLayout(new java.awt.BorderLayout());

        jPanelLexical.setBackground(new java.awt.Color(102, 102, 102));
        jPanelLexical.setLayout(new java.awt.BorderLayout());

        jTableTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Line", "Lexeme", "Token"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTokens.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTableTokens);
        jTableTokens.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTableTokens.getColumnModel().getColumnCount() > 0) {
            jTableTokens.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanelLexical.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jButtonClearTokensTable.setText("Clear");
        jPanelLexical.add(jButtonClearTokensTable, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Lexical", jPanelLexical);

        javax.swing.GroupLayout jPanelSyntaxLayout = new javax.swing.GroupLayout(jPanelSyntax);
        jPanelSyntax.setLayout(jPanelSyntaxLayout);
        jPanelSyntaxLayout.setHorizontalGroup(
            jPanelSyntaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanelSyntaxLayout.setVerticalGroup(
            jPanelSyntaxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Syntax", jPanelSyntax);

        javax.swing.GroupLayout jPanelSemanticLayout = new javax.swing.GroupLayout(jPanelSemantic);
        jPanelSemantic.setLayout(jPanelSemanticLayout);
        jPanelSemanticLayout.setHorizontalGroup(
            jPanelSemanticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jPanelSemanticLayout.setVerticalGroup(
            jPanelSemanticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Semantic", jPanelSemantic);

        add(jTabbedPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClearTokensTable;
    private javax.swing.JPanel jPanelLexical;
    private javax.swing.JPanel jPanelSemantic;
    private javax.swing.JPanel jPanelSyntax;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableTokens;
    // End of variables declaration//GEN-END:variables
}
