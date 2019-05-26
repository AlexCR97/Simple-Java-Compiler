package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class RelationalOperationSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("3", "7", "9");
        
        addTransition("1", Token.LOGIC_OPERATOR_NOT.toString(), "5");
        addTransition("1", Token.NUMBER.toString(), "2");
        addTransition("1", Token.IDENTIFIER.toString(), "3");
        addTransition("2", Token.RELATIONAL_OPERATOR_DIFFERENT.toString(), "6");
        addTransition("2", Token.RELATIONAL_OPERATOR_EQUALS.toString(), "6");
        addTransition("2", Token.RELATIONAL_OPERATOR_GREATER.toString(), "6");
        addTransition("2", Token.RELATIONAL_OPERATOR_GREATER_EQUALS.toString(), "6");
        addTransition("2", Token.RELATIONAL_OPERATOR_LESS.toString(), "6");
        addTransition("2", Token.RELATIONAL_OPERATOR_LESS_EQULAS.toString(), "6");
        addTransition("3", Token.LOGIC_OPERATOR_AND.toString(), "1");
        addTransition("3", Token.LOGIC_OPERATOR_OR.toString(), "1");
        addTransition("3", Token.RELATIONAL_OPERATOR_DIFFERENT.toString(), "6");
        addTransition("3", Token.RELATIONAL_OPERATOR_EQUALS.toString(), "6");
        addTransition("3", Token.RELATIONAL_OPERATOR_GREATER.toString(), "6");
        addTransition("3", Token.RELATIONAL_OPERATOR_GREATER_EQUALS.toString(), "6");
        addTransition("3", Token.RELATIONAL_OPERATOR_LESS.toString(), "6");
        addTransition("3", Token.RELATIONAL_OPERATOR_LESS_EQULAS.toString(), "6");
        addTransition("5", Token.NUMBER.toString(), "8");
        addTransition("5", Token.IDENTIFIER.toString(), "9");
        addTransition("6", Token.NUMBER.toString(), "7");
        addTransition("6", Token.IDENTIFIER.toString(), "7");
        addTransition("7", Token.LOGIC_OPERATOR_AND.toString(), "1");
        addTransition("7", Token.LOGIC_OPERATOR_OR.toString(), "1");
        addTransition("8", Token.RELATIONAL_OPERATOR_DIFFERENT.toString(), "6");
        addTransition("8", Token.RELATIONAL_OPERATOR_EQUALS.toString(), "6");
        addTransition("8", Token.RELATIONAL_OPERATOR_GREATER.toString(), "6");
        addTransition("8", Token.RELATIONAL_OPERATOR_GREATER_EQUALS.toString(), "6");
        addTransition("8", Token.RELATIONAL_OPERATOR_LESS.toString(), "6");
        addTransition("8", Token.RELATIONAL_OPERATOR_LESS_EQULAS.toString(), "6");
        addTransition("9", Token.LOGIC_OPERATOR_AND.toString(), "1");
        addTransition("9", Token.LOGIC_OPERATOR_OR.toString(), "1");
        addTransition("9", Token.RELATIONAL_OPERATOR_DIFFERENT.toString(), "6");
        addTransition("9", Token.RELATIONAL_OPERATOR_EQUALS.toString(), "6");
        addTransition("9", Token.RELATIONAL_OPERATOR_GREATER.toString(), "6");
        addTransition("9", Token.RELATIONAL_OPERATOR_GREATER_EQUALS.toString(), "6");
        addTransition("9", Token.RELATIONAL_OPERATOR_LESS.toString(), "6");
        addTransition("9", Token.RELATIONAL_OPERATOR_LESS_EQULAS.toString(), "6");
        
        //addTransition("", Token.toString(), "");
    }
    
}
