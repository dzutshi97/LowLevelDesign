package coding.Atlassian;
import java.util.HashMap;
import java.util.Map;

public class FindAnagram2 {

        public void solve(String[] words, String mainWord) {
            for (String word : words) {
                Map<Character, Integer> wordFreq = new HashMap<>();
                for (char c : word.toCharArray()) {
                    wordFreq.put(c, wordFreq.getOrDefault(c, 0) + 1);
                }

                int wordLength = word.length();
                Map<Character, Integer> windowFreq = new HashMap<>();

                // Initialize the frequency map for the first window
                for (int i = 0; i < wordLength && i < mainWord.length(); i++) {
                    char c = mainWord.charAt(i);
                    windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);
                }

                if (wordFreq.equals(windowFreq)) {
                    System.out.println("Anagram found for word: " + word + " at index 0");
                    return;
                }

                for (int i = wordLength; i < mainWord.length(); i++) {
                    // Slide the window
                    char addChar = mainWord.charAt(i);
                    char removeChar = mainWord.charAt(i - wordLength);

                    // Add the new character
                    windowFreq.put(addChar, windowFreq.getOrDefault(addChar, 0) + 1);

                    // Remove the old character
                    windowFreq.put(removeChar, windowFreq.get(removeChar) - 1);
                    if (windowFreq.get(removeChar) == 0) {
                        windowFreq.remove(removeChar);
                    }

                    if (wordFreq.equals(windowFreq)) {
                        System.out.println("Anagram found for word: " + word + " at index " + (i - wordLength + 1));
                        return;
                    }
                }
            }
        }

        public static void main(String[] args) {
            String mainWord = "xyzlonmrqp";
            String[] words = {"xxx", "mno", "pqr"};

            FindAnagram finder = new FindAnagram();
            finder.solve(words, mainWord);
        }
    }

/**
 * 2. Frequency Counting Solution
 * Time Complexity Analysis:
 *
 * Building Frequency Map for Each Word:
 *
 * Building the frequency map for a word of length
 * 𝑘
 * k takes
 * 𝑂
 * (
 * 𝑘
 * )
 * O(k).
 * For
 * 𝑤
 * w words, this step has a complexity of
 * 𝑂
 * (
 * 𝑤
 * ⋅
 * 𝑘
 * )
 * O(w⋅k).
 * Sliding Window Technique:
 *
 * Initializing the frequency map for the first window of length
 * 𝑘
 * k takes
 * 𝑂
 * (
 * 𝑘
 * )
 * O(k).
 * Sliding the window through mainWord involves updating the frequency map for each new character and removing the frequency of the old character. Each update operation is
 * 𝑂
 * (
 * 1
 * )
 * O(1) assuming efficient hash map operations.
 * You perform this operation
 * 𝑛
 * −
 * 𝑘
 * +
 * 1
 * n−k+1 times, where
 * 𝑛
 * n is the length of mainWord. Thus, the complexity is
 * 𝑂
 * (
 * (
 * 𝑛
 * −
 * 𝑘
 * +
 * 1
 * )
 * ⋅
 * 1
 * )
 * O((n−k+1)⋅1), which simplifies to
 * 𝑂
 * (
 * 𝑛
 * )
 * O(n).
 * Total Time Complexity:
 *
 * For each word, the complexity is
 * 𝑂
 * (
 * 𝑘
 * )
 * +
 * 𝑂
 * (
 * 𝑛
 * )
 * O(k)+O(n). Given
 * 𝑤
 * w words, the overall complexity is
 * 𝑂
 * (
 * 𝑤
 * ⋅
 * (
 * 𝑘
 * +
 * 𝑛
 * )
 * )
 * O(w⋅(k+n)).
 */
