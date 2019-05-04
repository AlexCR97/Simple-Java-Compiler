package ale.compiler;

public enum Lexeme {
    
    PREPROCESOR("PREPROCESOR"),
    RESERVED_WORD("RESERVED_WORD"),
    FLOW_CONTROLLER("FLOW_CONTROLLER"),
    DATA_TYPE("DATA_TYPE"),
    DELIMITER("DELIMITER"),
    COMMA("COMMA"),
    SEMICOLON("SEMICOLON"),
    LOGIC_OPERATOR("LOGIC_OPERATOR"),
    RELATIONAL_OPERATOR("RELATIONAL_OPERATOR"),
    ASSIGNMENT_OPERATOR("ASSIGNMENT_OPERATOR"),
    ARITHMETIC_OPERATOR("ARITHMETIC_OPERATOR"),
    ACCESS_OPERATOR("ACCESS_OPERATOR"),
    STRING("STRING"),
    NUMBER("NUMBER"),
    IDENTIFIER("IDENTIFIER")
    ;
    
    private final String lexeme;
    
    Lexeme(final String lexeme) {
        this.lexeme = lexeme;
    }
    
    @Override
    public String toString() {
        return lexeme;
    }
}
