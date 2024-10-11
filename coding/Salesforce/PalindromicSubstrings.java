package coding.Salesforce;

import java.util.ArrayList;
import java.util.List;

public class PalindromicSubstrings {

    public static void main(String[] args) {
        int ans= palindromicSubstrings("abc");
        System.out.println(ans);
    }

    static List<String> ans = new ArrayList<String>();
    public static int palindromicSubstrings(String str) {

        // Your code goes here.
        int evenCnt=0;
        int oddCnt=0;
        for(int i=0; i<str.length(); i++){
            evenCnt += expandFromCentre(i,i+1, str);
            oddCnt += expandFromCentre(i,i, str);
        }
        System.out.println(ans);
        return evenCnt+oddCnt;
    }

    public static int expandFromCentre(int i, int j, String s){
        int count =0;
        while(i>= 0 && j<s.length() && s.charAt(i) == s.charAt(j)){
            // Add only the palindromic substring
            ans.add(s.substring(i+1,j));
            /**
             * When adding the substring using i+1 and j,
             * the correct palindrome is the one inside the while loop, not the substring after the loop exits.
             */
            count++;
            i--;
            j++;
        }
        return count;
    }
}
