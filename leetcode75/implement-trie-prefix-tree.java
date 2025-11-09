//208. Implement Trie (Prefix Tree)
//https://leetcode.com/problems/implement-trie-prefix-tree/description/?envType=study-plan-v2&envId=leetcode-75

class Trie {
    class TrieNode{
        boolean isEndOfWord;
        TrieNode[] children;
        TrieNode(){
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }
    TrieNode root;
    public Trie() {
        root = new TrieNode();
       
    }
    
    private void insertChar(String word, int pos, TrieNode node){
        if(pos>=word.length()){
            return;
        }
        char c = word.charAt(pos);
        if(node.children[c-'a']==null){
            node.children[c-'a'] = new TrieNode();
        }
        if(pos==word.length()-1){
            node.children[c-'a'].isEndOfWord = true;
        }
        insertChar(word, pos+1, node.children[c-'a']);
    }

    public void insert(String word) {
        insertChar(word, 0, root);
    }
    
    private boolean search(String word, int pos, TrieNode node, boolean searchFull){
        if(pos==word.length()){
            return true;
        }
        char c = word.charAt(pos);
        if(node.children[c-'a'] == null){
           return false;
        }
        if(searchFull== true && pos==word.length()-1  ){
            return (node.children[c-'a'].isEndOfWord==true);
        }
        return search(word, pos+1, node.children[c-'a'], searchFull);
    }

    public boolean search(String word) {
        return search(word, 0, root, true);
    }
    
    public boolean startsWith(String prefix) {
        return search(prefix, 0, root, false);
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
