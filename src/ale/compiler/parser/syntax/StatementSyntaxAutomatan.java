package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class StatementSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("3");
        
        addTransition("1", "<dynamic-declaration>", "2");
        addTransition("1", "<static-declaration>", "2");
        addTransition("1", "<const-declaration>", "2");
        addTransition("1", "<function-call>", "2");
        addTransition("2", Token.SEMICOLON.toString(), "3");
        
        //addTransition("", Token.toString(), "");
    }
    
}
