package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class VariableDynamicDeclarationSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("4", "12");
        
        addTransition("1", Token.RESERVED_WORD_VAR.toString(), "2");
        addTransition("2", Token.IDENTIFIER.toString(), "3");
        addTransition("3", Token.SEMICOLON.toString(), "4");
        addTransition("3", Token.ASSIGNMENT_OPERATOR.toString(), "5");
        addTransition("5", Token.NUMBER.toString(), "6");
        addTransition("5", Token.STRING.toString(), "7");
        addTransition("5", Token.IDENTIFIER.toString(), "8");
        addTransition("5", Token.RESERVED_WORD_TRUE.toString(), "9");
        addTransition("5", Token.RESERVED_WORD_FALSE.toString(), "10");
        addTransition("6", Token.SEMICOLON.toString(), "12");
        addTransition("7", Token.SEMICOLON.toString(), "12");
        addTransition("8", Token.SEMICOLON.toString(), "12");
        addTransition("9", Token.SEMICOLON.toString(), "12");
        addTransition("10", Token.SEMICOLON.toString(), "12");
    }
    
}
