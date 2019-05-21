package ale.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContextFreeGrammar {
    
    private final Map<String, List<List<String>>> GRAMMAR = new LinkedHashMap<>();
    private final List<String> words = new ArrayList<>();
    private final String DEFAULT_START_TAG = "<s>";
    
    private String startTag = DEFAULT_START_TAG;
    private boolean expanded = false;
    
    public ContextFreeGrammar() {
        initGrammar();
    }
    
    protected void initGrammar() {
        addRules(startTag, Arrays.asList("There was once", "<a>", "<b>"));
        
        addRule("<a>", "the");
        addRule("<a>", "a");
        
        addRules("<b>", Arrays.asList("<c>", "<d>"));
        
        addRule("<c>", "happy");
        addRule("<c>", "sad");
        addRule("<c>", "wild");
        
        addRule("<d>", "cat");
        addRule("<d>", "dog");
        addRule("<d>", "horse");
    }
    
    public String getStartTag() {
        return startTag;
    }
    
    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }
    
    public List<List<String>> getSentencesInTag(String tag) {
        if (!GRAMMAR.containsKey(tag))
            return new ArrayList<>();
        
        return GRAMMAR.get(tag);
    }
    
    public List<String> getTagsInTag(String tag) {
        if (!GRAMMAR.containsKey(tag))
            return new ArrayList<>();
        
        List<String> tagsInTag = new ArrayList<>();
        
        getSentencesInTag(tag).forEach((sentence) -> {
            sentence.forEach((word) -> {
                if (isTag(word))
                    tagsInTag.add(word);
            });
        });
        
        return tagsInTag;
    }
    
    public List<String> getWordsInTag(String tag) {
        if (!GRAMMAR.containsKey(tag))
            return new ArrayList<>();
        
        List<String> wordsInTag = new ArrayList<>();
        
        getSentencesInTag(tag).forEach((sentence) -> {
            sentence.forEach((word) -> {
                if (!isTag(word))
                    wordsInTag.add(word);
            });
        });
        
        return wordsInTag;
    }
    
    public List<String> getWords() {
        return words;
    }
    
    public void addRule(String tag, String rule) {
        if (!GRAMMAR.containsKey(tag))
            GRAMMAR.put(tag, new ArrayList<>());
        
        List<String> newRules = new ArrayList<>();
        newRules.add(rule);
        
        List<List<String>> grammarRules = GRAMMAR.get(tag);
        grammarRules.add(newRules);
    }
    
    public void addRules(String tag, List<String> rules) {
        if (!GRAMMAR.containsKey(tag))
            GRAMMAR.put(tag, new ArrayList<>());
        
        List<List<String>> grammarRules = GRAMMAR.get(tag);
        grammarRules.add(rules);
    }
    
    public void clearGrammar() {
        GRAMMAR.clear();
        words.clear();
        this.expanded = false;
        this.startTag = DEFAULT_START_TAG;
    }
    
    public boolean containsTag(String tag) {
        return this.GRAMMAR.containsKey(tag);
    }
    
    public void expand() {
        expand(startTag);
    }
    
    public void expand(String tag) {
        if (expanded)
            return;
        
        expand(tag, words);
        expanded = true;
        System.out.println("Tree has been expanded");
    }
    
    private void expand(String tag, List<String> expansion) {
        if (GRAMMAR.containsKey(tag)) {
            List<List<String>> possible = GRAMMAR.get(tag);
            int index = ale.Utils.getRandomRangeI(0, possible.size() - 1);
            List<String> randomExpansion = possible.get(index);
            
            for (int i = 0; i < randomExpansion.size(); i++)
                expand(randomExpansion.get(i), expansion);
        } else
            expansion.add(tag);
    }
    
    public boolean isExpanded() {
        return expanded;
    }
    
    private boolean isTag(String tag) {
        return tag.charAt(0) == '<' && tag.charAt(tag.length() - 1) == '>';
    }
    
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        GRAMMAR.forEach((key, value) -> {
            s.append(key).append(" => ");
            
            value.forEach((sentence) -> {
                sentence.forEach((word) -> {
                    s.append(word).append(", ");
                });
            });
            s.append("\n");
        });
        
        return s.toString();
    }
    
}
