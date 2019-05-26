package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class SwitchSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("7", "16");
        
        addTransition("1", Token.FLOW_CONTROLLER_SWITCH.toString(), "2");
        addTransition("2", Token.DELIMITER_PARENTHESIS_OPEN.toString(), "3");
        addTransition("3", Token.IDENTIFIER.toString(), "4");
        addTransition("4", Token.DELIMITER_PARENTHESIS_CLOSE.toString(), "5");
        addTransition("5", Token.DELIMITER_BRACKET_OPEN.toString(), "6");
        addTransition("6", Token.DELIMITER_BRACKET_CLOSE.toString(), "7");
        addTransition("6", Token.FLOW_CONTROLLER_CASE.toString(), "8");
        addTransition("6", Token.FLOW_CONTROLLER_DEFAULT.toString(), "12");
        addTransition("8", Token.NUMBER.toString(), "9");
        addTransition("8", Token.STRING.toString(), "9");
        addTransition("9", Token.COMMA.toString(), "8");
        addTransition("9", Token.DELIMITER_BRACKET_OPEN.toString(), "10");
        addTransition("10", "<statement-list>", "11");
        addTransition("11", Token.DELIMITER_BRACKET_CLOSE.toString(), "6");
        addTransition("12", Token.DELIMITER_BRACKET_OPEN.toString(), "13");
        addTransition("13", "<statement-list>", "14");
        addTransition("14", Token.DELIMITER_BRACKET_CLOSE.toString(), "15");
        addTransition("15", Token.DELIMITER_BRACKET_CLOSE.toString(), "16");
        
        //addTransition("", Token.toString(), "");
    }
    
}

/*

switch (number) {
    case 1 { <statements> }
    case 2 { <statements> }
    default { <statements> }
}

switch (vowel) {
    case "a", "e", "i", "o", "u" { <statements> }
}

switch (variable) {
    case "a", "e", "i", "o", "u" { <statements> }
    case 1, 2, 3 { <statements> }
    case 10 { <statements> }
    case "neo" { <statements> }
    default { <statements> }
}

*/
