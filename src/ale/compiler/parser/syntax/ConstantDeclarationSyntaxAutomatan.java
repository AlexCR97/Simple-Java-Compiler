package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class ConstantDeclarationSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("5");
        
        addTransition("1", Token.RESERVED_WORD_CONST.toString(), "2");
        addTransition("2", Token.IDENTIFIER.toString(), "3");
        addTransition("3", Token.ASSIGNMENT_OPERATOR.toString(), "4");
        addTransition("4", Token.NUMBER.toString(), "5");
        addTransition("4", Token.STRING.toString(), "5");
        addTransition("4", Token.IDENTIFIER.toString(), "5");
        addTransition("4", Token.RESERVED_WORD_TRUE.toString(), "5");
        addTransition("4", Token.RESERVED_WORD_FALSE.toString(), "5");
    }
    
}
