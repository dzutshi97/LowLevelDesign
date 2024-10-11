package coding.Google;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * I was asked this question in my Google onsite.
 * Basically leetcode 720 - https://leetcode.com/problems/longest-word-in-dictionary/ but with the caveat that words can be added in any order.
 * Example input/output:
 *
 *
 * starting_character = "a"
 * dictionary = ["a", "an", "ant", "want", "ba", "bat"]
 *
 * Output: "want"
 * Explanation: "a" -> "an" -> "ant" -> "want"
 *
 * starting_character = "s"
 * dictionary = ["t", "ta", "w", "star", "sta", "stars", "ba", "bat"]
 *
 * Output: "stars"
 * Explanation: "t" -> "ta" -> "sta" -> "star" -> "stars"
 * I could only do the brute force approach. Any suggestions on how to do this?
 *
 * https://leetcode.com/discuss/interview-question/2002748/google-onsite-coding-interview-question
 * I think this is: https://leetcode.com/problems/longest-string-chain/ undercover
 */
class TrieNode{
    HashMap<Character, TrieNode> children;
    boolean isWordEnd;
    public TrieNode() {
        this.children = new HashMap<>();
        this.isWordEnd = false;
    }
}

class Trie{
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();;
    }

    public void insertToTrie(String word){
        TrieNode node = this.root;  // Use a local variable to traverse. In the insertToTrie method, you are using this.node to traverse and insert the characters into the trie.
        // However, you should not modify the root node itself; instead, you should use a local variable to traverse the trie and make changes.
        char[] wordCh = word.toCharArray();
        for(int i=0; i<wordCh.length; i++){
            if(!node.children.containsKey(wordCh[i])){
                node.children.put(wordCh[i], new TrieNode());
            }
            node = node.children.get(wordCh[i]);
        }
        node.isWordEnd = true;
    }
}

public class LongestWordInDict {
     int maxLen = 0;
     String longestCommonPrefix = "";

    public void search(TrieNode node, int currLength, String prefix){


            if (currLength > maxLen || currLength == maxLen && prefix.compareTo(longestCommonPrefix) < 0) {
                maxLen = currLength;
                longestCommonPrefix = prefix;
            }

            for (Character childChar : node.children.keySet()) {
                TrieNode childNode = node.children.get(childChar);
                if(childNode.isWordEnd){
                    search(childNode, currLength + 1, prefix + childChar);
                }
            }
        }


    public void ans(String[] words){
        Trie trie = new Trie();
        for(int i=0; i<words.length;i++){
            trie.insertToTrie(words[i]);
        }
        search(trie.root,0,"");
        System.out.println(longestCommonPrefix.toString());
    }

    public static void main(String[] args) {

//        List<String> words = new ArrayList<>();
//        words.add("w");
//        words.add("wo");
//        words.add("wor");
//        words.add("worl");
//        words.add("world");
//        words.add("happy");
        String[] words = new String[]{"a","banana","app","appl","ap","apply","apple"};

        LongestWordInDict longestWordInDict = new LongestWordInDict();
        longestWordInDict.ans(words);

    }
}

