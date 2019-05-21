package ale.structures;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class StaticContextFreeGrammar {
    
    private static final Map<String, String[][]> GRAMMAR = new LinkedHashMap<>();
    static {
        GRAMMAR.put("<s>", new String[][] {
            {"There was once", "<a>", "<b>"},
            //"There was once,<a>,<b>".split(","),
        });
        GRAMMAR.put("<a>", new String[][] {
            {"the"},
            {"a"},
        });
        GRAMMAR.put("<b>", new String[][] {
            {"<c>", "<d>"},
        });
        GRAMMAR.put("<c>", new String[][] {
            {"happy"},
            {"sad"},
            {"wild"},
            {"crazy"},
        });
        GRAMMAR.put("<d>", new String[][] {
            {"cat"},
            {"dog"},
            {"lizard"},
            {"horse"},
        });
    }
    
    public List<String> expand(String start) {
        List<String> expansion = new ArrayList<>();
        expand(start, expansion);
        return expansion;
    }
    
    private void expand(String start, List<String> expansion) {
        if (GRAMMAR.containsKey(start)) {
            String[][] possible = GRAMMAR.get(start);
            int index = getRandomRangeI(0, possible.length - 1);
            String[] randomExpansion = possible[index];
            
            for (int i = 0; i < randomExpansion.length; i++)
                expand(randomExpansion[i], expansion);
        } else
            expansion.add(start);
    }
    
    private int getRandomRangeI(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
}
