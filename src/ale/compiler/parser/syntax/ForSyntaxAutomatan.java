package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class ForSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("12");
        
        addTransition("1", Token.FLOW_CONTROLLER_FOR.toString(), "2");
        addTransition("2", Token.DELIMITER_PARENTHESIS_OPEN.toString(), "3");
        addTransition("3", "<statement>", "4");
        addTransition("3", Token.SEMICOLON.toString(), "5");
        addTransition("4", Token.SEMICOLON.toString(), "5");
        addTransition("5", "<relational-operation>", "6");
        addTransition("5", Token.SEMICOLON.toString(), "7");
        addTransition("6", Token.SEMICOLON.toString(), "7");
        addTransition("7", "<statement>", "8");
        addTransition("7", Token.SEMICOLON.toString(), "8");
        addTransition("8", Token.DELIMITER_PARENTHESIS_CLOSE.toString(), "9");
        addTransition("9", Token.DELIMITER_BRACKET_OPEN.toString(), "10");
        addTransition("10", "<statement-list>", "11");
        addTransition("11", Token.DELIMITER_BRACKET_CLOSE.toString(), "12");

        //addTransition("", Token.toString(), "");
    }
    
    public int foo() {
        return 0;
    }
    
}

/*

for (int i = 0; i < 10; i++) { }

for (var x = 0; x < y; x += 0.25) { }

for (;;) {}

*/
