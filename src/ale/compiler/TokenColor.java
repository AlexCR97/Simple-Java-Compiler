package ale.compiler;

import java.awt.Color;

public class TokenColor {
    
    public static final Color PREPROCESOR = Color.PINK;
    public static final Color RESERVED_WORD = new Color(0, 0, 0);
    public static final Color FLOW_CONTROLLER = new Color(0, 0, 0);
    public static final Color DATA_TYPE = new Color(0, 0, 0);
    public static final Color DELIMITER = new Color(0, 0, 0);
    public static final Color COMMA = new Color(0, 0, 0);
    public static final Color SEMICOLON = new Color(0, 0, 0);
    public static final Color LOGIC_OPERATOR = new Color(0, 0, 0);
    public static final Color RELATIONAL_OPERATOR = new Color(0, 0, 0);
    public static final Color ASSIGNMENT_OPERATOR = new Color(0, 0, 0);
    public static final Color ARITHMETIC_OPERATOR = new Color(0, 0, 0);
    public static final Color ACCESS_OPERATOR = new Color(0, 0, 0);
    public static final Color STRING = new Color(0, 0, 0);
    public static final Color NUMBER = new Color(0, 0, 0);
    public static final Color IDENTIFIER = new Color(0, 0, 0);
    
    public static Color getColor(String lexeme) {
        
        if (lexeme.equals(Lexeme.PREPROCESOR.toString()))
            return PREPROCESOR;
        
        return Color.BLACK;
    }
    
}
