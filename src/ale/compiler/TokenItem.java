package ale.compiler;

import java.awt.Color;

public class TokenItem {

    public final int line;
    public final String lexeme;
    public final String token;
    public final Color color;
    
    public TokenItem(int line, String lexeme, String token, Color color) {
        this.line = line;
        this.lexeme = lexeme;
        this.token = token;
        this.color = color;
    }

    @Override
    public String toString() {
        return "TokenItem{" + "line=" + line + ", lexeme=" + lexeme + ", token=" + token + ", color=" + color + '}';
    }
    
}
