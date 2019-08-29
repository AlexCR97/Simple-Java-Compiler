package ale.compiler.parser.earley;

import java.util.ArrayList;
import java.util.List;

public class Chart {
    
    private final List<State> chart = new ArrayList<>();
    
    public void addState(State state) {
        if (!chart.contains(state))
            chart.add(state);
    }
    
    public State getState(int index) {
        if (index < 0 || index >= chart.size())
            return null;
        
        return chart.get(index);
    }
    
    public int size() {
        return chart.size();
    }
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        
        chart.forEach((state) -> {
            str.append(state.toString()).append("\r\n");
        });
        
        return str.toString();
    }
    
}
