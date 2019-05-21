package ale.structures;

import ale.compiler.Token;
import java.util.Arrays;

public class NeoContextFreeGrammar extends ContextFreeGrammar {
    
    private final String NEO_TAG_START = "<start>";
    private final String NEO_TAG_EMPTY = "<empty>";
    private final String NEO_TAG_IMPORT = "<import>";
    private final String NEO_TAG_NAMESPACE = "<namespace>";
    private final String NEO_TAG_MAIN = "<main>";
    private final String NEO_TAG_STATEMENT_LIST = "<statement-list>";
    private final String NEO_TAG_STATEMENT = "<statement>";
    private final String NEO_TAG_DECLARATION = "<declaration>";
    private final String NEO_TAG_ASSIGNMENT = "<assignment>";
    private final String NEO_TAG_FUNCTION_CALL = "<function-call>";
    private final String NEO_TAG_IF = "<if>";
    private final String NEO_TAG_SWITCH = "<switch>";
    private final String NEO_TAG_WHILE = "<while>";
    private final String NEO_TAG_FOR = "<for>";
    private final String NEO_TAG_ITERATE = "<iterate>";
    
    public NeoContextFreeGrammar() {
        clearGrammar();
        initGrammar();
    }
    
    @Override
    protected final void initGrammar() {
        setStartTag(NEO_TAG_START);
        
        addRules(NEO_TAG_START, Arrays.asList(NEO_TAG_IMPORT, NEO_TAG_NAMESPACE));
        addRules(NEO_TAG_IMPORT, Arrays.asList(Token.IMPORT.toString(), Token.SEMICOLON.toString()));
        addRules(NEO_TAG_NAMESPACE, Arrays.asList(Token.RESERVED_WORD_NAMESPACE.toString(), Token.IDENTIFIER.toString(), Token.DELIMITER_BRACKET_OPEN.toString(), NEO_TAG_MAIN, Token.DELIMITER_BRACKET_CLOSE.toString()));
        addRules(NEO_TAG_MAIN, Arrays.asList(Token.RESERVED_WORD_VOID.toString(), Token.MAIN.toString(), Token.DELIMITER_PARENTHESIS_OPEN.toString(), Token.DELIMITER_PARENTHESIS_CLOSE.toString(), Token.DELIMITER_BRACKET_OPEN.toString(), Token.DELIMITER_BRACKET_CLOSE.toString()));
        //addRule(NEO_TAG_FUNCTION_CALL, Token);
    }
    
}
