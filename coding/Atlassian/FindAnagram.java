package coding.Atlassian;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FindAnagram {

    /**
     * Atlassian Karat round -
     * Given a main word and a list of words, we need to find the word from the list whose anagram is present in a given string. HashMap problem
     */

    public static void solve(String[] words, String mainWord){

        for(String word: words){

            char[] tmp = word.toCharArray();
            Arrays.sort(tmp);
            String wrd = new String(tmp);

            for(int i=0; i<= mainWord.length()-word.length(); i++){
                String substr = mainWord.substring(i,i+word.length());

                char[] tmp1 = substr.toCharArray();
                Arrays.sort(tmp1);
                String wrd1 = new String(tmp1);

                if(wrd.equals(wrd1)){
                    System.out.println("anagram found! -"+substr);
                    return;
                }
            }
        }

    }
    public static void main(String[] args) {

        String mainWord = "xyzlonmrqp";
        String[] words = {"xxx", "mno","pqr"};
        solve(words,mainWord);

    }
}
//TC: O(k log k) where k is length of string - logK for sorting
