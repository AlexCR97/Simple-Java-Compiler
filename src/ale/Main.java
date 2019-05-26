package ale;

import ale.compiler.parser.Parser;
import ale.compiler.parser.syntax.ConstantDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.ForSyntaxAutomatan;
import ale.compiler.parser.syntax.FunctionCallSyntaxAutomatan;
import ale.compiler.parser.syntax.IfSyntaxAutomatan;
import ale.compiler.parser.syntax.RelationalOperationSyntaxAutomatan;
import ale.compiler.parser.syntax.SwitchSyntaxAutomatan;
import ale.compiler.parser.syntax.SyntaxAutomatan;
import ale.compiler.parser.syntax.VariableDynamicDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.VariableStaticDeclarationSyntaxAutomatan;
import ale.compiler.parser.syntax.WhileSyntaxAutomatan;
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
        
        //a.validateAll(ale.Utils.toStringList("a > b", " "));
        //a.validateAll(ale.Utils.toStringList("a > b and b < c", " "));
        //a.validateAll(ale.Utils.toStringList("a > b and not exhausted", " "));
        //a.validateAll(ale.Utils.toStringList("a > b and exhausted", " "));
        //a.validateAll(ale.Utils.toStringList("b == 10 and not c", " "));
        //a.validateAll(ale.Utils.toStringList("done or a <> b", " "));
        //a.validateAll(ale.Utils.toStringList("not done and a >= 10", " "));
        //a.validateAll(ale.Utils.toStringList("not id > b", " "));
        //a.validateAll(ale.Utils.toStringList("not id and id and id", " "));
        a.validateAll(ale.Utils.toStringList("not id and num < 20 or b == 10", " "));
        //a.validateAll(ale.Utils.toStringList("not id and id and id or id == 10", " "));
        
        //a.validateAll(ale.Utils.toStringList("not not not", " "));
        //a.validateAll(ale.Utils.toStringList("not id and <> a ==", " "));
        //a.validateAll(ale.Utils.toStringList("id and num < 20 or b ==", " "));
        //a.validateAll(ale.Utils.toStringList("id and num < or b == a", " "));
    }
    
    public static void mainSyntaxAutomatanIf() {
        IfSyntaxAutomatan a = new IfSyntaxAutomatan();
        System.out.println(a);
        
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else { <statement-list> }", " "));
        a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> } else if ( <relational-operation> ) { <statement-list> }", " "));
        
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else", " "));
        //a.validateAll(ale.Utils.toStringList("if ( <relational-operation> ) { <statement-list> } else { <statement-list> } else if ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("else ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("else if ( <relational-operation> ) { <statement-list> }", " "));
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
        
        a.validateAll(ale.Utils.toStringList("while ( <relational-operation> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("while ( ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("while ( <relational-operation> ) { }", " "));
        //a.validateAll(ale.Utils.toStringList("while ( <relational-operation> ) {{ <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("while <relational-operation> { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("( <relational-operation> ) { <statement-list> }", " "));
    }
    
    public static void mainSyntaxAutomatanFor() {
        ForSyntaxAutomatan a = new ForSyntaxAutomatan();
        System.out.println(a);
        
        a.validateAll(ale.Utils.toStringList("for ( <statement> ; <relational-operation> ; <statement> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("for ( ; <relational-operation> ; <statement> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("for ( <statement> ; ; <statement> ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("for ( <statement> ; <relational-operation> ; ) { <statement-list> }", " "));
        //a.validateAll(ale.Utils.toStringList("for ( ; ; ; ) { <statement-list> }", " "));
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
        
        //a.validateAll(ale.Utils.toStringList("foo ( )", " "));
        //a.validateAll(ale.Utils.toStringList("lorem . ipsum ( )", " "));
        //a.validateAll(ale.Utils.toStringList("lorem . ipsum ( a )", " "));
        //a.validateAll(ale.Utils.toStringList("lorem . ipsum ( a , 10 , \"start\" )", " "));
        //a.validateAll(ale.Utils.toStringList("console :: println ( )", " "));
        //a.validateAll(Arrays.asList("console", "::", "println", "(", "\"Hello, Neo!\"", ")"));
        a.validateAll(ale.Utils.toStringList("console :: printf ( \"Your-full-name-is-%s-%s\\n\" , firstName , lastName )", " "));
        
        //a.validateAll(ale.Utils.toStringList("( )", " "));
        //a.validateAll(ale.Utils.toStringList("lorem ipsum ( )", " "));
        //a.validateAll(ale.Utils.toStringList("lorem . ipsum ( a", " "));
        //a.validateAll(ale.Utils.toStringList("lorem . ipsum ( a , 10 , )", " "));
        //a.validateAll(ale.Utils.toStringList("console :: :: println ( )", " "));
    }
    
    public static void mainStepValidation() {
        FunctionCallSyntaxAutomatan a = new FunctionCallSyntaxAutomatan();
        //List<String> tokens = ale.Utils.toStringList("lorem . ipsum ( a )", " ");
        List<String> tokens = ale.Utils.toStringList("foo ( , , )", " ");
        
        tokens.forEach((word) -> {
            System.out.println("AUTOMATON'S CONDITION is " + a.getCondition().name());
            a.validateStep(word);
        });
        
        System.out.println("Final CONDITION was: " + a.getCondition().name());
        System.out.println("On final state: " + (a.getCondition() == SyntaxAutomatan.Condition.ON_FINAL_STATE));
    }
    
    public static void mainCompleteValidation() {
        FunctionCallSyntaxAutomatan a = new FunctionCallSyntaxAutomatan();
        List<String> tokens = ale.Utils.toStringList("lorem . ipsum ( a )", " ");
        //List<String> tokens = ale.Utils.toStringList("foo ( a", " ");
        //List<String> tokens = ale.Utils.toStringList("foo ( , , )", " ");
        
        a.validateAll(tokens);
        
        if (a.isOnFinalState())
            System.out.println("ON FINAL STATE");
        else
            System.out.println("NOT ON FINAL STATE");
        
        System.out.println("Condition: " + a.getCondition().name());
    }
    
    public static void mainParser() {
        List<String> tokens = ale.Utils.toStringList("var x = 10 ;", " ");
        Parser parser = new Parser(tokens);
        //parser.parse();
        parser.parse("<dynamic-declaration>");
    }
    
}
