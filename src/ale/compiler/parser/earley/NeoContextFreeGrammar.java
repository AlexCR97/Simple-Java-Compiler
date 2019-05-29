package ale.compiler.parser.earley;

import java.util.Arrays;
import java.util.List;

public class NeoContextFreeGrammar extends ContextFreeGrammar {

    @Override
    protected void initStartRules() {
        List<String> rules = Arrays.asList(
                "if ( <condition> ) { <statement> }",
                "if ( <condition> ) { <statement> } else { <statement> }"
        );
        this.addStartRules(rules);
    }

    @Override
    protected void initProductionRules() {
        List<String> conditionRules = Arrays.asList(
                "<id> <relational-operator> <id>",
                "<id> <relational-operator> <number>",
                "<number> <relational-operator> <number>"
        );
        addProductionRules("<condition>", conditionRules);

        addProductionRule("<id>", "var0 var1 var2 var3 var4 var5 var6 var7 var8 var9");

        addProductionRule("<relational-operator>", "> < == >= <= !=");

        addProductionRule("<number>", "0 1 2 3 4 5 6 7 8 9");

        List<String> statementRules = Arrays.asList(
                "<type> <id> ;",
                "<type> <id> = <id> ;",
                "<type> <id> = <number> ;",
                "<type> <id> = <function-call> ;",
                "<id> = <id> ;",
                "<id> = <number> ;",
                "<id> = <function-call> ;",
                "<function-call> ;"
        );
        addProductionRules("<statement>", statementRules);

        addProductionRule("<type>", "int float double char string bool");

        List<String> functionCallRules = Arrays.asList(
                "<id> ( )",
                "<id> . <id> ( )"
        );
        addProductionRules("<function-call>", functionCallRules);

        addProductionRule("if", "if");
        addProductionRule("else", "else");
        addProductionRule("=", "=");
        addProductionRule(";", ";");
        addProductionRule(".", ".");
        addProductionRule("(", "(");
        addProductionRule(")", ")");
        addProductionRule("{", "{");
        addProductionRule("}", "}");
    }

    @Override
    protected void initPos() {
        addPos("<id>");
        addPos("<relational-operator>");
        addPos("<number>");
        addPos("<type>");

        addPos("if");
        addPos("else");
        addPos("=");
        addPos(";");
        addPos(".");
        addPos("(");
        addPos(")");
        addPos("{");
        addPos("}");
    }
    
}
