package ale.structures;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Automatan {
    
    private List<String> states;
    private List<String> alphabet;
    private String initialState;
    private List<String> finalStates;
    private Map<String, Map<String, String>> transitions = new LinkedHashMap<>();

    public Automatan(List<String> states, List<String> alphabet, String initialState, List<String> finalStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.initialState = initialState;
        this.finalStates = finalStates;
    }
    
    public void addTransition(String from, String symbol, String to) {
        if (!alphabet.contains(symbol))
            return;
        
        if (!transitions.containsKey(from))
            transitions.put(from, new LinkedHashMap<>());
        
        transitions.get(from).put(symbol, to);
    }
    
    public boolean validate(String input) {
        String currentState = initialState;
        for (int i = 0; i < input.length(); i++) {
            String currentSymbol = String.valueOf(input.charAt(i));
            currentState = transitions.get(currentState).get(currentSymbol);
        }
        return finalStates.contains(currentState);
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
