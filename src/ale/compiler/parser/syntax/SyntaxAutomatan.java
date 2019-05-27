package ale.compiler.parser.syntax;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class SyntaxAutomatan {
    
    public static enum Condition {
        INVALID_SYNTAX,
        VALID_SYNTAX,
        DERIVATION,
        ON_FINAL_STATE,
        ON_ABSOLUTE_FINAL_STATE
    }
    
    protected String initialState;
    protected String currentState;
    protected List<String> finalStates;
    protected List<String> currentDerivations = new ArrayList<>();
    protected Map<String, Map<String, String>> transitions = new LinkedHashMap<>();

    private Condition condition = Condition.VALID_SYNTAX;
    
    public SyntaxAutomatan() {
        init();
    }
    
    public SyntaxAutomatan(String initialState, List<String> finalStates) {
        this.initialState = initialState;
        this.finalStates = finalStates;
    }
    
    public abstract void init();
    
    public void addTransition(String from, String symbol, String to) {
        if (!transitions.containsKey(from))
            transitions.put(from, new LinkedHashMap<>());
        
        transitions.get(from).put(symbol, to);
    }
    
    public void forceValidate() {
        int state = Integer.parseInt(this.currentState) + 1;
        this.currentState = String.valueOf(state);
        
        currentDerivations = new ArrayList<>();
    }
    
    public void validateStep(String word) {
        System.out.println("STEP VALIDATION on word '" + word + "' from '" + getClass().getSimpleName() + "'");
        
        if (currentState == null)
            currentState = initialState;
        
        System.out.println("Current state: " + currentState);
        
        String match = getMatchingTransition(currentState, word);
        
        if (match == null) {
            
            if (condition == Condition.DERIVATION) {
                System.out.println("Found derivations " + currentDerivations);
                return;
            }
            
            System.out.println("Could not find transition with word '" + word + "' from state '" + currentState + "' :(");
            System.out.println("Word validation is: FALSE");
            System.out.println("-------------------------------------------------");
            condition = Condition.INVALID_SYNTAX;
            return;
        }
        
        System.out.println("Found transition with word '" + word + "' from state '" + currentState + "' :D");
        currentState = transitions.get(currentState).get(match);
        
        System.out.println("New state is : " + currentState);
        System.out.println("Word validation is: TRUE");
        System.out.println("-------------------------------------------------");
        
        if (isOnAbsoluteFinalState())
            condition = Condition.ON_ABSOLUTE_FINAL_STATE;
        else if (isOnFinalState())
            condition = Condition.ON_FINAL_STATE;
        else
            condition = Condition.VALID_SYNTAX;
    }
    
    public void validateAll(List<String> sentence) {
        reset();
        
        System.out.println("COMPLETE VALIDATION on: " + sentence);
        
        for (String word : sentence) {
            validateStep(word);
            
            if (condition == Condition.INVALID_SYNTAX) {
                System.out.println("Encountered invalid syntax :(");
                System.out.println("Sentence validation is: FALSE");
                System.out.println("-------------------------------------------------");
                return;
            }
        }
        
        boolean success = isOnFinalState();
        
        if (success) {
            System.out.println("Sentence validation is: TRUE");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("Sentence validation is: FALSE");
            System.out.println("-------------------------------------------------");
        }
    }
    
    public Condition getCondition() {
        return condition;
    }
    
    public List<String> getLastDerivations() {
        return currentDerivations;
    }
    
    private String getMatchingTransition(String state, String word) {
        System.out.println("Checking if '" + state + "' has any transition matching regex '" + word + "'...");
        
        if (!transitions.containsKey(state))
            return null;
        
        currentDerivations = new ArrayList<>();
        
        for (Map.Entry<String, String> i : transitions.get(state).entrySet()) {
            String transitionString = i.getKey();
            
            if (isTag(transitionString)) {
                currentDerivations.add(transitionString);
                continue;
            }
            
            if (ale.Utils.regexValidate(transitionString, word))
                return i.getKey();
        }
        
        if (!currentDerivations.isEmpty())
            condition = Condition.DERIVATION;
        
        return null;
    }
    
    public boolean isOnFinalState() {
        return finalStates.contains(currentState);
    }
    
    public boolean isOnAbsoluteFinalState() {
        return isOnFinalState() && !transitions.containsKey(currentState);
    }
    
    private boolean isTag(String word) {
        return word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>';
    }
    
    public void reset() {
        System.out.println("Just reset automaton!");
        currentState = null;
        condition = Condition.VALID_SYNTAX;
        currentDerivations = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String className = getClass().getSimpleName();
        
        s.append(className).append(":\n");
        
        transitions.forEach((from, transitionStates) -> {
            transitionStates.forEach((symbol, to) -> {
                s.append("(").append(from).append(", ").append(symbol).append(") = ").append(to).append("\n");
            });
        });
        
        return s.toString();
    }
    
}
