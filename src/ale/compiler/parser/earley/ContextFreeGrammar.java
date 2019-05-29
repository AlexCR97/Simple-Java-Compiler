package ale.compiler.parser.earley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ContextFreeGrammar {
    
    private final Map<RHS[], String> rules = new HashMap<>();
    private final List<String> pos = new ArrayList<>();
    
    public ContextFreeGrammar() {
        init();
    }

    private void init() {
        initStartRules();
        initProductionRules();
        initPos();
    }
    
    protected abstract void initStartRules();
    protected abstract void initProductionRules();
    protected abstract void initPos();
    
    public RHS[] getRHS(String lhs) {
        if (rules.containsValue(lhs))
            for (Map.Entry<RHS[], String> i : rules.entrySet())
                if (i.getValue().equals(lhs))
                    return i.getKey();
        
        return null;
    }
    
    public boolean isPartOfSpeech(String word) {
        return pos.contains(word);
    }
    
    public void addStartRule(String rule) {
        String[] words = rule.split(" ");
        addStartRule(words);
    }

    public void addStartRule(String[] words) {
        RHS[] rhs = { new RHS(words) };
        rules.put(rhs, "S");
    }
    
    public void addStartRules(List<String> rules) {
        RHS[] rhs = new RHS[rules.size()];
        
        for (int i = 0; i < rules.size(); i++) {
            String[] words = rules.get(i).split(" ");
            rhs[i] = new RHS(words);
        }
        
        this.rules.put(rhs, "S");
    }
    
    public void addStartRulesFromArrays(List<String[]> rules) {
        RHS[] rhs = new RHS[rules.size()];
        
        for (int i = 0; i < rules.size(); i++) {
            String[] words = rules.get(i);
            rhs[i] = new RHS(words);
        }
        
        this.rules.put(rhs, "S");
    }
    
    public void addProductionRule(String tag, String rule) {
        String[] words = rule.split(" ");
        addProductionRule(tag, words);
    }

    public void addProductionRule(String tag, String[] words) {
        RHS[] rhs = { new RHS(words) };
        rules.put(rhs, tag);
    }
    
    public void addProductionRules(String tag, List<String> rules) {
        RHS[] rhs = new RHS[rules.size()];
        
        for (int i = 0; i < rules.size(); i++) {
            String[] words = rules.get(i).split(" ");
            rhs[i] = new RHS(words);
        }
        
        this.rules.put(rhs, tag);
    }
    
    public void addProductionRulesFromArrays(String tag, List<String[]> rules) {
        RHS[] rhs = new RHS[rules.size()];
        
        for (int i = 0; i < rules.size(); i++) {
            String[] words = rules.get(i);
            rhs[i] = new RHS(words);
        }
        
        this.rules.put(rhs, tag);
    }
    
    public void addPos(String pos) {
        this.pos.add(pos);
    }
    
}
