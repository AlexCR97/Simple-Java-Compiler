package ale.compiler.parser;

import ale.compiler.parser.syntax.ConstantDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.FunctionCallSyntaxAutomatan;
import ale.compiler.parser.syntax.StatementSyntaxAutomatan;
import ale.compiler.parser.syntax.SyntaxAutomatan;
import ale.compiler.parser.syntax.VariableDynamicDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.VariableStaticDeclarationSyntaxAutomatan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    
    private static final Map<String, SyntaxAutomatan> AUTOMATONS = new HashMap<>();
    static {
        AUTOMATONS.put("<statement>", new StatementSyntaxAutomatan());
        AUTOMATONS.put("<dynamic-declaration>", new VariableDynamicDeclarationSyntaxAutomatan());
        AUTOMATONS.put("<static-declaration>", new VariableStaticDeclarationSyntaxAutomatan());
        AUTOMATONS.put("<const-declaration>", new ConstantDeclarationSyntaxAutomatan());
        AUTOMATONS.put("<function-call>", new FunctionCallSyntaxAutomatan());
    }
    
    private List<String> tokens;
    private String startTag = "<statement>";
    
    public Parser(List<String> tokens) {
        this.tokens = tokens;
    }
    
    public boolean parse() {
        System.out.println("PARSING " + tokens);
        
        boolean success = parse(startTag, 0, null);
        
        if (success)
            System.out.println("Parsing was SUCCESSFUL! :D");
        else
            System.out.println("Parsing FAILED! :(");
        
        return success;
    }
    
    public boolean parse(String tag, int index, SyntaxAutomatan sourceAutomatan) {
        if (!AUTOMATONS.containsKey(tag)) {
            System.out.println("Could not find syntax automaton with tag '" + tag + "'");
            System.out.println("-------------------------------------------------");
            return false;
        }
        
        SyntaxAutomatan currentAutomatan = AUTOMATONS.get(tag);
        
        for (int i = index; i < tokens.size(); i++) {
            String currentToken = tokens.get(i);
            
            currentAutomatan.validateStep(currentToken);
            
            switch (currentAutomatan.getCondition()) {
                case DERIVATION: {
                    System.out.println("~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~");
                    List<String> transitionTags = currentAutomatan.getLastDerivations();
                    System.out.println("Derivating to automatons with tags '" + transitionTags + "' *~*");
                    System.out.println("~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~ o ~");
                    for (String transitionTag : transitionTags)
                        parse(transitionTag, i, currentAutomatan);
                    break;
                }
                
                case INVALID_SYNTAX: {
                    return false;
                }
                
                case VALID_SYNTAX: {
                    break;
                }
                
                case ON_FINAL_STATE: {
                    System.out.println("! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - !");
                    System.out.println("ON FINAL STATE");
                    System.out.println("INDEX = " + i);
                    System.out.println("TOKENS SIZE = " + tokens.size());
                    System.out.println("! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - !");
                    
                    //if (sourceAutomatan != null)
                        //sourceAutomatan.forceValidate();
                    
                    break;
                }
                
                case ON_ABSOLUTE_FINAL_STATE: {
                    System.out.println("! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - !");
                    System.out.println("ON ABSOLUTE FINAL STATE");
                    System.out.println("INDEX = " + i);
                    System.out.println("TOKENS SIZE = " + tokens.size());
                    System.out.println("! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - ! - !");
                    
                    if (sourceAutomatan != null) {
                        sourceAutomatan.forceValidate();
                        //tokens = tokens.subList(i, tokens.size());
                    }
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
