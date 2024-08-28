package coding.Atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * link - https://www.ambitionbox.com/interviews/atlassian-question/spell-checker-you-are-given-a-list-of-strings-dictionary-that-represents-the-correct-spelling-of-words-and-a-query-string-query-that-may-have-incorrect-spelling-you-have-to-jqZJCOc3
 * s
 * Asked in Atlassian,Software Developer
 * Spell Checker
 * You are given a list of strings, ‘DICTIONARY[]’ that represents the correct spelling of words and a query string ‘QUERY’ that may have incorrect spelling. You have to check whether the spelling of the string ‘QUERY’ is correct or not. If not, then return the list of suggestions from the list ‘DICTIONARY’. Otherwise, return an empty list which will be internally interpreted as the correct string.
 * Note:
 * 1) The suggested correct strings for the string ‘QUERY’ will be all those strings present in the ‘DICTIONARY[]’ that have the prefix same as the longest prefix of string ‘QUERY’. 2) The ‘DICTIONARY[]’ contains only distinct strings.
 * For example:
 * Given 'DICTIONARY[]' = {“ninja”, “ninjas”, “nineteen”, “ninny”} and query = “ninjk”. Since “ninjk” is not present in the ‘DICTIONARY[]’, it is an incorrect string. The suggested current spellings for “ninjk” are “ninja” and “ninjas”. It is because “ninj” is the longest prefix of “ninjk” which is present as the prefix in ‘DICTIONARY’.
 * Input Format
 * The first line of input contains an integer 'T' representing the number of test cases. The first line of each test case contains an integer ‘N’ representing the number of strings in the list, ‘DICTIONARY[].’ The second line of each test case contains ‘N’ space separated strings present in the list, ‘DICTIONARY[]’. The last line of each test case contains the ‘QUERY’ string.
 * Output Format:
 * For each test case, return a list of strings containing all suggested correct spellings from the list, ‘DICTIONARY[]’, if the spelling of the ‘query’ string is incorrect. Otherwise, return an empty list that will be internally interpreted as the correct string and will print “CORRECT”. The output of each test case will be printed in a separate line.
 * Note:
 * You do not need to print anything, it has already been taken care of. Just implement the given function.
 * Constraints:
 * 1 <= T <= 5 1 <= N <= 100 1 <= |DICTIONARY[i]| <= 100 1 <= |QUERY| <= 100 ‘DICTIONARY[i]’ and ‘QUERY’ contains only lowercase english letters. Where ‘|DICTIONARY[i]|’ is the length of the string in the list ‘DICTIONARY' and ‘|QUERY|’ is the length of the ‘QUERY’ string. Time limit: 1 sec
 *
 */
public class SpellChecker {
    static List<String> suggestions = new ArrayList<>();


    static class TrieNode{
        HashMap<Character, TrieNode> children;
        boolean isWordEnd;
        String value;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWordEnd = false;
            this.value = null;
        }
    }
    static class Trie{
        TrieNode root = new TrieNode();

        public void addToTrie(String word){
            TrieNode node = root;
            for(Character c: word.toCharArray()){
                if(!node.children.containsKey(c)){
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.value = word;
            node.isWordEnd = true;
        }

        //query - ninjk
        // dict - ninja, ninjx, ninjas
        public void searchInTrie(String query, int index, TrieNode node){

            if(node != null && index == query.length() && node.isWordEnd && node.value.equals(query)){
                System.out.println(query);
                return;
            }

            Character c = query.charAt(index);
            if(node.children.containsKey(c)){
                node = node.children.get(c);
                searchInTrie(query, index+1, node);
            }
            else{
                findSuggestions(node); //parent node
            }
        }

        public void findSuggestions(TrieNode node){

            if(node == null){
                return;
            }
            if(node.isWordEnd){
                suggestions.add(node.value);
            }
            for(TrieNode childNode: node.children.values()){
                findSuggestions(childNode);
            }
        }
    }



    public static void solve(List<String> dictornary, String query){

        Trie trie = new Trie();
        for(int i=0; i<dictornary.size(); i++){
            trie.addToTrie(dictornary.get(i));
        }
        trie.searchInTrie(query,0,trie.root);
        System.out.println(suggestions);
    }

    public static void main(String[] args) {

        List<String> dictonary = new ArrayList<>();
        dictonary.add("ninja");
        dictonary.add("ninjas");
        dictonary.add("nineteen");
        dictonary.add("ninny");

        String query = "ninjx";

        solve(dictonary,query);
    }
}
