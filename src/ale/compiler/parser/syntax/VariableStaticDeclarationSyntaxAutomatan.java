package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class VariableStaticDeclarationSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("3", "5");
        
        addTransition("1", Token.DATA_TYPE_BOOL.toString(), "2");
        addTransition("1", Token.DATA_TYPE_DOUBLE.toString(), "2");
        addTransition("1", Token.DATA_TYPE_FLOAT.toString(), "2");
        addTransition("1", Token.DATA_TYPE_INT.toString(), "2");
        addTransition("1", Token.DATA_TYPE_STRING.toString(), "2");
        addTransition("2", Token.IDENTIFIER.toString(), "3");
        addTransition("3", Token.ASSIGNMENT_OPERATOR.toString(), "4");
        addTransition("4", Token.NUMBER.toString(), "5");
        addTransition("4", Token.STRING.toString(), "5");
        addTransition("4", Token.IDENTIFIER.toString(), "5");
        addTransition("4", Token.RESERVED_WORD_TRUE.toString(), "5");
        addTransition("4", Token.RESERVED_WORD_FALSE.toString(), "5");
    }

}
