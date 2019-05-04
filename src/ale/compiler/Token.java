package ale.compiler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Token {
    
    COMMENT("\\/\\*[\\s\\S]*?\\*\\/"),
    
    PREPROCESOR_IMPORT("@ale(\\/[a-z]+)+"),
    
    RESERVED_WORD_NAMESPACE("namespace\\b"),
    RESERVED_WORD_VOID("void\\b"),
    RESERVED_WORD_RETURN("return\\b"),
    RESERVED_WORD_VAR("var\\b"),
    RESERVED_WORD_CONST("const\\b"),
    RESERVED_WORD_NULL("null\\b"),
    
    FLOW_CONTROLLER_IF("if\\b"),
    FLOW_CONTROLLER_ELSE("else\\b"),
    FLOW_CONTROLLER_SWITCH("switch\\b"),
    FLOW_CONTROLLER_WHILE("while\\b"),
    FLOW_CONTROLLER_FOR("for\\b"),
    FLOW_CONTROLLER_ITERATE("iterate\\b"),
    
    DATA_TYPE_INT("int\\b"),
    DATA_TYPE_FLOAT("float\\b"),
    DATA_TYPE_DOUBLE("double\\b"),
    DATA_TYPE_BOOL("bool\\b"),
    DATA_TYPE_STRING("string\\b"),
    DATA_TYPE_ARRAY("array\\b"),
    DATA_TYPE_MATRIX("matrix\\b"),
    
    DELIMITER_PARENTHESIS_OPEN("\\("),
    DELIMITER_PARENTHESIS_CLOSE("\\)"),
    DELIMITER_BRACKET_OPEN("\\{"),
    DELIMITER_BRACKET_CLOSE("\\}"),
    
    LOGIC_OPERATOR_AND("and\\b"),
    LOGIC_OPERATOR_OR("or\\b"),
    LOGIC_OPERATOR_NOT("not\\b"),
    
    RELATIONAL_OPERATOR_EQUALS("=="),
    RELATIONAL_OPERATOR_DIFFERENT("<>"),
    RELATIONAL_OPERATOR_GREATER_EQUALS(">="),
    RELATIONAL_OPERATOR_GREATER(">"),
    RELATIONAL_OPERATOR_LESS_EQULAS("<="),
    RELATIONAL_OPERATOR_LESS("<"),
    
    ARITHMETIC_OPERATOR_ADD("\\+"),
    ARITHMETIC_OPERATOR_SUB("\\-"),
    ARITHMETIC_OPERATOR_MUL("\\*"),
    ARITHMETIC_OPERATOR_DIV("\\/"),
    ARITHMETIC_OPERATOR_MOD("\\%"),
    ARITHMETIC_OPERATOR_POW("\\^"),
    ARITHMETIC_OPERATOR_ROO("\\~"),
    
    ACCESS_OPERATOR_CLASS("\\:{2}"),
    ACCESS_OPERATOR_INSTANCE("\\."),
    ACCESS_OPERATOR_INDEX_OPEN("\\["),
    ACCESS_OPERATOR_INDEX_CLOSE("\\]"),
    
    ASSIGNMENT_OPERATOR("\\="),
    COLON("\\:"),
    COMMA("\\,"),
    SEMICOLON("\\;"),
    TYPE_SPECIFIER("\\|"),
    STRING("\"[^\"]+\""),
    NUMBER("\\d+[f|d]?(\\.\\d+[f|d]?)?"),
    IDENTIFIER("([a-zA-Z]|_*[a-zA-Z]){1}[a-zA-Z0-9_]*"),
    ;
    
    public final Pattern pattern;

    Token(String regex) {
        pattern = Pattern.compile("^" + regex);
    }
    
    public int endOfMatch(String s) {
        Matcher m = pattern.matcher(s);
        
        if (m.find())
            return m.end();
        
        return -1;
    }
    
    @Override
    public String toString() {
        return pattern.toString();
    }
    
}
