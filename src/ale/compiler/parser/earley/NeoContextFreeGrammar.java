package ale.compiler.parser.earley;

import java.util.Arrays;

public class NeoContextFreeGrammar extends ContextFreeGrammar {    
    
    @Override
    protected void initStartRules() {
        addStartRules(Arrays.asList(
                "<use-list>",
                "<namespace>",
                "<use-list> <namespace>"
        ));
    }
    
    @Override
    protected void initProductionRules() {
        
        addProductionRules("<use-list>", Arrays.asList(
                "<use-statement> ; <use-list>",
                "<use-statement> ;"
        ));
        
        addProductionRules("<use-statement>", Arrays.asList(
                "use <use-id>"
        ));
        
        addProductionRules("<use-id>", Arrays.asList(
                "<id> :: <use-id>",
                "<id>"
        ));
        
        addProductionRules("<namespace>", Arrays.asList(
                "namespace <namespace-id> <namespace-block>"
        ));
        
        addProductionRules("<namespace-id>", Arrays.asList(
                "<id> :: <namespace-id>",
                "<id>"
        ));
        
        addProductionRules("<namespace-block>", Arrays.asList(
                "{ }",
                "{ <namespace-block-content> }"
        ));
        
        addProductionRules("<namespace-block-content>", Arrays.asList(
                "<var-declaration> ; <namespace-block-content>",
                "<var-declaration> ;",

                "<const-declaration> ; <namespace-block-content>",
                "<const-declaration> ;",

                "<func-declaration> <namespace-block-content>",
                "<func-declaration>",

                "<main>"
        ));
        
        addProductionRules("<main>", Arrays.asList(
                "void main ( ) <func-block>"
        ));
        
        addProductionRules("<func-block>", Arrays.asList(
                "{ }",
                "{ <statement-list> }"
        ));
        
        addProductionRules("<statement-list>", Arrays.asList(
                "<statement> ; <statement-list>",
                "<statement> ;",
                
                "<flow-controller> <statement-list>",
                "<flow-controller>"
        ));
        
        addProductionRules("<statement>", Arrays.asList(
                "<var-declaration>",
                "<const-declaration>",
                "<assignment>",
                "<math-assignment>",
                "<func-call>",
                
                "return",
                "return <assignable>"
        ));
        
        addProductionRules("<flow-controller>", Arrays.asList(
                "<if>",
                "<when>",
                "<while>",
                "<for>"
        ));
        
        addProductionRules("<var-declaration>", Arrays.asList(
                "<type> <var-declaration-list>",
                "var <var-declaration-list-dynamic>"
        ));
        
        addProductionRule("<type>", "int float double bool char string");
        
        addProductionRules("<var-declaration-list>", Arrays.asList(
                "<id> , <var-declaration-list>",
                "<assignment> , <var-declaration-list>",
                "<assignment>",
                "<id>"
        ));
        
        addProductionRules("<assignment>", Arrays.asList(
                "<id> = <assignable>"
        ));
        
        addProductionRules("<var-declaration-list-dynamic>", Arrays.asList(
                "<assignment> , <var-declaration-list>",
                "<assignment>"
        ));
        
        addProductionRules("<assignable>", Arrays.asList(
                "<id>",
                "<arithmetic-expression>",
                "<bool>",
                "<string>",
                "<func-call>"
        ));
        
        addProductionRules("<arithmetic-expression>", Arrays.asList(
                "<number>",
                "<id>",
                "<func-call>",
                "( <arithmetic-expression> )",
                "<arithmetic-expression> <arithmetic-operator> <arithmetic-expression>"
        ));
        
        addProductionRule("<arithmetic-operator>", "+ - * / % ^ ~");
        
        addProductionRule("<bool>", "true false");
        
        addProductionRules("<math-assignment>", Arrays.asList(
                "<id> <math-operator> <math-assignable>"
        ));
        
        addProductionRule("<math-operator>", "+= -= *= /= %= ^= ~=");
        
        addProductionRules("<math-assignable>", Arrays.asList(
                "<id>",
                "<arithmetic-expression>",
                "<func-call>"
        ));
        
        addProductionRules("<func-call>", Arrays.asList(
                "<func-call-id> ( )",
                "<func-call-id> ( <assignable-list> )"
        ));
        
        addProductionRules("<func-call-id>", Arrays.asList(
                "<id> . <func-call-id>",
                "<id> :: <func-call-id>",
                "<id>"
        ));
        
        addProductionRules("<assignable-list>", Arrays.asList(
                "<assignable> , <assignable-list>",
                "<assignable>"
        ));
        
        addProductionRules("<const-declaration>", Arrays.asList(
                "const <const-declaration-list>"
        ));

        addProductionRules("<const-declaration-list>", Arrays.asList(
                "<assignment> , <const-declaration-list>",
                "<assignment>"
        ));
        
        addProductionRules("<func-declaration>", Arrays.asList(
                "<func-type> <id> ( <param-list> ) <func-block>",
                "<func-type> <id> ( ) <func-block>"
        ));
        
        addProductionRules("<func-type>", Arrays.asList(
                "void",
                "<type>"
        ));
        
        addProductionRules("<param-list>", Arrays.asList(
                "<param> , <param-list>",
                "<param>"
        ));

        addProductionRules("<param>", Arrays.asList(
                "<type> <id>"
        ));
        
        addProductionRules("<if>", Arrays.asList(
                "if ( <relational-operation> ) <if-block>",
                "if ( <relational-operation> ) <if-block> else <if>",
                "if ( <relational-operation> ) <if-block> else <if-block>"
        ));
        
        addProductionRules("<if-block>", Arrays.asList(
                "<func-block>",
                "<statement> ;",
                "<flow-controller>"
        ));
        
        addProductionRules("<relational-operation>", Arrays.asList(
                "<relational-term> <relational-operator> <relational-term>"
        ));
        
        addProductionRules("<relational-term>", Arrays.asList(
                "<assignable>"
        ));
        
        addProductionRule("<relational-operator>", "== <> < <= > >=");
        
        addProductionRules("<when>", Arrays.asList(
                "when ( <id> ) <when-block>",
                "when ( <func-call> ) <when-block>"
        ));
        
        addProductionRules("<when-block>", Arrays.asList(
                "{ }",
                "{ <when-match-list> }"
        ));
        
        addProductionRules("<when-match-list>", Arrays.asList(
                "<when-match> <when-match-list>",
                "<when-match>",
                "<when-default>"
        ));
        
        addProductionRules("<when-match>", Arrays.asList(
                "matches <assignable-list> <func-block>"
        ));
        
        addProductionRules("<when-default>", Arrays.asList(
                "default <func-block>"
        ));
        
        addProductionRules("<while>", Arrays.asList(
                "while ( <relational-operation> ) <while-block>",
                "while ( <id> ) <while-block>",
                "while ( <func-call> ) <while-block>"
        ));
        
        addProductionRules("<while-block>", Arrays.asList(
                "<func-block>",
                "<statement> ;",
                "<flow-controller>"
        ));
        
        addProductionRules("<for>", Arrays.asList(
                "for ( <for-params> ) <for-block>"
        ));
        
        addProductionRules("<for-params>", Arrays.asList(
                "; ;",
                "<for-param-1> ; ;",
                "; <for-param-2> ;",
                "<for-param-1> ; <for-param-2> ;",
                "; ; <for-param-3>",
                "<for-param-1> ; ; <for-param-3>",
                "; <for-param-2> ; <for-param-3>",
                "<for-param-1> ; <for-param-2> ; <for-param-3>"
        ));
        
        addProductionRules("<for-param-1>", Arrays.asList(
                "<var-declaration>"
        ));
        
        addProductionRules("<for-param-2>", Arrays.asList(
                "<relational-operation>"
        ));
        
        addProductionRules("<for-param-3>", Arrays.asList(
                "<assignment>",
                "<math-assignment>"
        ));
        
        addProductionRules("<for-block>", Arrays.asList(
                "<func-block>",
                "<statement> ;",
                "<flow-controller>"
        ));
        
        addProductionRule("use", "use");
        addProductionRule("namespace", "namespace");
        addProductionRule("::", "::");
        addProductionRule("{", "{");
        addProductionRule("}", "}");
        addProductionRule("void", "void");
        addProductionRule("main", "main");
        addProductionRule("(", "(");
        addProductionRule(")", ")");
        addProductionRule(";", ";");
        addProductionRule(",", ",");
        addProductionRule("=", "=");
        addProductionRule(".", ".");
        addProductionRule("var", "var");
        addProductionRule("const", "const");
        addProductionRule("return", "return");
        addProductionRule("if", "if");
        addProductionRule("else", "else");
        addProductionRule("when", "when");
        addProductionRule("matches", "matches");
        addProductionRule("default", "default");
        addProductionRule("while", "while");
        addProductionRule("for", "for");
    }

    @Override
    protected void initPos() {
        addPos("<type>");
        addPos("<bool>");
        addPos("<arithmetic-operator>");
        addPos("<relational-operator>");
        addPos("<math-operator>");
        
        addPos("use");
        addPos("namespace");
        addPos("::");
        addPos("{");
        addPos("}");
        addPos("void");
        addPos("main");
        addPos("(");
        addPos(")");
        addPos(";");
        addPos(",");
        addPos("=");
        addPos(".");
        addPos("var");
        addPos("const");
        addPos("return");
        addPos("if");
        addPos("else");
        addPos("when");
        addPos("matches");
        addPos("default");
        addPos("while");
        addPos("for");
    }
    
}
