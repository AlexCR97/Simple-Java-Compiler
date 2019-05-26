package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class FlowControllerSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "";
        finalStates = Arrays.asList("");
        
        // if else DERIVATION
        // switch DERIVATION
        // while DERIVATION
        // for DERIVATION
        
        //addTransition("", Token.toString(), "");
    }
    
}
