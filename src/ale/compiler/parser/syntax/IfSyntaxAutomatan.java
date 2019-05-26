package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class IfSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("8", "12");
        
        addTransition("1", Token.FLOW_CONTROLLER_IF.toString(), "2");
        addTransition("2", Token.DELIMITER_PARENTHESIS_OPEN.toString(), "3");
        addTransition("3", "<relational-operation>", "4");
        addTransition("4", Token.DELIMITER_PARENTHESIS_CLOSE.toString(), "5");
        addTransition("5", Token.DELIMITER_BRACKET_OPEN.toString(), "6");
        addTransition("6", "<statement-list>", "7");
        addTransition("7", Token.DELIMITER_BRACKET_CLOSE.toString(), "8");
        addTransition("8", Token.FLOW_CONTROLLER_ELSE.toString(), "9");
        addTransition("9", Token.FLOW_CONTROLLER_IF.toString(), "2");
        addTransition("9", Token.DELIMITER_BRACKET_OPEN.toString(), "10");
        addTransition("10", "<statement-list>", "11");
        addTransition("11", Token.DELIMITER_BRACKET_CLOSE.toString(), "12");
        
        //addTransition("", Token.toString(), "");
    }
    
}
