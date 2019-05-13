package ale.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class DynamicContextFreeGrammar {
    
    private final Map<String, List<List<String>>> GRAMMAR = new LinkedHashMap<>();
    
    public DynamicContextFreeGrammar() {
        addRules("<s>", Arrays.asList("There was once", "<a>", "<b>"));
        
        addRule("<a>", "the");
        addRule("<a>", "a");
        
        addRules("<b>", Arrays.asList("<c>", "<d>"));
        
        addRule("<c>", "happy");
        addRule("<c>", "sad");
        addRule("<c>", "wild");
        addRule("<c>", "crazy");
        
        addRule("<d>", "cat");
        addRule("<d>", "dog");
        addRule("<d>", "lizard");
        addRule("<d>", "horse");
    }
    
    public void addRule(String tag, String rule) {
        if (!GRAMMAR.containsKey(tag))
            GRAMMAR.put(tag, new ArrayList<>());
        
        List<String> newRules = new ArrayList<>();
        newRules.add(rule);
        
        List<List<String>> grammarRules = GRAMMAR.get(tag);
        grammarRules.add(newRules);
    }
    
    public void addRules(String tag, List<String> rules) {
        if (!GRAMMAR.containsKey(tag))
            GRAMMAR.put(tag, new ArrayList<>());
        
        List<List<String>> grammarRules = GRAMMAR.get(tag);
        grammarRules.add(rules);
    }
    
    public List<String> expand(String start) {
        List<String> expansion = new ArrayList<>();
        expand(start, expansion);
        return expansion;
    }
    
    private void expand(String start, List<String> expansion) {
        if (GRAMMAR.containsKey(start)) {
            List<List<String>> possible = GRAMMAR.get(start);
            int index = getRandomRangeI(0, possible.size() - 1);
            List<String> randomExpansion = possible.get(index);
            
            for (int i = 0; i < randomExpansion.size(); i++)
                expand(randomExpansion.get(i), expansion);
        } else
            expansion.add(start);
    }
    
    private int getRandomRangeI(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
}
