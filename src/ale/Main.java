package ale;

import ale.compiler.Token;
import ale.structures.DerivationTree;
import ale.structures.ContextFreeGrammar;
import ale.structures.DerivationRegexTree;
import ale.structures.NeoContextFreeGrammar;
import ale.ui.frames.MainFrame;
import ale.ui.frames.SplashFrame;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    private static final long LOAD_TIME = 6000;
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        //openSplashFrame();
        //openMainFrame();
        //mainTree();
        //mainRegex();
        mainTreeNeo();
    }
    
    public static void openMainFrame() {
        MainFrame frame = new MainFrame();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(frame.getExtendedState() | MainFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    public static void openSplashFrame() {
        SplashFrame splash = new SplashFrame();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        
        try {
            Thread.sleep(LOAD_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        splash.setVisible(false);
        openMainFrame();
    }
    
    public static void mainTree() {
        ContextFreeGrammar grammar = new ContextFreeGrammar();
        
        DerivationTree tree = new DerivationTree();
        tree.fromGrammar(grammar);

        tree.traversePre();
        System.out.println();
     
        List<String> sentence = Arrays.asList("There was once", "a", "happy", "horse");
        //List<String> sentence = Arrays.asList("happy", "horse", "There was once", "a");
        //System.out.println("The sentence " + sentence + " is valid: " + tree.evaluate(sentence));
        
        boolean success = tree.validate(sentence);
    }
    
    public static void mainRegex() {
        String regex;
        String input;
        
        regex = Token.NUMBER.toString();
        input = "3.1459";
        System.out.printf("input '%s' matches regex '%s': ", input, regex);
        System.out.println(ale.Utils.regexValidate(regex, input));
    }
    
    public static void mainTreeNeo() {
        NeoContextFreeGrammar grammar = new NeoContextFreeGrammar();
        
        DerivationRegexTree tree = new DerivationRegexTree();
        tree.fromGrammar(grammar);
        tree.traversePre();
        
        List<String> sentence = Arrays.asList("@neo::console", ";", "namespace", "ale", "{", "void", "main", "(", ")", "{", "}", "}");
        tree.validate(sentence);
    }
    
}
