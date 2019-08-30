package ale.compiler;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class TokenColor {
    
    private static final Color COMMENT = Color.GRAY;
    private static final Color RESERVED_WORD = Color.RED;
    private static final Color FLOW_CONTROLLER = Color.RED;
    private static final Color DATA_TYPE = Color.RED;
    private static final Color DELIMITER = Color.BLACK;
    private static final Color LOGIC_OPERATOR = Color.BLACK;
    private static final Color RELATIONAL_OPERATOR = Color.BLACK;
    private static final Color ARITHMETIC_OPERATOR = Color.BLACK;
    private static final Color ACCESS_OPERATOR = Color.BLACK;
    private static final Color ASSIGNMENT_OPERATOR = Color.BLACK;
    private static final Color COMMA = Color.BLACK;
    private static final Color COLON = Color.BLACK;
    private static final Color SEMICOLON = Color.BLACK;
    private static final Color TYPE_SPECIFIER = Color.BLACK;
    private static final Color STRING = Color.BLUE;
    private static final Color NUMBER = Color.GREEN.darker();
    private static final Color IDENTIFIER = Color.BLACK;
    
    private static final Map<String, Color> COLORS = new HashMap<>();
    static {
        COLORS.put(Token.COMMENT.name(), COMMENT);
        
        COLORS.put(Token.RESERVED_WORD_CONST.name(), RESERVED_WORD);
        COLORS.put(Token.RESERVED_WORD_NAMESPACE.name(), RESERVED_WORD);
        COLORS.put(Token.RESERVED_WORD_NULL.name(), RESERVED_WORD);
        COLORS.put(Token.RESERVED_WORD_RETURN.name(), RESERVED_WORD);
        COLORS.put(Token.RESERVED_WORD_VAR.name(), RESERVED_WORD);
        COLORS.put(Token.RESERVED_WORD_VOID.name(), RESERVED_WORD);
        
        COLORS.put(Token.FLOW_CONTROLLER_ELSE.name(), FLOW_CONTROLLER);
        COLORS.put(Token.FLOW_CONTROLLER_FOR.name(), FLOW_CONTROLLER);
        COLORS.put(Token.FLOW_CONTROLLER_IF.name(), FLOW_CONTROLLER);
        COLORS.put(Token.FLOW_CONTROLLER_ITERATE.name(), FLOW_CONTROLLER);
        COLORS.put(Token.FLOW_CONTROLLER_WHEN.name(), FLOW_CONTROLLER);
        COLORS.put(Token.FLOW_CONTROLLER_WHILE.name(), FLOW_CONTROLLER);
        
        COLORS.put(Token.DATA_TYPE_ARRAY.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_BOOL.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_DOUBLE.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_FLOAT.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_INT.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_MATRIX.name(), DATA_TYPE);
        COLORS.put(Token.DATA_TYPE_STRING.name(), DATA_TYPE);
        
        COLORS.put(Token.DELIMITER_BRACKET_CLOSE.name(), DELIMITER);
        COLORS.put(Token.DELIMITER_BRACKET_OPEN.name(), DELIMITER);
        COLORS.put(Token.DELIMITER_PARENTHESIS_CLOSE.name(), DELIMITER);
        COLORS.put(Token.DELIMITER_PARENTHESIS_OPEN.name(), DELIMITER);
        
        COLORS.put(Token.LOGIC_OPERATOR_AND.name(), LOGIC_OPERATOR);
        COLORS.put(Token.LOGIC_OPERATOR_NOT.name(), LOGIC_OPERATOR);
        COLORS.put(Token.LOGIC_OPERATOR_OR.name(), LOGIC_OPERATOR);
        
        COLORS.put(Token.RELATIONAL_OPERATOR_DIFFERENT.name(), RELATIONAL_OPERATOR);
        COLORS.put(Token.RELATIONAL_OPERATOR_EQUALS.name(), RELATIONAL_OPERATOR);
        COLORS.put(Token.RELATIONAL_OPERATOR_GREATER.name(), RELATIONAL_OPERATOR);
        COLORS.put(Token.RELATIONAL_OPERATOR_GREATER_EQUALS.name(), RELATIONAL_OPERATOR);
        COLORS.put(Token.RELATIONAL_OPERATOR_LESS.name(), RELATIONAL_OPERATOR);
        COLORS.put(Token.RELATIONAL_OPERATOR_LESS_EQULAS.name(), RELATIONAL_OPERATOR);
        
        COLORS.put(Token.ARITHMETIC_OPERATOR_ADD.name(), ARITHMETIC_OPERATOR);
        COLORS.put(Token.ARITHMETIC_OPERATOR_DIV.name(), ARITHMETIC_OPERATOR);
        COLORS.put(Token.ARITHMETIC_OPERATOR_MOD.name(), ARITHMETIC_OPERATOR);
        COLORS.put(Token.ARITHMETIC_OPERATOR_POW.name(), ARITHMETIC_OPERATOR);
        COLORS.put(Token.ARITHMETIC_OPERATOR_ROO.name(), ARITHMETIC_OPERATOR);
        COLORS.put(Token.ARITHMETIC_OPERATOR_SUB.name(), ARITHMETIC_OPERATOR);
        
        COLORS.put(Token.ACCESS_OPERATOR_CLASS.name(), ACCESS_OPERATOR);
        COLORS.put(Token.ACCESS_OPERATOR_INDEX_CLOSE.name(), ACCESS_OPERATOR);
        COLORS.put(Token.ACCESS_OPERATOR_INDEX_OPEN.name(), ACCESS_OPERATOR);
        COLORS.put(Token.ACCESS_OPERATOR_INSTANCE.name(), ACCESS_OPERATOR);
        
        COLORS.put(Token.ASSIGNMENT_OPERATOR.name(), ASSIGNMENT_OPERATOR);
        COLORS.put(Token.COLON.name(), COLON);
        COLORS.put(Token.COMMA.name(), COMMA);
        COLORS.put(Token.SEMICOLON.name(), SEMICOLON);
        COLORS.put(Token.TYPE_SPECIFIER.name(), TYPE_SPECIFIER);
        COLORS.put(Token.STRING.name(), STRING);
        COLORS.put(Token.NUMBER.name(), NUMBER);
        COLORS.put(Token.IDENTIFIER.name(), IDENTIFIER);
    }
    
    public static Color getColor(String token) {
        if (COLORS.containsKey(token))
            return COLORS.get(token);
        
        return Color.BLACK;
    }
    
}
