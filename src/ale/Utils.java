package ale;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.AttributeSet;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class Utils {
    
    private static final Dimension FILE_CHOOSER_DIMENSION = new Dimension(800, 500);
    private static final String NEO_EXTENSION = "neo";
    
    public static File openFile(Component frame) {
        JFileChooser chooser = new JFileChooser();
        chooser.setPreferredSize(FILE_CHOOSER_DIMENSION);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Neo files", NEO_EXTENSION);
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Neo files", NEO_EXTENSION);
        chooser.setFileFilter(filter);
        chooser.setPreferredSize(FILE_CHOOSER_DIMENSION);
        
        int result = chooser.showSaveDialog(frame);
        if (result != JFileChooser.APPROVE_OPTION)
            return null;
        
        File file = chooser.getSelectedFile();
        return saveFile(file, "");
    }
    
    public static File saveFile(Component frame, String text) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Neo files", NEO_EXTENSION);
        chooser.setFileFilter(filter);
        chooser.setPreferredSize(FILE_CHOOSER_DIMENSION);
        
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
        catch (FileNotFoundException ex)        { ex.printStackTrace(); }
        catch (UnsupportedEncodingException ex) { ex.printStackTrace(); }
        catch (IOException ex)                  { ex.printStackTrace(); }
        finally {
            try { writer.close(); }
            catch (IOException ex) { ex.printStackTrace(); }
        }
        return null;
    }
    
    public static void showMessageDialog(Component frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
    public static void appendToPane(JTextPane textpane, String text, Color color) {
        StyleContext style = StyleContext.getDefaultStyleContext();
        
        AttributeSet attributes;
        attributes = style.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attributes = style.addAttribute(attributes, StyleConstants.FontFamily, "Courier new");
        attributes = style.addAttribute(attributes, StyleConstants.FontSize, 16);
        attributes = style.addAttribute(attributes, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

        int length = textpane.getDocument().getLength();
        
        textpane.setCaretPosition(length);
        textpane.setCharacterAttributes(attributes, false);
        textpane.replaceSelection(text);
    }
    
    public static void setTabSize(JTextPane textPane, int charactersPerTab) {
        FontMetrics metrics = textPane.getFontMetrics(textPane.getFont());
        int charWidth = metrics.charWidth(' ');
        int tabWidth = charWidth * charactersPerTab;

        TabStop[] tabs = new TabStop[5];
        for (int i = 0; i < tabs.length; i++) {
            int tab = i + 1;
            tabs[i] = new TabStop(tab * tabWidth);
        }

        TabSet tabSet = new TabSet(tabs);
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setTabSet(attributes, tabSet);
        
        int length = textPane.getDocument().getLength();
        textPane.getStyledDocument().setParagraphAttributes(0, length, attributes, false);
    }
    
    public static void addUndoRedo(JTextComponent component) {
        String UNDO = "undo";
        String REDO = "redo";
        
        KeyStroke undoKey = KeyStroke.getKeyStroke("control Z");
        KeyStroke redoKey = KeyStroke.getKeyStroke("control Y");
        
        UndoManager manager = new UndoManager();
        component.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent event) {
                manager.addEdit(event.getEdit());
            }
        });
        
        component.getActionMap().put(REDO, new AbstractAction(REDO) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (manager.canRedo())
                        manager.redo();
                } catch (CannotRedoException ex) { ex.printStackTrace(); }
            }
        });
        component.getInputMap().put(redoKey, REDO);
        
        component.getActionMap().put(UNDO, new AbstractAction(UNDO) {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (manager.canUndo())
                        manager.undo();
                } catch (CannotUndoException ex) { ex.printStackTrace(); }
            }
        });
        component.getInputMap().put(undoKey, UNDO);
    }
    
    public static int getRandomRangeI(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    public static int wrap(int value, int min, int max) {
        if (value < min)
            return max;
        if (value > max)
            return min;
        return value;
    }
    
    public static JPopupMenu createPopupMenu(String[] items) {
        JPopupMenu popup = new JPopupMenu();
        for (String item : items)
            popup.add(new JMenuItem(item));
        
        popup.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        return popup;
    }
    
    public static int getLineCount(JTextPane textPane) {
        Document doc = textPane.getDocument();
        int lineCount = new JTextArea(doc).getLineCount();
        return lineCount;
    }
    
    public static boolean regexValidate(String regex, String input) {
        Pattern pattern = Pattern.compile("^" + regex);
        Matcher matcher = pattern.matcher(input);
        
        System.out.printf("Checking if input '%s' matches regex '%s'...\n", input, regex);
        boolean success = matcher.matches();
        
        if (success)
            System.out.println("Match! :D");
        else
            System.out.println("No match :(");
        
        return success;
    }
    
    public static List<String> toStringList(String input, String separator) {
        String[] words = input.split(separator);
        return Arrays.asList(words);
    }
    
}
