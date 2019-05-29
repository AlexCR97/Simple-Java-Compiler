package ale.compiler.parser.earley;

import java.util.Arrays;
import java.util.List;

public class NeoContextFreeGrammar extends ContextFreeGrammar {

    @Override
    protected void initStartRules() {
        List<String> rules = Arrays.asList(
                "<namespace>",
                "<import-list> <namespace>"
        );
        this.addStartRules(rules);
    }

    @Override
    protected void initProductionRules() {
        List<String> importListRules = Arrays.asList(
                "<import> <import-list>",
                "<import>"
        );
        addProductionRules("<import-list>", importListRules);
        
        List<String> importRules = Arrays.asList(
                "<id> ;",
                "<id> :: <import>"
        );
        addProductionRules("<import>", importRules);
        
        List<String> namespaceRules = Arrays.asList(
                "namespace <id-namespace> { }",
                "namespace <id-namespace> { <main> }"
        );
        addProductionRules("<namespace>", namespaceRules);
        
        List<String> idNamespaceRules = Arrays.asList(
                "<id>",
                "<id> . <id-namespace>",
                "<id> :: <id-namespace>"
        );
        addProductionRules("<id-namespace>", idNamespaceRules);
        
        List<String> mainRules = Arrays.asList(
                "void main ( ) { }",
                "void main ( ) { <statement-list> }"
        );
        addProductionRules("<main>", mainRules);
        
        List<String> statementListRules = Arrays.asList(
                "<statement> <statement-list>",
                "<statement>"
        );
        addProductionRules("<statement-list>", statementListRules);
        
        List<String> statementRules = Arrays.asList(
                "<static-declaration> ;",
                "<dynamic-declaration> ;",
                "<const-declaration> ;",
                "<assignment> ;",
                "<function-call> ;",
                
                "<if>",
                "<switch>",
                "<while>",
                "<for>"
        );
        addProductionRules("<statement>", statementRules);

        List<String> staticDeclarationRules = Arrays.asList(
                "<type> <id>",
                "<type> <id> = <id>",
                "<type> <id> = <number>",
                "<type> <id> = <function-call>"
        );
        addProductionRules("<static-declaration>", staticDeclarationRules);
        
        List<String> dynamicDeclarationRules = Arrays.asList(
                "var <id> = <id>",
                "var <id> = <number>",
                "var <id> = <function-call>"
        );
        addProductionRules("<dynamic-declaration>", dynamicDeclarationRules);
        
        List<String> constDeclarationRules = Arrays.asList(
                "const <id> = <id>",
                "const <id> = <number>",
                "const <id> = <function-call>"
        );
        addProductionRules("<const-declaration>", constDeclarationRules);
        
        List<String> assignmentRules = Arrays.asList(
                "<id> = <id>",
                "<id> = <number>",
                "<id> = <function-call>"
        );
        addProductionRules("<assignment>", assignmentRules);
        
        addProductionRule("<type>", "int float double char string bool");
        
        addProductionRule("<id>", "a b c d e f g h i j k l m n o p q r s t u v w x y z var0 var1 var2 var3 var4 var5 var6 var7 var8 var9 foo lorem ipsum");

        List<String> numberRules = Arrays.asList(
                "<integer>",
                "<float>",
                "<double>"
        );
        addProductionRules("<number>", numberRules);
        
        List<String> integerRules = Arrays.asList(
                "<digit> <integer>",
                "<digit>"
        );
        addProductionRules("<integer>", integerRules);
        
        addProductionRule("<digit>", "0 1 2 3 4 5 6 7 8 9");
        
        List<String> floatRules = Arrays.asList(
                "<integer> f",
                "<integer> . <integer> f"
        );
        addProductionRules("<float>", floatRules);
        
        List<String> doubleRules = Arrays.asList(
                "<integer> d",
                "<integer> . <integer> d"
        );
        addProductionRules("<double>", doubleRules);
        
        List<String> functionCallRules = Arrays.asList(
                "<id> ( )",
                "<id> . <id> ( )",
                "<id> :: <id> ( )"
        );
        addProductionRules("<function-call>", functionCallRules);
        
        List<String> ifRules = Arrays.asList(
                "if ( <condition> ) { }",
                "if ( <condition> ) { } else { }",
                "if ( <condition> ) { <statement-list> }",
                "if ( <condition> ) { <statement-list> } else { <statement-list> }"
        );
        addProductionRules("<if>", ifRules);
        
        List<String> conditionRules = Arrays.asList(
                "<id> <relational-operator> <id>",
                "<id> <relational-operator> <number>",
                "<number> <relational-operator> <number>",
                "<number> <relational-operator> <id>"
        );
        addProductionRules("<condition>", conditionRules);
        
        addProductionRule("<relational-operator>", "> < == >= <= !=");

        List<String> switchRules = Arrays.asList(
                "switch ( <id> ) { }",
                "switch ( <id> ) { <case-list> }"
        );
        addProductionRules("<switch>", switchRules);
        
        List<String> caseListRules = Arrays.asList(
                "case <number> { }",
                "case <number> { } <case-list>",
                "case <number> { <statement-list> }",
                "case <number> { <statement-list> } <case-list>"
        );
        addProductionRules("<case-list>", caseListRules);
        
        List<String> whileRules = Arrays.asList(
                "while ( <condition> ) { }",
                "while ( <condition> ) { <statement-list> }"
        );
        addProductionRules("<while>", whileRules);
        
        List<String> forRules = Arrays.asList(
                "for ( ; ; ) { }",
                "for ( <static-declaration> ; ; ) { }",
                "for ( <dynamic-declaration> ; ; ) { }",
                "for ( <assignment> ; ; ) { }",
                
                "for ( ; <condition> ; ) { }",
                "for ( <static-declaration> ; <condition> ; ) { }",
                "for ( <dynamic-declaration> ; <condition> ; ) { }",
                "for ( <assignment> ; <condition> ; ) { }",
                
                "for ( ; <condition> ; <assignment> ) { }",
                "for ( <static-declaration> ; <condition> ; <assignment> ) { }",
                "for ( <dynamic-declaration> ; <condition> ; <assignment> ) { }",
                "for ( <assignment> ; <condition> ; <assignment> ) { }",
                
                "for ( ; ; ) { <statement-list> }",
                "for ( <static-declaration> ; ; ) { <statement-list> }",
                "for ( <dynamic-declaration> ; ; ) { <statement-list> }",
                "for ( <assignment> ; ; ) { <statement-list> }",
                
                "for ( ; <condition> ; ) { <statement-list> }",
                "for ( <static-declaration> ; <condition> ; ) { <statement-list> }",
                "for ( <dynamic-declaration> ; <condition> ; ) { <statement-list> }",
                "for ( <assignment> ; <condition> ; ) { <statement-list> }",
                
                "for ( ; <condition> ; <assignment> ) { <statement-list> }",
                "for ( <static-declaration> ; <condition> ; <assignment> ) { <statement-list> }",
                "for ( <dynamic-declaration> ; <condition> ; <assignment> ) { <statement-list> }",
                "for ( <assignment> ; <condition> ; <assignment> ) { <statement-list> }"
        );
        addProductionRules("<for>", forRules);
        
        addProductionRule("namespace", "namespace");
        addProductionRule("void", "void"); addProductionRule("main", "main");
        addProductionRule("var", "var"); addProductionRule("const", "const");
        addProductionRule("if", "if"); addProductionRule("else", "else");
        addProductionRule("switch", "switch"); addProductionRule("case", "case");
        addProductionRule("while", "while");
        addProductionRule("for", "for");
        addProductionRule("f", "f"); addProductionRule("d", "d");
        addProductionRule(".", ".");
        addProductionRule("=", "=");
        addProductionRule(";", ";");
        addProductionRule("::", "::");
        addProductionRule("(", "("); addProductionRule(")", ")");
        addProductionRule("{", "{"); addProductionRule("}", "}");
    }

    @Override
    protected void initPos() {
        addPos("<id>");
        addPos("<relational-operator>");
        addPos("<digit>");
        addPos("<type>");

        addPos("namespace");
        addPos("void"); addPos("main");
        addPos("var"); addPos("const");
        addPos("if"); addPos("else");
        addPos("switch"); addPos("case");
        addPos("while");
        addPos("for");
        addPos("f"); addPos("d");
        addPos(".");
        addPos("=");
        addPos(";");
        addPos("::");
        addPos("("); addPos(")");
        addPos("{"); addPos("}");
    }
    
}
