package alg;

import base.TrieNode;

public class WordDictionary {
    private TrieNode root;

    public WordDictionary(TrieNode root) {
        this.root = root;
    }
    // Inserts a word into the trie.
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }


    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return match(word, root, 0);
    }

    private boolean match(String word, TrieNode node, int start) {
        if (start == word.length()) {
            return node.isEnd();
        }
        char alpha = word.charAt(start);
        if (alpha == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.getLinks()[i] != null && match(word, node.getLinks()[i], start + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (node.get(alpha) == null) {
                return false;

            }
            return match(word, node.get(alpha), start + 1);
        }
    }


    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary(new TrieNode());
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        boolean search1 = wordDictionary.search("pad");// -> false
        System.out.println(search1);
        boolean search2 = wordDictionary.search("bad"); // -> true
        System.out.println(search2);
        boolean search3 = wordDictionary.search(".ad"); // -> true
        System.out.println(search3);
        boolean search4 = wordDictionary.search("b.."); //-> true
        System.out.println(search4);
    }

}
