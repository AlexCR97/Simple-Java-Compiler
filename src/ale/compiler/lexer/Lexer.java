package ale.compiler.lexer;

import ale.compiler.Token;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class Lexer {
    
    public static final int FROM_FILE = 1;
    public static final int FROM_INPUT = 2;
    
    private static final Set<Character> BLANK_CHARACTERS = new HashSet<>();
    static {
        BLANK_CHARACTERS.add('\r');
        BLANK_CHARACTERS.add('\n');
        BLANK_CHARACTERS.add((char) 8);
        BLANK_CHARACTERS.add((char) 9);
        BLANK_CHARACTERS.add((char) 11);
        BLANK_CHARACTERS.add((char) 12);
        BLANK_CHARACTERS.add((char) 32);
    }
    
    private StringBuilder input = new StringBuilder();
    private String lexeme;
    private Token token;
    private boolean exhausted = false;
    private String errorMessage = "";
    
    private Map<String, String> tokensTable = new LinkedHashMap<>();
    
    public Lexer(String filePath) {
        try {
            Path path = Paths.get(filePath);
            Stream<String> fileContents = Files.lines(path);
            fileContents.forEach(this.input::append);
        }
        catch (IOException ex) {
            exhausted = true;
            errorMessage = "Could not read the file: " + input;
            ex.printStackTrace();
        }
    }
    
    public boolean hasNext() {
        if (exhausted)
            return false;
        
        if (input.length() == 0) {
            exhausted = true;
            return false;
        }
        
        ignoreBlankCharacters();
        
        if (findNextToken())
            return true;
        
        exhausted = true;
        
        if (input.length() > 0)
            errorMessage = "Unexpected symbol: '" + input.charAt(0) + "'";
        
        return false;
    }
    
    private void ignoreBlankCharacters() {
        int charsToDelete = 0;
        
        while (BLANK_CHARACTERS.contains(input.charAt(charsToDelete)))
            charsToDelete++;
        
        if (charsToDelete > 0)
            input.delete(0, charsToDelete);
    }
    
    private boolean findNextToken() {
        for (Token t : Token.values()) {
            int end = t.endOfMatch(input.toString());
            
            if (end != -1) {
                token = t;
                lexeme = input.substring(0, end);
                
                tokensTable.put(currentLexeme(), currentToken());
                
                input.delete(0, end);
                return true;
            }
        }
        
        return false;
    }
    
    public String currentToken() {
        return token.name();
    }
    
    public String currentLexeme() {
        return lexeme;
    }
    
    public boolean isSuccessful() {
        return errorMessage.isEmpty();
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public String getInput() {
        return input.toString();
    }
    
    public Map<String, String> getTokensTable() {
        return tokensTable;
    }
    
}
