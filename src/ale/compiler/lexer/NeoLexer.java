package ale.compiler.lexer;

import ale.compiler.Token;
import ale.compiler.TokenColor;
import ale.compiler.TokenItem;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class NeoLexer {
    
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
    private List<TokenItem> tokenItems = new ArrayList<>();
    
    public NeoLexer(String filePath) {
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
                
                String currentLexeme = currentLexeme();
                String currentToken = currentToken();
                Color color = TokenColor.getColor(currentToken);
                tokenItems.add(new TokenItem(0, currentLexeme, currentToken, color));
                tokensTable.put(currentLexeme, currentToken);
                
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
    
    public List<TokenItem> getTokenItems() {
        return tokenItems;
    }
    
    public List<String> getTokenIdentifiers() {
        List<String> tokens = new ArrayList<>();
        
        tokenItems.forEach((tokenItem) -> {
            String currentToken = tokenItem.token;
            String currentLexeme = tokenItem.lexeme;
            
            if (currentToken.equals(Token.IDENTIFIER.name()) && !tokens.contains(currentLexeme))
                tokens.add(currentLexeme);
        });
        
        return tokens;
    }
    
    public List<String> getTokenNumbers() {
        List<String> tokens = new ArrayList<>();

        tokenItems.forEach((tokenItem) -> {
            String currentToken = tokenItem.token;
            String currentLexeme = tokenItem.lexeme;
            
            if (currentToken.equals(Token.NUMBER.name()) && !tokens.contains(currentLexeme))
                tokens.add(currentLexeme);
        });
        
        return tokens;
    }
    
    public List<String> getTokenStrings() {
        List<String> tokens = new ArrayList<>();
        
        tokenItems.stream()
                .filter((tokenItem) -> tokenItem.token.equals(Token.STRING.name()) && !tokens.contains(tokenItem.lexeme))
                .forEach((tokenItem) -> tokens.add(tokenItem.lexeme));
        
        return tokens;
    }
    
}
