package ale.compiler.parser;

import ale.compiler.parser.earley.Chart;
import ale.compiler.parser.earley.ContextFreeGrammar;
import ale.compiler.parser.earley.EarleyParser;
import ale.compiler.parser.earley.NeoContextFreeGrammar;
import java.util.Arrays;

public class NeoParser {
    
    private final ContextFreeGrammar grammar = new NeoContextFreeGrammar();
    private final EarleyParser parser = new EarleyParser(grammar);
    
    public boolean parse(String[] tokens) {
        System.out.println("Parsing: " + Arrays.asList(tokens));
        
        boolean success = parser.parse(tokens);
        
        Chart[] charts = parser.getCharts();
        System.out.println("Charts produced by the sentence:\n");

        for (int i = 0; i < charts.length; i++)
        {
            System.out.println("Chart[" + i + "]");
            System.out.println(charts[i].toString());
        }

        System.out.println("=============================================================");
        System.out.println("=============================================================");
        if (success)
            System.out.println("Parse SUCCESSFUL! :D");
        else
            System.out.println("Parse FAILED :(");
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        
        return success;
    }
    
}
