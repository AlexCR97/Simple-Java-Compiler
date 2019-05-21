package ale.structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DerivationTree {
    
    public class Vertex {
        
        public String value;
        private List<Vertex> children = new ArrayList<>();
        
        public Vertex() {}
        
        public Vertex(String value) {
            this.value = value;
        }
                
        public Vertex(String value, List<Vertex> children) {
            this.value = value;
            this.children = children;
        }
        
        public List<Vertex> getChildren() {
            return children;
        }
        
        public List<Vertex> getLeaves() {
            List<Vertex> leaves = new ArrayList<>();
            
            children.forEach((vertex) -> {
                if (vertex.isLeaf())
                    leaves.add(vertex);
            });
            
            return leaves;
        }
        
        public List<Vertex> getVertices() {
            List<Vertex> vertices = new ArrayList<>();
            
            children.forEach((vertex) -> {
                if (vertex.isVertex())
                    vertices.add(vertex);
            });
            
            return vertices;
        }
        
        public void addChild(String value) {
            children.add(new Vertex(value));
        }
        
        public void addChildren(List<String> values) {
            values.forEach((v) -> {
                addChild(v);
            });
        }
        
        public void clearChildren() {
            this.children.clear();
        }
        
        public boolean containsChild(String value) {
            for (int i = 0; i < this.children.size(); i++)
                if (this.children.get(i).value.equals(value))
                    return true;
            
            return false;
        }
        
        public boolean containsLeaf(String value) {
            List<Vertex> leaves = getLeaves();
            for (int i = 0; i < leaves.size(); i++)
                if (leaves.get(i).value.equals(value))
                    return true;
            
            return false;
        }
        
        public boolean containsVertex(String value) {
            List<Vertex> vertices = getVertices();
            for (int i = 0; i < vertices.size(); i++)
                if (vertices.get(i).value.equals(value))
                    return true;
            
            return false;
        }
        
        public boolean hasChildren() {
            return !getChildren().isEmpty();
        }
        
        public boolean hasLeaves() {
            return !getLeaves().isEmpty();
        }
        
        public boolean hasVertices() {
            return !getVertices().isEmpty();
        }
        
        public boolean isLeaf() {
            return children.isEmpty();
        }
        
        public boolean isVertex() {
            return !children.isEmpty();
        }
        
        public Vertex removeChild(String value) {
            for (int i = 0; i < this.children.size(); i++)
                if (this.children.get(i).value.equals(value))
                    return this.children.remove(i);
            
            return null;
        }
        
        @Override
        public String toString() {
            if (children.isEmpty())
                return "Leaf: " + value;
            else
                return "Vertex: " + value;
        }
        
    }
    
    protected Vertex root;
    
    public DerivationTree() {}
    public DerivationTree(String value) {
        root = new Vertex(value);
    }
    
    public Vertex getRoot() {
        return root;
    }
    
    public void setRoot(String value) {
        root = new Vertex(value);
    }
    
    public void fromGrammar(ContextFreeGrammar grammar) {
        this.setRoot(grammar.getStartTag());
        fromGrammar(grammar, root);
    }
    
    private void fromGrammar(ContextFreeGrammar grammar, Vertex root) {
        if (!grammar.containsTag(root.value))
            return;
            
        List<List<String>> sentences = grammar.getSentencesInTag(root.value);
        
        sentences.forEach((words) -> {
            words.forEach((word) -> {
                root.addChild(word);
            });
        });
        
        root.getChildren().forEach((child) -> {
            fromGrammar(grammar, child);
        });
    }
    
    public boolean validate(List<String> sentence) {
        System.out.println("---------------------------------------------");
        System.out.println("Validating sentence " + sentence);
        
        if (root == null)
            return false;
        
        Queue<Vertex> queue = new LinkedList<>();
        validateGetRoots(root, queue);
        
        System.out.println("Found roots are " + queue);
        
        boolean success = validateWithRoots(sentence, queue);
        
        System.out.println("Sentence validation is " + success);
        
        return success;
    }
    
    private void validateGetRoots(Vertex root, Queue<Vertex> queue) {
        if (root.isVertex())
            queue.add(root);
        
        root.getChildren().forEach((vertex) -> {
            validateGetRoots(vertex, queue);
        });
    }
    
    protected boolean validateWithRoots(List<String> sentence, Queue<Vertex> queue) {
        for (int i = 0; i < sentence.size();) {
            System.out.println("---------------------------------------------");
            System.out.println("Index: " + i);
            
            Vertex currentRoot = queue.poll();
            String currentWord = sentence.get(i);
            
            System.out.println("Current root: " + currentRoot);
            System.out.println("Current word: " + currentWord);
            
            if (currentRoot.hasLeaves()) {
                System.out.println("Root's leaves are: " + currentRoot.getLeaves());
                
                if (!currentRoot.containsLeaf(currentWord)) {
                    System.out.println("Current word not found!");
                    System.out.println("---------------------------------------------");
                    System.out.println("FALSE");
                    return false;
                }
                else {
                    System.out.println("Current word found!");
                    System.out.println("About to check with next index...");
                    i++;
                }
            }
            else {
                System.out.println("Root does not have leaves");
                System.out.println("About to check with same index...");
            }
        }
        
        System.out.println("---------------------------------------------");
        System.out.println("TRUE");
        return true;
    }
    
    public void traversePost() {
        if (root == null) {
            System.out.println("Null root");
            return;
        }
        
        System.out.println("Post-order traversal:");
        traversePost(root);
        System.out.println();
    }
    
    private void traversePost(Vertex root) {
        root.getChildren().forEach((vertex) -> {
            traversePost(vertex);
        });
        
        System.out.print(root.value + ", ");
    }
    
    // good one
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
