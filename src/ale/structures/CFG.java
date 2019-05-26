package ale.structures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CFG {
    
    private final Map<String, List<String>> grammar = new LinkedHashMap<>();
    private List<String> expansion = new ArrayList<>();
    private String startTag;
    
    public CFG() {
        //initGrammar();
        initGrammar2();
    }
    
    private void initGrammar() {
        startTag = "<if>";
        
        addRule("<if>", "if <condition> { <statement-list> }");
        //addRule("<if>", "if <condition> { <statement-list> } else { <statement-list> }");
        
        addRule("<condition>", "<id> <relational-operator> <id>");
        addRule("<condition>", "<id> <relational-operator> <number>");
        addRule("<condition>", "<id> <relational-operator> <string>");
        addRule("<condition>", "<number> <relational-operator> <id>");
        addRule("<condition>", "<number> <relational-operator> <number>");
        addRule("<condition>", "<number> <relational-operator> <string>");
        addRule("<condition>", "<string> <relational-operator> <id>");
        addRule("<condition>", "<string> <relational-operator> <number>");
        addRule("<condition>", "<string> <relational-operator> <string>");
        
        addRule("<id>", "var1");
        addRule("<id>", "var2");
        addRule("<id>", "var3");
        addRule("<id>", "var4");
        addRule("<id>", "var5");
        addRule("<id>", "var6");
        
        addRule("<relational-operator>", ">");
        addRule("<relational-operator>", "<");
        addRule("<relational-operator>", ">=");
        addRule("<relational-operator>", "<=");
        addRule("<relational-operator>", "==");
        addRule("<relational-operator>", "<>");
        
        addRule("<number>", "0");
        addRule("<number>", "1");
        addRule("<number>", "2");
        addRule("<number>", "3");
        addRule("<number>", "4");
        addRule("<number>", "5");
        addRule("<number>", "6");
        addRule("<number>", "7");
        addRule("<number>", "8");
        addRule("<number>", "9");
        
        addRule("<string>", "\"This is a string\"");
        addRule("<string>", "\"Hello world\"");
        addRule("<string>", "\"Lorem ipsum lorem ipsum lorem ipsum\"");
        addRule("<string>", "\"Hey there :v\"");
        addRule("<string>", "\"Greetings earthling!\"");
        
        addRule("<statement-list>", "<statement> <statement-list>");
        addRule("<statement-list>", "<empty>");
        
        addRule("<statement>", "<id> = <id> ;");
        addRule("<statement>", "<id> = <number> ;");
        addRule("<statement>", "<id> = <call> ;");
        
        addRule("<empty>", "");
        
        addRule("<call>", "<id> ( )");
        addRule("<call>", "<id> :: <id> ( )");
        addRule("<call>", "<id> . <id> ( )");
    }
    
    private void initGrammar2() {
        startTag = "<start>";
        
        addRule("<start>", "<declaration> ;");
        
        addRule("<declaration>", "<type> <id>");
        addRule("<declaration>", "<type> <id> = <number>");
        addRule("<declaration>", "<type> <id> = <string>");
        addRule("<declaration>", "<type> <id> = <char>");
        
        addRule("<type>", "int");
        addRule("<type>", "float");
        addRule("<type>", "double");
        addRule("<type>", "char");
        addRule("<type>", "bool");
        addRule("<type>", "string");
        
        addRule("<id>", "var1");
        addRule("<id>", "var2");
        addRule("<id>", "var3");
        addRule("<id>", "var4");
        addRule("<id>", "var5");
        
        addRule("<number>", "0");
        addRule("<number>", "1");
        addRule("<number>", "2");
        addRule("<number>", "3");
        addRule("<number>", "4");
        addRule("<number>", "5");
        addRule("<number>", "6");
        addRule("<number>", "7");
        addRule("<number>", "8");
        addRule("<number>", "9");
        
        addRule("<string>", "\"This is a string\"");
        addRule("<string>", "\"Hello world\"");
        addRule("<string>", "\"Lorem ipsum lorem ipsum lorem ipsum\"");
        addRule("<string>", "\"Hey there :v\"");
        addRule("<string>", "\"Greetings earthling!\"");
        
        addRule("<char>", "\'a\'");
        addRule("<char>", "\'e\'");
        addRule("<char>", "\'i\'");
        addRule("<char>", "\'o\'");
        addRule("<char>", "\'u\'");
    }
    
    public List<String> getExpansion() {
        return expansion;
    }
    
    public void setExpansion(List<String> expansion) {
        this.expansion = expansion;
    }
    
    public String getStartTag() {
        return startTag;
    }

    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }
    
    public List<String> getRulesInTag(String tag) {
        if (!grammar.containsKey(tag))
            return null;
        
        return grammar.get(tag);
    }
    
    public void addRule(String tag, String rule) {
        if (!grammar.containsKey(tag))
            grammar.put(tag, new ArrayList<>());
        
        List<String> rules = grammar.get(tag);
        rules.add(rule);
    }
    
    public boolean containsTag(String tag) {
        return grammar.containsKey(tag);
    }
    
    public void expand() {
        expansion.clear();
        expand(startTag);
    }
    
    public void expand(String tag) {
        if (!grammar.containsKey(tag))
            return;
        
        List<String> rules = grammar.get(tag);
        int index = ale.Utils.getRandomRangeI(0, rules.size() - 1);
        
        for (String word : rules.get(index).split(" ")) {
            if (!grammar.containsKey(word) && !word.isEmpty()) {
                expansion.add(word);
            } else {
                expand(word);
            }
        }
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        grammar.forEach((key, value) -> {
            value.forEach((word) -> {
                s.append(key).append(" => ").append(word).append(" ").append("\n");
            });
        });
        
        return s.toString();
    }
    
}
