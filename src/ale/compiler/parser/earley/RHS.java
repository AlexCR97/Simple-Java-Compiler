package ale.compiler.parser.earley;

public class RHS {
    
    private final String DOT = "@";
    private int dot = -1;
    
    private boolean hasDot = false;
    public boolean hasDot() {
        return hasDot;
    }

    private final String[] terms;
    public String[] getTerms() {
        return terms;
    }
    
    public RHS(String[] terms) {
        this.terms = terms;
        
        for (int i = 0; i < terms.length; i++) {
            if (terms[i].compareTo(DOT) == 0) {
                dot = i;
                hasDot = true;
                break;
            }
        }
    }
    
    public String getAfterDot() {
        if (hasDot && dot < terms.length - 1)
            return terms[dot + 1];
        
        return "";
    }
    
    public String getBeforeDot() {
        if (hasDot && dot > 0)
            return terms[dot - 1];
        
        return "";
    }
    
    public boolean isDotFirst() {
        if (hasDot)
            return dot == 0;
        
        return false;
    }
    
    public boolean isDotLast() {
        if (hasDot)
            return dot == terms.length - 1;
        
        return false;
    }
    
    public RHS addDot() {
        String[] newTerms = new String[terms.length + 1];
        newTerms[0] = DOT;
        
        for (int i = 1; i < newTerms.length; i++)
            newTerms[i] = terms[i - 1];
        
        return new RHS(newTerms);
    }
    
    public RHS addDotLast() {
        String[] newTerms = new String[terms.length + 1];
        
        for (int i = 0; i < newTerms.length - 1; i++)
            newTerms[i] = terms[i];
        
        newTerms[newTerms.length - 1] = DOT;
        
        return new RHS(newTerms);
    }
    
    public RHS moveDot() {
        String[] newTerms = new String[terms.length];
        
        for (int i = 0; i < newTerms.length; i++) {
            if (terms[i].compareTo(DOT) == 0) {
                newTerms[i] = terms[i + 1];
                newTerms[i + 1] = DOT;
                i++;
            } else {
                newTerms[i] = terms[i];
            }
        }
        
        return new RHS(newTerms);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return this == null;
        
        RHS rhs = (RHS) object;
        
        if (terms.length != rhs.terms.length)
            return false;
        
        for (int i = 0; i < terms.length; i++)
            if (terms[i].compareTo(rhs.terms[i]) != 0)
                return false;
        
        return dot == rhs.dot;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        for (String term : terms)
            str.append(term).append(" ");
        
        return str.toString();
    }
    
}
