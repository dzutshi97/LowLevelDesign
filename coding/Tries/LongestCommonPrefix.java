package coding.Tries;


    /**
     * Longest common prefix
     *  [abcd, abce, abcf, dog]
     *
     *    root  <-- branching point. Returns "" as the prefix
     *   /    \
     *  a,N   d,N
     *    |     |
     *    b,N   o,N
     *      |
     *      c, N
     *         |
     *         (d, N), (e,N), (f,N) <-- branching point. Returns "abc" as the common prefix
     *
     *         i.e. Until c we have a common prefix. Thus, wherever we find a branching point,
     *         all the chars above it are part of a common prefix of 1 or more strings
     *
     */
    //GPT code. TODO: Code on your own
    class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // Assuming only lowercase letters 'a' to 'z'
            isEndOfWord = false;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Insert a word into the Trie
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        // Function to find the longest common prefix
        public String longestCommonPrefix() {
            TrieNode current = root;
            StringBuilder prefix = new StringBuilder();

            while (countChildren(current) == 1 && !current.isEndOfWord) {
                for (int i = 0; i < 26; i++) {
                    if (current.children[i] != null) {
                        current = current.children[i];
                        prefix.append((char)(i + 'a')); // Add the character to the prefix
                        break;
                    }
                }
            }
            return prefix.toString();
        }

        // Helper function to count the number of children of a node
        private int countChildren(TrieNode node) {
            int count = 0;
            for (TrieNode child : node.children) {
                if (child != null) {
                    count++;
                }
            }
            return count;
        }
    }

    public class LongestCommonPrefix {
        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            Trie trie = new Trie();

            // Insert all words into the Trie
            for (String word : strs) {
                trie.insert(word);
            }

            // Find and return the longest common prefix
            return trie.longestCommonPrefix();
        }

        public static void main(String[] args) {
            String[] words = {"flower", "flow", "flight"};
            System.out.println("Longest Common Prefix: " + longestCommonPrefix(words)); // Output: "fl"
        }
    }

