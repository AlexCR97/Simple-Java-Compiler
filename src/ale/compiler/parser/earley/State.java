package ale.compiler.parser.earley;

public class State {
    
    private final String lhs;
    public String getLhs() {
        return lhs;
    }
    
    private final RHS rhs;
    public RHS getRhs() {
        return rhs;
    }
    
    private final int i;
    public int getI() {
        return i;
    }
    
    private final int j;
    public int getJ() {
        return j;
    }
    
    public State(String lhs, RHS rhs, int i, int j) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.i = i;
        this.j = j;
    }
    
    public String getAfterDot() {
        return rhs.getAfterDot();
    }
    
    public String getBeforeDot() {
        return rhs.getBeforeDot();
    }
    
    public boolean isDotLast() {
        return rhs.isDotLast();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return this == null;
        
        State state = (State) object;
        boolean checkLhs = lhs.compareTo(state.lhs) == 0;
        boolean checkRhs = rhs.equals(state.rhs);
        
        return checkLhs && checkRhs && i == state.i && j == state.j;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        return str.append(lhs).append(" ").append(rhs.toString()).append(" ").append(i).append(" ").append(j).toString();
    }
    
}
