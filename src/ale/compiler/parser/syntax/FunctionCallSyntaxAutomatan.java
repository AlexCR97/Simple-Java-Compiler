package ale.compiler.parser.syntax;

import ale.compiler.Token;
import java.util.Arrays;

public class FunctionCallSyntaxAutomatan extends SyntaxAutomatan {
    
    @Override
    public final void init() {
        initialState = "1";
        finalStates = Arrays.asList("4");
        
        addTransition("1", Token.IDENTIFIER.toString(), "2");
        addTransition("2", Token.ACCESS_OPERATOR_INSTANCE.toString(), "1");
        addTransition("2", Token.ACCESS_OPERATOR_CLASS.toString(), "1");
        addTransition("2", Token.DELIMITER_PARENTHESIS_OPEN.toString(), "3");
        addTransition("3", Token.DELIMITER_PARENTHESIS_CLOSE.toString(), "4");
        addTransition("3", Token.IDENTIFIER.toString(), "5");
        addTransition("3", Token.NUMBER.toString(), "5");
        addTransition("3", Token.STRING.toString(), "5");
        addTransition("5", Token.DELIMITER_PARENTHESIS_CLOSE.toString(), "4");
        addTransition("5", Token.COMMA.toString(), "6");
        addTransition("6", Token.IDENTIFIER.toString(), "5");
        addTransition("6", Token.NUMBER.toString(), "5");
        addTransition("6", Token.STRING.toString(), "5");
        
        //addTransition("", Token.toString(), "");
    }
    
}

/*

EASY

foo()
lorem.ipsum()
lorem.ipsum(a)
lorem.ipsum(a, 10)
lorem.ipsum(a, 10, "start")
console::println()
console::println("Hello, Neo!")
console::printf("Your full name is %s %s\n", firstName, lastName)

HARD

foo(a(), b(), c())
foo(math::PI)
console::scan(foo::get_class(), math::PI, a, 10)
ale::foo.lorem(math::sum(a, b), 10, time.now(), util::clamp(value, min, max))
*/
