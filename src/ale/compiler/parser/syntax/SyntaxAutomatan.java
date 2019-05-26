package ale.compiler.parser.syntax;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class SyntaxAutomatan {
    
    public enum STATE {
        INVALID_TOKEN,
        VALID_TOKEN,
        DERIVATION
    }
    
    protected String initialState;
    protected String currentState;
    protected List<String> finalStates;
    protected Map<String, Map<String, String>> transitions = new LinkedHashMap<>();

    private STATE state = STATE.VALID_TOKEN;
    
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
    
    public boolean isOnFinalState() {
        return finalStates.contains(currentState);
    }
    
    public boolean validateStep(String word) {
        System.out.println("STEP VALIDATION on word: " + word);
        
        if (currentState == null)
            currentState = initialState;
        
        System.out.println("Current state: " + currentState);
        
        if (isOnFinalState()) {
            System.out.println("Already on final state!");
            System.out.println("-------------------------------------------------");
            return true;
        }
        
        String match = getMatchingTransition(currentState, word);
        
        if (match == null) {
            System.out.println("Could not find transition with word '" + word + "' from state '" + currentState + "' :(");
            System.out.println("Word validation is: FALSE");
            System.out.println("-------------------------------------------------");
            return false;
        }
        
        System.out.println("Found transition with word '" + word + "' from state '" + currentState + "' :D");
        currentState = transitions.get(currentState).get(match);
        System.out.println("New state is : " + currentState);
        System.out.println("Word validation is: TRUE");
        System.out.println("-------------------------------------------------");
        
        return true;
    }
    
    // GOOD ONE
    /*public boolean validateAll(List<String> sentence) {
        System.out.println("Validating sentence: '" + sentence + "'");
        currentState = initialState;
        
        for (String word : sentence) {
            String match = getMatchingTransition(currentState, word);
            
            if (match == null) {
                System.out.println("Could not find transition with '" + word + "' from '" + currentState + "'");
                System.out.println("Sentence validation is: FALSE");
                System.out.println("-------------------------------------------------");
                return false;
            }
            
            currentState = transitions.get(currentState).get(match);
        }
        
        boolean success = finalStates.contains(currentState);
        if (success) {
            System.out.println("Sentence validation is: TRUE");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("Sentence validation is: FALSE");
            System.out.println("-------------------------------------------------");
        }
        
        return success;
    }*/
    
    public boolean validateAll(List<String> sentence) {
        System.out.println("COMPLETE VALIDATION on: " + sentence);
        
        for (String word : sentence) {
            if (!validateStep(word))
                break;
        }
        
        boolean success = isOnFinalState();
        
        if (success) {
            System.out.println("Sentence validation is: TRUE");
            System.out.println("-------------------------------------------------");
        } else {
            System.out.println("Sentence validation is: FALSE");
            System.out.println("-------------------------------------------------");
        }
        
        return success;
    }
    
    private String getMatchingTransition(String state, String word) {
        System.out.println("Checking if '" + state + "' has any transition matching regex '" + word + "'...");
        
        if (!transitions.containsKey(state))
            return null;
        
        for (Map.Entry<String, String> i : transitions.get(state).entrySet()) {
            if (ale.Utils.regexValidate(i.getKey(), word))
                return i.getKey();
        }
        
        return null;
    }
    
    private boolean isTag(String word) {
        return word.charAt(0) == '<' && word.charAt(word.length() - 1) == '>';
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
