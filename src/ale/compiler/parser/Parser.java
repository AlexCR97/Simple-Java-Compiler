package ale.compiler.parser;

import ale.compiler.parser.syntax.StatementSyntaxAutomatan;
import ale.compiler.parser.syntax.SyntaxAutomatan;
import ale.compiler.parser.syntax.VariableDynamicDeclarationSyntaxAutomatan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    
    private static final Map<String, SyntaxAutomatan> AUTOMATONS = new HashMap<>();
    static {
        AUTOMATONS.put("<statement>", new StatementSyntaxAutomatan());
        AUTOMATONS.put("<dynamic-declaration>", new VariableDynamicDeclarationSyntaxAutomatan());
    }
    
    private List<String> tokens;
    
    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }
    
    public boolean parse() {
        System.out.println("PARSING " + tokens);
        return parse("<statement>");
    }
    
    public boolean parse(String tag) {
        
        SyntaxAutomatan currentAutomatan = AUTOMATONS.get(tag);
        
        boolean success = currentAutomatan.validateAll(tokens);
        
        if (success) {
            System.out.println("Parse SUCCESSFUL!");
            System.out.println("---------------------------------------------");
        } else {
            System.out.println("Parse FAILED!");
            System.out.println("---------------------------------------------");
        }
        
        return success;
    }
    
    public boolean parse2() {
        return parse2("<statement>");
    }
    
    public boolean parse2(String tag) {
        SyntaxAutomatan currentAutomatan = AUTOMATONS.get(tag);
        
        while (!tokens.isEmpty()) {
            String currentToken = tokens.get(0);
            tokens.remove(0);
            
            
        }
        
        return true;
    }
    
}
