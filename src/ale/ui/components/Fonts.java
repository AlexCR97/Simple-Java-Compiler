package ale.ui.components;

import java.awt.Font;

public class Fonts {
    
    public static final int SIZE_10 = 10;
    public static final int SIZE_12 = 12;
    public static final int SIZE_14 = 14;
    public static final int SIZE_16 = 16;
    public static final int SIZE_18 = 18;
    
    public static Font getCustomFont() {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, SIZE_18);
        return font;
    }
    
}
