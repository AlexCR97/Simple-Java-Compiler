package ale;

import ale.compiler.parser.Parser;
import ale.compiler.parser.syntax.ConstantDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.ForSyntaxAutomatan;
import ale.compiler.parser.syntax.FunctionCallSyntaxAutomatan;
import ale.compiler.parser.syntax.IfSyntaxAutomatan;
import ale.compiler.parser.syntax.RelationalOperationSyntaxAutomatan;
import ale.compiler.parser.syntax.SwitchSyntaxAutomatan;
import ale.compiler.parser.syntax.VariableDynamicDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.VariableStaticDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.WhileSyntaxAutomatan;
import ale.structures.Automatan;
import ale.structures.CFG;
import ale.structures.SyntaxAutomatan;
import ale.structures.SyntaxTree;
import ale.ui.frames.MainFrame;
import ale.ui.frames.SplashFrame;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    private static final long LOAD_TIME = 6000;
    
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        
        //openSplashFrame();
        //openMainFrame();
        //mainCFG();
        //mainAutomatan();
        //mainSyntaxAutomatan();
        //mainSyntaxAutomatanStaticVars();
        //mainSyntaxAutomatanDynamicVars();
        //mainSyntaxAutomatanConst();
        //mainSyntaxAutomatanRelationalOperations();
        //mainSyntaxAutomatanIf();
        //mainSyntaxAutomatanSwitch();
        //mainSyntaxAutomatanWhile();
        //mainSyntaxAutomatanFor();
        //mainSyntaxAutomatanFunctionCall();
        //mainStepValidation();
        //mainCompleteValidation();
        mainParser();
    }
    
    public static void openMainFrame() {
        MainFrame frame = new MainFrame();
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(frame.getExtendedState() | MainFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    public static void openSplashFrame() {
        SplashFrame splash = new SplashFrame();
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);
        
        try {
            Thread.sleep(LOAD_TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        splash.setVisible(false);
        openMainFrame();
    }
    
    public static void mainCFG() {
        CFG cfg = new CFG();
        //[if, var5, >, var4, {, var5, =, 9, ;, }]
        cfg.setExpansion(Arrays.asList("if", "var5", ">", "10", "{", "var5", "=", "0", ";", "}"));
        System.out.println(cfg.getExpansion());
        
        /*for (int i = 0; i < 10; i++) {
            cfg.expand();
            System.out.println(cfg.getExpansion());
        }
        System.out.println("");*/
        
        SyntaxTree tree = new SyntaxTree();
        for (int i = 0; i < 10; i++) {
            tree.fromGrammar(cfg);
            tree.traversePre();
        }
        System.out.println("");
        
        // if var5 == 5 { var5 = var1 ; }
    }
    
    public static void mainAutomatan() {
        List<String> states = Arrays.asList("q0", "q1", "a2");
        List<String> alphabet = Arrays.asList("0", "1");
        String initialState = "q0";
        List<String> finalStates = Arrays.asList("q2");
        
        Automatan a = new Automatan(states, alphabet, initialState, finalStates);
        
        a.addTransition("q0", "0", "q1");
        a.addTransition("q0", "1", "q0");
        a.addTransition("q1", "0", "q2");
        a.addTransition("q1", "1", "q2");
        a.addTransition("q2", "0", "q2");
        a.addTransition("q2", "1", "q2");
        
        System.out.println(a);
        System.out.println();
        
        System.out.println(a.validate("11010"));
        System.out.println(a.validate("10"));
        System.out.println(a.validate("01"));
        System.out.println(a.validate("11"));
    }
    
    public static void mainSyntaxAutomatan() {
        List<String> states = Arrays.asList("q0", "q1", "q2", "q3", "q4", "q5");
        List<String> alphabet = Arrays.asList("<tipo-de-dato>", "<variable>", "<asignador>", "<fin-de-linea>", "<numero>", "<cadena>", "<caracter>");
        String initialState = "q0";
        List<String> finalStates = Arrays.asList("q4");
        
        SyntaxAutomatan a = new SyntaxAutomatan(states, alphabet, initialState, finalStates);
        a.addTransition("q0", "<tipo-de-dato>", "q1");
        a.addTransition("q1", "<variable>", "q2");
        a.addTransition("q2", "<asignador>", "q3");
        a.addTransition("q2", "<fin-de-linea>", "q4");
        a.addTransition("q3", "<numero>", "q5");
        a.addTransition("q3", "<cadena>", "q5");
        a.addTransition("q3", "<caracter>", "q5");
        a.addTransition("q5", "<fin-de-linea>", "q4");
        
        System.out.println(a);
        
        a.validate("<tipo-de-dato> <variable> <fin-de-linea>");
        a.validate("<tipo-de-dato> <variable> <asignador> <numero> <fin-de-linea>");
        a.validate("<tipo-de-dato> <variable> <asignador> <cadena> <fin-de-linea>");
        a.validate("<tipo-de-dato> <variable> <asignador> <caracter> <fin-de-linea>");
        
        a.validate("<tipo-de-dato> <variable>");
        a.validate("<variable> <asignador> <numero> <fin-de-linea>");
        a.validate("<tipo-de-dato> <variable> <asignador> <cadena>");
        a.validate("<tipo-de-dato> <variable> <asignador> <fin-de-linea>");
    }
    
    public static void mainSyntaxAutomatanStaticVars() {
        VariableStaticDeclarationSyntaxAutomatan a = new VariableStaticDeclarationSyntaxAutomatan();
        System.out.println(a);
        
        a.validateAll(Arrays.asList("int", "number", "=", "10", ";"));
        a.validateAll(Arrays.asList("string", "message", "=", "\"Hello world\"", ";"));
        
        a.validateAll(Arrays.asList("number", "=", "10", ";"));
        a.validateAll(Arrays.asList("string", "message", "=", "\"Hello world\""));
    }
    
    public static void mainSyntaxAutomatanDynamicVars() {
        VariableDynamicDeclarationSyntaxAutomatan a = new VariableDynamicDeclarationSyntaxAutomatan();
        System.out.println(a);
        
        a.validateAll(Arrays.asList("var", "number", "=", "10", ";"));
        a.validateAll(Arrays.asList("var", "message", "=", "\"Hello world\"", ";"));
        
        a.validateAll(Arrays.asList("var", "=", "10", ";"));
        a.validateAll(Arrays.asList("var", "message", "=", "\"Hello world\""));
    }
    
    public static void mainSyntaxAutomatanConst() {
        ConstantDeclarationSyntaxAutomatan a = new ConstantDeclarationSyntaxAutomatan();
        System.out.println(a);
        
        a.validateAll(Arrays.asList("const", "number", "=", "10", ";"));
        a.validateAll(Arrays.asList("const", "message", "=", "\"Hello world\"", ";"));
        
        a.validateAll(Arrays.asList("const", "=", "10", ";"));
        a.validateAll(Arrays.asList("const", "message", "=", "\"Hello world\""));
    }
    
    public static void mainSyntaxAutomatanRelationalOperations() {
        RelationalOperationSyntaxAutomatan a = new RelationalOperationSyntaxAutomatan();
        System.out.println(a);
        
        /* a > b */
        /* a > b and b < c */
        /* a > 10 and not exhausted */
        /* b = 10 and not c */
        /* done or a <> b */
        /* not done and a >= 10 */
        /* not id > b */
        /* not id and id and id */
        /* not id and id < 20 or b == 10*/
        /* not id and id and id or id == 10 */
        
        //a.validate(ale.Utils.toStringList("a > b", " "));
        //a.validate(ale.Utils.toStringList("a > b and b < c", " "));
        a.validateAll(ale.Utils.toStringList("a > b and not exhausted", " "));
        //a.validate(ale.Utils.toStringList("a > b and exhausted", " "));
        //a.validate(ale.Utils.toStringList("b == 10 and not c", " "));
        //a.validate(ale.Utils.toStringList("done or a <> b", " "));
        //a.validate(ale.Utils.toStringList("not done and a >= 10", " "));
        //a.validate(ale.Utils.toStringList("not id > b", " "));
        //a.validate(ale.Utils.toStringList("not id and id and id", " "));
        //a.validate(ale.Utils.toStringList("not id and id < 20 or b == 10", " "));
        //a.validate(ale.Utils.toStringList("not id and id and id or id == 10", " "));
    }
    
    public static void mainSyntaxAutomatanIf() {
        IfSyntaxAutomatan a = new IfSyntaxAutomatan();
        System.out.println(a);
        
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> } else if ( <relational-operation> ) { <statements> }", " "));
        
        a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else", " "));
        //a.validate(ale.Utils.toStringList("if ( <relational-operation> ) { <statements> } else { <statements> } else if ( <relational-operation> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("else ( <relational-operation> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("else if ( <relational-operation> ) { <statements> }", " "));
    }
    
    public static void mainSyntaxAutomatanSwitch() {
        SwitchSyntaxAutomatan a = new SwitchSyntaxAutomatan();
        System.out.println(a);
        
        /*switch (variable) {
            case "a", "e", "i", "o", "u" { <statements> }
            case 1, 2, 3 { <statements> }
            case 10 { <statements> }
            case "neo" { <statements> }
            default { <statements> }
        }*/

        /*a.validate(ale.Utils.toStringList(
                "switch ( number ) { " +
                    "case 1 { <statements> } " +
                    "case 2 { <statements> } " +
                    "default { <statements> } " +
                "}", " "
        ));*/
        
        /*a.validate(ale.Utils.toStringList(
                "switch ( vowel ) { " +
                    "case \"a\" , \"e\" , \"i\" , \"o\" , \"u\" { <statements> } " +
                "}", " "
        ));*/
        
        a.validateAll(ale.Utils.toStringList(
                "switch ( variable ) { " +
                    "case \"a\" , \"e\" , \"i\" , \"o\" , \"u\" { <statement-list> } " +
                    "case 1 , 2 , 3 { <statement-list> } " +
                    "case 10 { <statement-list> } " +
                    "case \"neo\" { <statement-list> } " +
                    "default { <statement-list> } " +
                "}", " "
        ));
    }
    
    public static void mainSyntaxAutomatanWhile() {
        WhileSyntaxAutomatan a = new WhileSyntaxAutomatan();
        System.out.println(a);
        
        a.validateAll(ale.Utils.toStringList("while ( <relational-operation> ) { <statements> }", " "));
        a.validateAll(ale.Utils.toStringList("while ( ) { <statements> }", " "));
    }
    
    public static void mainSyntaxAutomatanFor() {
        ForSyntaxAutomatan a = new ForSyntaxAutomatan();
        System.out.println(a);
        
        //a.validate(ale.Utils.toStringList("for ( <statement> ; <relational-operation> ; <statement> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("for ( ; <relational-operation> ; <statement> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("for ( <statement> ; ; <statement> ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("for ( <statement> ; <relational-operation> ; ; ) { <statements> }", " "));
        //a.validate(ale.Utils.toStringList("for ( ; ; ; ) { <statements> }", " "));
    }
    
    public static void mainSyntaxAutomatanFunctionCall() {
        FunctionCallSyntaxAutomatan a = new FunctionCallSyntaxAutomatan();
        System.out.println(a);
        
        /*
        foo()
        lorem.ipsum()
        lorem.ipsum(a)
        lorem.ipsum(a, 10)
        lorem.ipsum(a, 10, "start")
        console::println()
        console::println("Hello, Neo!")
        console::printf("Your full name is %s %s\n", firstName, lastName)
        */
        
        //a.validate(ale.Utils.toStringList("foo ( )", " "));
        //a.validate(ale.Utils.toStringList("lorem . ipsum ( )", " "));
        //a.validate(ale.Utils.toStringList("lorem . ipsum ( a )", " "));
        //a.validate(ale.Utils.toStringList("lorem . ipsum ( a , 10 , \"start\" )", " "));
        //a.validate(ale.Utils.toStringList("console :: println ( )", " "));
        //a.validate(Arrays.asList("console", "::", "println", "(", "\"Hello, Neo!\"", ")"));
        a.validateAll(ale.Utils.toStringList("console :: printf ( \"Your-full-name-is-%s-%s\\n\" , firstName , lastName )", " "));
    }
    
    public static void mainStepValidation() {
        FunctionCallSyntaxAutomatan a = new FunctionCallSyntaxAutomatan();
        //List<String> tokens = ale.Utils.toStringList("lorem . ipsum ( a )", " ");
        List<String> tokens = ale.Utils.toStringList("foo ( , , )", " ");
        
        int i = 0;
        while (a.validateStep(tokens.get(i))) {
            i++;
        }
        
    }
    
    public static void mainCompleteValidation() {
        FunctionCallSyntaxAutomatan a = new FunctionCallSyntaxAutomatan();
        List<String> tokens = ale.Utils.toStringList("lorem . ipsum ( a )", " ");
        //List<String> tokens = ale.Utils.toStringList("( a )", " ");
        
        a.validateAll(tokens);
    }
    
    public static void mainParser() {
        List<String> tokens = ale.Utils.toStringList("var x = 10 ;", " ");
        Parser parser = new Parser(tokens);
        parser.parse();
        //parser.parse("<dynamic-declaration>");
    }
    
}
