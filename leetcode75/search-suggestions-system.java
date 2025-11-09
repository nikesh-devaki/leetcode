//1268. Search Suggestions System
//https://leetcode.com/problems/search-suggestions-system/description/?envType=study-plan-v2&envId=leetcode-75
class Solution {
    class TrieNode{
        boolean isEndOfWord;
        TrieNode[] children;
        public TrieNode(){
            children = new TrieNode[26];
            isEndOfWord = false;
        }
    }

    class Trie {

        TrieNode root;
        List<String> hintWords;

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
        
        private Pair<TrieNode,List<String>> search(char c, String prefix, TrieNode node){
            hintWords = new ArrayList<String>();
            if(node==null || node.children[c-'a'] == null){
                return new Pair<TrieNode,List<String>>(null,hintWords);
            }
            if(node.children[c-'a'].isEndOfWord){
                hintWords.add(prefix+c);
                System.out.println("Found matching word "+(prefix+c));
            }
            searchWords(node.children[c-'a'], prefix+c);
            return new Pair<TrieNode,List<String>>(node.children[c-'a'], hintWords);
        }

        public List<String> searchWords(TrieNode node, String prefix){
            if(hintWords.size()>=3){
                return hintWords;
            }
            for(int i=0;i<26;i++){
                TrieNode tmpNode = node.children[i];
                char currChar = (char)('a'+i);
                if(hintWords.size()>=3){
                    return hintWords;
                }
                if(tmpNode != null){
                    if(tmpNode.isEndOfWord && hintWords.size()<=2){
                        hintWords.add(prefix+currChar);
                        System.out.println("Found matching word "+(prefix+currChar));
                    }

                    if(hintWords.size() >= 3){
                        return hintWords;
                    }
                    
                    searchWords(tmpNode, prefix+currChar);
                    if(hintWords.size()>=3){
                        return hintWords;
                    }
                }
            }
            return hintWords;
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Trie trie = new Trie();

        for(int i=0;i<products.length;i++){
            trie.insert(products[i]);
        }

        TrieNode node = trie.root;
        List<List<String>> result = new ArrayList<List<String>>();
        String prefix = "";
        String prefixWord = "";
        for(int i=0; i<searchWord.length(); i++){
            Pair<TrieNode,List<String>> resultPair = trie.search(searchWord.charAt(i), prefixWord, node);
            prefixWord += searchWord.charAt(i);
            node = resultPair.getKey();
            result.add(resultPair.getValue());
        }

        return result;
    }
}
