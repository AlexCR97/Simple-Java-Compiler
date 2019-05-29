package ale.compiler.parser.earley;

public class EarleyParser {
    
    private String[] sentence;
    
    private ContextFreeGrammar grammar;
    public ContextFreeGrammar getGrammar() {
        return grammar;
    }
    
    private Chart[] charts;
    public Chart[] getCharts() {
        return charts;
    }
    
    public EarleyParser(ContextFreeGrammar grammar) {
        this.grammar = grammar;
    }
    
    public boolean parse(String[] tokens) {
        sentence = tokens;
        charts = new Chart[sentence.length + 1];
        
        for (int i = 0; i < charts.length; i++)
            charts[i] = new Chart();
        
        String[] start1 = {"@", "S"};
        RHS startRHS = new RHS(start1);
        State start = new State("$", startRHS, 0, 0);
        
        charts[0].addState(start);
        
        for (int i = 0; i < charts.length; i++) {
            for (int j = 0; j < charts[i].size(); j++) {
                State state = charts[i].getState(j);
                String nextTerm = state.getAfterDot();
                
                if (state.isDotLast())
                    completer(state);
                
                else if (grammar.isPartOfSpeech(nextTerm))
                    scanner(state);
                
                else
                    predictor(state);
            }
        }
        
        String[] end = {"S", "@"};
        RHS endRHS = new RHS(end);
        State endState = new State("$", endRHS, 0, sentence.length);
        
        for (int j = 0; j < charts[sentence.length].size(); j++) {
            State state = charts[sentence.length].getState(j);
            
            if (state.equals(endState))
                return true;
        }
        
        return false;
    }
    
    private void predictor(State state) {
        String lhs = state.getAfterDot();
        RHS[] rhs = grammar.getRHS(lhs);
        int j = state.getJ();
        
        for (int i = 0; i < rhs.length; i++) {
            State newState = new State(lhs, rhs[i].addDot(), j, j);
            charts[j].addState(newState);
        }
    }
    
    private void scanner(State state) {
        String lhs = state.getAfterDot();
        RHS[] rhs = grammar.getRHS(lhs);
        int i = state.getI();
        int j = state.getJ();
        
        for (int a = 0; a < rhs.length; a++) {
            String[] terms = rhs[a].getTerms();
            int count = terms.length;
            
            if (terms.length == count && j < sentence.length) {
                for (int k = 0; k < count; k++) {
                    String term = terms[k].toLowerCase();
                    String sent = sentence[j].toLowerCase();
                    
                    if (term.compareTo(sent) == 0) {
                        State newState = new State(lhs, rhs[a].addDotLast(), j, j + 1);
                        charts[j + 1].addState(newState);
                    }
                }
            }
        }
    }
    
    private void completer(State state) {
        String lhs = state.getLhs();
        
        for (int index = 0; index < charts[state.getI()].size(); index++) {
            State oldState = charts[state.getI()].getState(index);
            String after = oldState.getAfterDot();
            
            if (!after.isEmpty() && lhs.compareTo(after) == 0) {
                State newState = new State(oldState.getLhs(), oldState.getRhs().moveDot(), oldState.getI(), state.getJ());
                charts[state.getJ()].addState(newState);
            }
        }
    }
    
}
