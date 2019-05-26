package ale.compiler.parser;

import ale.compiler.parser.syntax.StatementSyntaxAutomatan;
import ale.compiler.parser.syntax.SyntaxAutomatan;
import ale.compiler.parser.syntax.VariableDynamicDeclarationSyntaxAutomatan;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Parser {
    
    private static final Map<String, SyntaxAutomatan> AUTOMATONS = new HashMap<>();
    static {
        AUTOMATONS.put("<statement>", new StatementSyntaxAutomatan());
        AUTOMATONS.put("<dynamic-declaration>", new VariableDynamicDeclarationSyntaxAutomatan());
    }
    
    private Queue<String> tokensQueue;
    private String startTag = "<statement>";
    
    public Parser(List<String> tokens) {
        tokensQueue = new LinkedList<>(tokens);
    }
    
    public boolean parse() {
        System.out.println("PARSING " + tokensQueue);
        
        boolean success = parse(startTag);
        
        if (success)
            System.out.println("Parsing was SUCCESSFUL! :D");
        else
            System.out.println("Parsing FAILED! :(");
        
        return success;
    }
    
    public boolean parse(String tag) {
        if (!AUTOMATONS.containsKey(tag)) {
            System.out.println("Could not find syntax automaton with tag '" + tag + "'");
            return false;
        }
        
        SyntaxAutomatan a = AUTOMATONS.get(tag);
        
        while (!tokensQueue.isEmpty()) {
            String currentToken = tokensQueue.peek();
            System.out.println("Current token: " + currentToken);
            
            a.validateStep(currentToken);
            
            switch (a.getCondition()) {
                case DERIVATION: {
                    
                    break;
                }
                
                case INVALID_SYNTAX: {
                    return false;
                }
                
                case VALID_SYNTAX: {
                    tokensQueue.poll();
                    break;
                }
            }
        }
        
        return true;
    }
    
}
