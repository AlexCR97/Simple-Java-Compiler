package ale;

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
        
        //openSplashFrame();
        openMainFrame();
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
    
}
