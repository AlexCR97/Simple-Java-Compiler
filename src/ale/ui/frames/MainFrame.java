package ale.ui.frames;

import ale.compiler.lexer.Lexer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends javax.swing.JFrame {

    private final Map<String, File> files = new HashMap<>();
    
    private Lexer lexer;
    
    public MainFrame() {
        initComponents();
        
        // set frame in modules
        this.explorerModule.setApp(this);
        this.outputModule.setApp(this);
        this.phasesModule.setApp(this);
        this.sourceCodeModule.setApp(this);
        
        // set listeners
        this.jButtonNewFile.addActionListener(newFileListener);
        this.jButtonOpenFile.addActionListener(openFileListener);
        this.jButtonSaveAll.addActionListener(saveFileListener);
        this.jButtonClose.addActionListener(closeListener);
        this.jButtonCompile.addActionListener(compileListener);
    }

    private final ActionListener newFileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File file = ale.Utils.saveFile(MainFrame.this);
            if (file == null)
                return;
            
            String fileName = file.getName();
            
            files.put(fileName, file);
            sourceCodeModule.addTab(fileName);
        }
    };
    
    private final ActionListener openFileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            File file = ale.Utils.openFile(MainFrame.this);
            if (file == null)
                return;
            
            String fileName = file.getName();
            String fileContent = ale.Utils.readFile(file);
            
            if (fileName == null || fileContent == null) {
                ale.Utils.showMessageDialog(MainFrame.this, "File could not be opened :(");
                return;
            }
            
            files.put(fileName, file);
            sourceCodeModule.addTab(fileName, fileContent);
        }
    };
    
    private final ActionListener saveFileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sourceCodeModule.getTabCount() == 0)
                return;
            
            String currentFileName = sourceCodeModule.getCurrentTabName();
            String currentFileContent = sourceCodeModule.getCurrentTabContent();
            File file = files.get(currentFileName);
            
            if (file == null) {
                ale.Utils.showMessageDialog(MainFrame.this, "An error occured trying to save the file :(");
                return;
            }
            
            file = ale.Utils.saveFile(file, currentFileContent);
            if (file == null) {
                ale.Utils.showMessageDialog(MainFrame.this, "An error occured trying to save the file :(");
                return;
            }
            
            ale.Utils.showMessageDialog(MainFrame.this, "File saved! :D");
        }
    };
    
    private final ActionListener closeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sourceCodeModule.getTabCount() == 0)
                return;
            
            String tabName = sourceCodeModule.getCurrentTabName();
            
            files.remove(tabName);
            sourceCodeModule.closeCurrentTab();
        }
    };
    
    private final ActionListener compileListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sourceCodeModule.getTabCount() == 0)
                return;
            
            String fileName = sourceCodeModule.getCurrentTabName();
            File file = files.get(fileName);
            
            if (!lexicalAnalysis(file)) {
                ale.Utils.showMessageDialog(MainFrame.this, "Lexical analysis failed! :(");
            }
        }
    };
    
    private boolean lexicalAnalysis(File file) {
        System.out.println("PERFORMING LEXICAL ANALYSIS ON FILE: " + file.getName());
        
        lexer = new Lexer(file.getAbsolutePath());
        while (lexer.hasNext()) {
            System.out.printf("%s %s\n", lexer.currentToken(), lexer.currentLexeme());
        }

        if (!lexer.isSuccessful()) {
            System.out.println("COULD NOT COMPLETE LEXICAL ANALYSIS");
            System.out.println(lexer.getErrorMessage());
            return false;
        }

        System.out.println("LEXICAL ANALYSIS COMPLETED");
        phasesModule.fillTokensTable(lexer.getTokensTable());
            
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jPanelContainer = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButtonNewFile = new javax.swing.JButton();
        jButtonOpenFile = new javax.swing.JButton();
        jButtonSaveAll = new javax.swing.JButton();
        jButtonClose = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButtonUndo = new javax.swing.JButton();
        jButtonRedo = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButtonCompile = new javax.swing.JButton();
        jButtonRun = new javax.swing.JButton();
        jButtonCompileRun = new javax.swing.JButton();
        jPanelModules = new javax.swing.JPanel();
        sourceCodeModule = new ale.ui.modules.SourceCodeModule();
        phasesModule = new ale.ui.modules.PhasesModule();
        outputModule = new ale.ui.modules.OutputModule();
        explorerModule = new ale.ui.modules.ExplorerModule();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemNewFile = new javax.swing.JMenuItem();
        jMenuItemOpenFile = new javax.swing.JMenuItem();
        jMenuItemCloseFile = new javax.swing.JMenuItem();
        jMenuItemCloseAll = new javax.swing.JMenuItem();
        jMenuItemSave = new javax.swing.JMenuItem();
        jMenuItemSaveAs = new javax.swing.JMenuItem();
        jMenuItemSaveAll = new javax.swing.JMenuItem();
        jMenuItemSettings = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuEdit = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuView = new javax.swing.JMenu();
        jMenuAbout = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        jMenuItem5.setText("jMenuItem5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelContainer.setBackground(new java.awt.Color(51, 51, 51));
        jPanelContainer.setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        jButtonNewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/new-file.png"))); // NOI18N
        jButtonNewFile.setFocusable(false);
        jButtonNewFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNewFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonNewFile);

        jButtonOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/open-file.png"))); // NOI18N
        jButtonOpenFile.setFocusable(false);
        jButtonOpenFile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonOpenFile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonOpenFile);

        jButtonSaveAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/save-all.png"))); // NOI18N
        jButtonSaveAll.setFocusable(false);
        jButtonSaveAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSaveAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonSaveAll);

        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/close.png"))); // NOI18N
        jButtonClose.setToolTipText("");
        jButtonClose.setFocusable(false);
        jButtonClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonClose.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonClose);
        jToolBar1.add(jSeparator4);

        jButtonUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/arrow-undo.png"))); // NOI18N
        jButtonUndo.setFocusable(false);
        jButtonUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonUndo);

        jButtonRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/arrow-redo.png"))); // NOI18N
        jButtonRedo.setFocusable(false);
        jButtonRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonRedo);
        jToolBar1.add(jSeparator5);

        jButtonCompile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/compile.png"))); // NOI18N
        jButtonCompile.setFocusable(false);
        jButtonCompile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCompile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonCompile);

        jButtonRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/run.png"))); // NOI18N
        jButtonRun.setFocusable(false);
        jButtonRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonRun);

        jButtonCompileRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ale/assets/compile-run.png"))); // NOI18N
        jButtonCompileRun.setFocusable(false);
        jButtonCompileRun.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCompileRun.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButtonCompileRun);

        jPanelContainer.add(jToolBar1, java.awt.BorderLayout.NORTH);

        jPanelModules.setBackground(new java.awt.Color(0, 204, 204));
        jPanelModules.setLayout(new java.awt.BorderLayout());
        jPanelModules.add(sourceCodeModule, java.awt.BorderLayout.CENTER);
        jPanelModules.add(phasesModule, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout outputModuleLayout = new javax.swing.GroupLayout(outputModule);
        outputModule.setLayout(outputModuleLayout);
        outputModuleLayout.setHorizontalGroup(
            outputModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1078, Short.MAX_VALUE)
        );
        outputModuleLayout.setVerticalGroup(
            outputModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanelModules.add(outputModule, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout explorerModuleLayout = new javax.swing.GroupLayout(explorerModule);
        explorerModule.setLayout(explorerModuleLayout);
        explorerModuleLayout.setHorizontalGroup(
            explorerModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        explorerModuleLayout.setVerticalGroup(
            explorerModuleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        jPanelModules.add(explorerModule, java.awt.BorderLayout.LINE_START);

        jPanelContainer.add(jPanelModules, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelContainer, java.awt.BorderLayout.CENTER);

        jMenuFile.setText("File");

        jMenuItemNewFile.setText("New File");
        jMenuFile.add(jMenuItemNewFile);

        jMenuItemOpenFile.setText("Open File");
        jMenuFile.add(jMenuItemOpenFile);

        jMenuItemCloseFile.setText("Close File");
        jMenuFile.add(jMenuItemCloseFile);

        jMenuItemCloseAll.setText("Close All");
        jMenuFile.add(jMenuItemCloseAll);

        jMenuItemSave.setText("Save");
        jMenuFile.add(jMenuItemSave);

        jMenuItemSaveAs.setText("Save As...");
        jMenuFile.add(jMenuItemSaveAs);

        jMenuItemSaveAll.setText("Save All");
        jMenuFile.add(jMenuItemSaveAll);

        jMenuItemSettings.setText("Settings");
        jMenuFile.add(jMenuItemSettings);

        jMenuItemExit.setText("Exit");
        jMenuFile.add(jMenuItemExit);

        jMenuBar1.add(jMenuFile);

        jMenuEdit.setText("Edit");

        jMenuItem1.setText("Undo");
        jMenuEdit.add(jMenuItem1);

        jMenuItem2.setText("Redo");
        jMenuEdit.add(jMenuItem2);

        jMenuItem3.setText("Search");
        jMenuEdit.add(jMenuItem3);

        jMenuBar1.add(jMenuEdit);

        jMenuView.setText("View");
        jMenuBar1.add(jMenuView);

        jMenuAbout.setText("Help");

        jMenuItem4.setText("Documentation");
        jMenuAbout.add(jMenuItem4);

        jMenuItem6.setText("About");
        jMenuAbout.add(jMenuItem6);

        jMenuBar1.add(jMenuAbout);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ale.ui.modules.ExplorerModule explorerModule;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonCompile;
    private javax.swing.JButton jButtonCompileRun;
    private javax.swing.JButton jButtonNewFile;
    private javax.swing.JButton jButtonOpenFile;
    private javax.swing.JButton jButtonRedo;
    private javax.swing.JButton jButtonRun;
    private javax.swing.JButton jButtonSaveAll;
    private javax.swing.JButton jButtonUndo;
    private javax.swing.JMenu jMenuAbout;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEdit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItemCloseAll;
    private javax.swing.JMenuItem jMenuItemCloseFile;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemNewFile;
    private javax.swing.JMenuItem jMenuItemOpenFile;
    private javax.swing.JMenuItem jMenuItemSave;
    private javax.swing.JMenuItem jMenuItemSaveAll;
    private javax.swing.JMenuItem jMenuItemSaveAs;
    private javax.swing.JMenuItem jMenuItemSettings;
    private javax.swing.JMenu jMenuView;
    private javax.swing.JPanel jPanelContainer;
    private javax.swing.JPanel jPanelModules;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private ale.ui.modules.OutputModule outputModule;
    private ale.ui.modules.PhasesModule phasesModule;
    private ale.ui.modules.SourceCodeModule sourceCodeModule;
    // End of variables declaration//GEN-END:variables
}
