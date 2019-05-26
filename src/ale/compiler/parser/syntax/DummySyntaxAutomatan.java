package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class DummySyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "";
        finalStates = Arrays.asList("");
        
        //addTransition("", Token.toString(), "");
    }
    
}
