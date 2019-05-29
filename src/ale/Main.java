package ale;

import ale.compiler.parser.NeoParser;
import ale.ui.frames.MainFrame;
import ale.ui.frames.SplashFrame;
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
        
        openSplashFrame();
        openMainFrame();
        
        //mainParser();
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
    
    public static void mainParser() {
        NeoParser parser = new NeoParser();
        
        //String[] tokens = "void main ( ) { int var1 = 0 ; }".split(" ");
        //String[] tokens = "void main ( ) { var1 ( ) ; }".split(" ");
        //String[] tokens = "void main ( ) { if ( a > b ) { string x = a ( ) ; } }".split(" ");
        //String[] tokens = "void main ( ) { if ( a > b ) { if ( b < c ) { string x = a ( ) ; c ( ) ; } } }".split(" ");
        //String[] tokens = "void main ( ) { var1 ( ) ; a ( ) ; }".split(" ");
        //String[] tokens = "void main ( ) { }".split(" ");
        //String[] tokens = "void main ( ) { switch ( a ) { } }".split(" ");
        //String[] tokens = "void main ( ) { switch ( x ) { case 0 { int x = 1 ; foo ( ) ; } } }".split(" ");
        //String[] tokens = "void main ( ) { switch ( x ) { case 0 { int x = 1 ; foo ( ) ; } case 1 { lorem . ipsum ( ) ; } } }".split(" ");
        //String[] tokens = "void main ( ) { switch ( x ) { case 0 { int x = 1 ; foo ( ) ; } case 1 { lorem . ipsum ( ) ; } case 2 { if ( a == b ) { string foo = foo ( ) ; } } } }".split(" ");
        //String[] tokens = "void main ( ) { while ( a < 9 ) { } }".split(" ");
        //String[] tokens = "void main ( ) { while ( a < 9 ) { foo ( ) ; x = y ; } }".split(" ");
        //String[] tokens = "void main ( ) { for ( int i = 0 ; i < 9 ; i = 2 ) { } }".split(" ");
        //String[] tokens = "void main ( ) { for ( var x = 10 ; x < 1 0 ; x = 1 0 0 ) { for ( var y = 0 ; y < 9 ; y = 1 ) { x = y ; } } }".split(" ");
        
        //String[] tokens = "namespace a . foo :: ipsum { void main ( ) { } }".split(" ");
        //String[] tokens = "a ; namespace a . foo :: ipsum { void main ( ) { } }".split(" ");
        //String[] tokens = "a :: b ; namespace a . foo :: ipsum { void main ( ) { } }".split(" ");
        String[] tokens = "a :: b :: c :: d ; namespace a . foo :: ipsum { void main ( ) { } }".split(" ");
        
        parser.parse(tokens);
    }
    
}
