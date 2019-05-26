package ale.structures;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SyntaxAutomatan {
    
    private List<String> states;
    private List<String> alphabet;
    private String initialState;
    private List<String> finalStates;
    private Map<String, Map<String, String>> transitions = new LinkedHashMap<>();

    public SyntaxAutomatan(List<String> states, List<String> alphabet, String initialState, List<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }
    
    public SyntaxAutomatan(CFG grammar) {
        
    }
    
    public void addTransition(String from, String symbol, String to) {
        if (!alphabet.contains(symbol))
            return;
        
        if (!transitions.containsKey(from))
            transitions.put(from, new LinkedHashMap<>());
        
        transitions.get(from).put(symbol, to);
    }
    
    public boolean validate(String sentence) {
        System.out.println("Validating sentence: '" + sentence + "'");
        String currentState = initialState;
        
        for (String word : sentence.split(" ")) {
            if (!transitions.containsKey(currentState)) {
                System.out.println("Sentence validation is: " + false);
                System.out.println("-------------------------------------------------");
                return false;
            }
            
            if (!transitions.get(currentState).containsKey(word)) {
                System.out.println("Sentence validation is: " + false);
                System.out.println("-------------------------------------------------");
                return false;
            }
            
            currentState = transitions.get(currentState).get(word);
        }
        
        boolean success = finalStates.contains(currentState);
        System.out.println("Sentence validation is: " + success);
        System.out.println("-------------------------------------------------");
        
        return success;
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        transitions.forEach((from, transitionStates) -> {
            transitionStates.forEach((symbol, to) -> {
                s.append("(").append(from).append(", ").append(symbol).append(") = ").append(to).append("\n");
            });
        });
        
        return s.toString();
    }
    
}
