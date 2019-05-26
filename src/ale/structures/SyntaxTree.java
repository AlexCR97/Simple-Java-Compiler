package ale.structures;

import java.util.ArrayList;
import java.util.List;

public class SyntaxTree {
    
    public class Vertex {
        private String value;
        private List<Vertex> children = new ArrayList<>();
        
        public Vertex(String value) {
            this.value = value;
        }
        
        public void addChild(String value) {
            children.add(new Vertex(value));
        }
        
        public void addChildren(List<String> values) {
            values.forEach((v) -> {
                addChild(v);
            });
        }
        
        public List<Vertex> getChildren() {
            return children;
        }
        
        @Override
        public String toString() {
            return (!children.isEmpty())? "Non-terminal: " + value : "Terminal: " + value;
        }
    }
    
    private Vertex root;
    
    public SyntaxTree() {}
    public SyntaxTree(String value) {
        root = new Vertex(value);
    }
    
    public Vertex getRoot() {
        return root;
    }

    public void setRoot(String value) {
        this.root = new Vertex(value);
    }
    
    public void fromGrammar(CFG grammar) {
        setRoot(grammar.getStartTag());
        fromGrammar(grammar, root);
    }
    
    public void fromGrammar(CFG grammar, Vertex root) {
        List<String> sentences = grammar.getRulesInTag(root.value);
        int index = ale.Utils.getRandomRangeI(0, sentences.size() - 1);
        String sentence = sentences.get(index);
        String[] words = sentence.split(" ");

        for (String word : words)
            root.addChild(word);
        
        root.getChildren().forEach((child) -> {
            if (grammar.containsTag(child.value))
                fromGrammar(grammar, child);
        });
    }
    
    public void fromExpansion(CFG grammar) {
        root = new Vertex(grammar.getStartTag());
        fromExpansion(grammar, 0, root);
    }
    
    private void fromExpansion(CFG grammar, int index, Vertex root) {
        List<String> rules = grammar.getRulesInTag(root.value);
        String currentWord = grammar.getExpansion().get(index);
        
        for (String rule : rules) {
            for (String word : rule.split(" ")) {
                if (currentWord.equals(word)) {
                    root.addChild(word);
                } else {
                    
                }
            }
        }
    }
    
    public void traversePre() {
        if (root == null) {
            System.out.println("Null root");
            return;
        }
        
        System.out.println("Pre-order traversal:");
        traversePre(root);
        System.out.println();
    }
    
    private void traversePre(Vertex root) {
        System.out.print(root.value + ", ");
        
        root.getChildren().forEach((vertex) -> {
            traversePre(vertex);
        });
    }
    
}
