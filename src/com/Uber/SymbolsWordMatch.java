package com.Uber;

import java.util.*;

/**
 * You're given a set of symbols for the elements in the periodic table [.... Li, Be, B, C, N, F, Ne, Na, Co, Ni, Cu, Ga, Al, Si.....]
 *
 *
 * Write the function breakingBad(name, symbols) that given a name and a set of symbols returns the phrase with the following format [Symbol]rest of the word
 *
 *
 * Example:
 * symbols = [H, He, Li, Be, B, C, N, F, Ne, Na, Co, Ni, Cu, Ga, Al, Si, Fa]
 * breakingBad("henry alba", symbols) results in [He]nry [Al]ba
 *
 *
 * Follow up: we only care about the longest symbol within a word. Example in the word henry there are two elements that are present [H] & [He] and we want He in the output phrase and not H.
 *
 *
 * I am wondering how you would approach this problem?
 *
 *
 * My attempt to solving this during the interview was:
 *
 *
 * Put the symbols in a set for constant lookup time.
 * Find the maxLen of any symbol.
 * Break up the given string into multiple words (the only delimiter will be a blank space)
 * For each word look for the maxLen substring and then recurse on that substring with maxLen - 1 till maxLen == 0; and do that for each substring that can be formed.
 * This seems like a brute force approach to me, I am wondering how best to optimize it which is why I am writing this post.
 *
 *
 * P.S. I found it hard. I am curious to know what your thoughts are?
 */
class TrieNode{
    HashMap<Character, TrieNode> children;
    boolean isWord;

    public TrieNode() {
        this.children = new HashMap<>();
        this.isWord = false;
    }
}
class Trie{

   TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insertToTrie(String symbol){
        TrieNode node = root;

        for(int i=0; i<symbol.length(); i++){
            Character c = symbol.charAt(i);
            if(!node.children.containsKey(c)){
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isWord = true;
    }

    public int findInTrie(String word){
        TrieNode node = root;
        int idx=-1;

        for(int i=0; i<word.length();i++){
            Character c = word.charAt(i);
            if(node.children.containsKey(c)){
                node = node.children.get(c);
                idx=i;
            }
        }
        if(node.isWord){
            return idx;
        }
        return -1;
    }
}
public class SymbolsWordMatch {

    Trie trie = new Trie();

    public void solve(ArrayList<String> symbolsList, String name){
        for(String symbol: symbolsList){
            trie.insertToTrie(symbol);
        }
        List<String> ansList = new ArrayList<>();
        String[] nameParts = name.split("\\s+"); //whitespaces skip


        for(String namePart: nameParts){
            int idx = trie.findInTrie(namePart);
            if(idx == -1){
                continue;
            }
            String ans = "[" + namePart.substring(0,idx+1) + "]"+ namePart.substring(idx+1, namePart.length());
            ansList.add(ans);
        }

        for(String a: ansList){
            System.out.print(a+" ");
        }
    }

    public static void main(String[] args) {

        String[] symbolsArr = new String[]{"H", "He", "Li", "Be", "B", "C", "N", "F", "Ne", "Na", "Co", "Ni", "Cu", "Ga", "Al", "Si", "Fa"};
        ArrayList<String> symbols = new ArrayList<>();
        symbols.addAll(Arrays.asList(symbolsArr));
//        String name = "henry alba"; //original question
        String name = "Henry Alba";

        SymbolsWordMatch symbolsWordMatch = new SymbolsWordMatch();
        symbolsWordMatch.solve(symbols,name);
    }
}
/**
 * Time Complexity:
 * Trie Construction: Inserting k symbols, each of length at most m, takes O(k * m).
 * Word Processing: For each word of length n, searching for the longest symbol takes O(n), since the search stops as soon as the symbol is fully matched. Thus, for w words of average length n, the total complexity is O(w * n).
 */
