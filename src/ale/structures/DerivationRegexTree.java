package ale.structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DerivationRegexTree extends DerivationTree {
    
    @Override
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
                
                if (!currentWordMatchesAnyLeaf(currentRoot, currentWord)) {
                    System.out.println("Current word not found!");
                    System.out.println("---------------------------------------------");
                    System.out.println("FALSE");
                    return false;
                }
                else {
                    System.out.println("Current word found!");
                    System.out.println("About to check with next index on same root...");
                    i++;
                }
            }
            else {
                System.out.println("Root does not have leaves");
                System.out.println("About to check with same index on next root...");
            }
        }
        
        System.out.println("---------------------------------------------");
        System.out.println("TRUE");
        return true;
    }
    
    private boolean currentWordMatchesAnyLeaf(Vertex root, String word) {
        for (Vertex leaf : root.getLeaves())
            if (ale.Utils.regexValidate(leaf.value, word))
                return true;
        return false;
    }
    
}



