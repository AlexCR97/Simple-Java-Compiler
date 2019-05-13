package ale.structures;

import java.util.ArrayList;
import java.util.List;

public class DerivationTree<T> {
    
    public class Vertex {
        
        public T value;
        private List<Vertex> children = new ArrayList<>();
        
        public Vertex() {}
        
        public Vertex(T value) {
            this.value = value;
        }
                
        public Vertex(T value, List<Vertex> children) {
            this.value = value;
            this.children = children;
        }
        
        public List<Vertex> getChildren() {
            return children;
        }
        
        public void addChild(T value) {
            children.add(new Vertex(value));
        }
        
        public void addChildren(List<T> values) {
            values.forEach((v) -> {
                addChild(v);
            });
        }
        
    }
    
    private Vertex root;
    
    public DerivationTree() {}
    public DerivationTree(T value) {
        root = new Vertex(value);
    }
    
    public Vertex getRoot() {
        return root;
    }
    
    public void setRoot(T value) {
        root = new Vertex(value);
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
        
        System.out.print(root.value + " ");
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
        System.out.print(root.value + " ");
        
        root.getChildren().forEach((vertex) -> {
            traversePre(vertex);
        });
    }
    
}
