package ale;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Utils {
    
    public static File openFile(Component frame) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ale files", "ale");
        chooser.setFileFilter(filter);
        
        int returnValue = chooser.showOpenDialog(frame);
        if (returnValue != JFileChooser.APPROVE_OPTION)
            return null;
        
        return chooser.getSelectedFile();
    }
    
    public static String readFile(File file) {
        try {
            byte[] encoded = Files.readAllBytes(file.toPath());
            return new String(encoded, Charset.defaultCharset());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static String readFileStringBuilder(File file) {
        try {
            StringBuilder string = new StringBuilder();
            Path path = Paths.get(file.getAbsolutePath());
            Stream<String> fileContent = Files.lines(path);
            fileContent.forEach(string::append);
            return string.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static File saveFile(Component frame) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ale files", "ale");
        chooser.setFileFilter(filter);
        
        int result = chooser.showSaveDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION)
            return null;
        
        File file = chooser.getSelectedFile();
        return saveFile(file, "");
    }
    
    public static File saveFile(Component frame, String text) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Ale files", "ale");
        chooser.setFileFilter(filter);
        
        int result = chooser.showSaveDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION)
            return null;
        
        File file = chooser.getSelectedFile();
        return saveFile(file, text);
    }
    
    public static File saveFile(File file, String text) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(text);
            return file;
        }
        catch (FileNotFoundException ex) { ex.printStackTrace(); }
        catch (UnsupportedEncodingException ex) { ex.printStackTrace(); }
        catch (IOException ex) { ex.printStackTrace(); }
        finally {
            try { writer.close(); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
        return null;
    }
    
    public static void showMessageDialog(Component frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
}
