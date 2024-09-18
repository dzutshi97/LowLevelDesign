package coding.Google;


//Question: https://leetcode.com/discuss/interview-question/5343977/Google-L3-Onsite-First-Round

import java.util.HashMap;

/**
 * Given a string with any format containing only english letters,
 * replace every percent-sign-sorrounded word (%EXAMPLE%) with the corresponding variable given inside a dictionary passed as an argument.
 * Example:
 * input = "home/usr/lib/%EXAMPLE%", {EXAMPLE: "testfile.tx"}
 * output =  "home/usr/lib/testfile.txt"
 *
 * input = "Hi %USER% how are you doing today %DATE%?", {USER: "John", DATE: "01/01/2024"}
 * output =  "Hi John how are you doing today 01/01/2024?"
 *
 * Follow Up Question:
 *
 * Imagine your dictionary with variables had nested variables. For example:
 * input = "Hi %USER%", {USER: "%PRONOUN% John", PRONOUN: "Mr."}
 *
 * TODO: Similar question - https://leetcode.com/discuss/interview-experience/5341224/Stripe-or-Backend-Engineer-or-Bangalore-or-Jun-2024-or-Reject/2469970
 */
public class StringReplacement {

    public static String solve(String s, HashMap<String,String> map){

        StringBuilder sb = new StringBuilder(s);

        for(int i=0; i<sb.length(); i++){
            if(sb.charAt(i) == '%'){
                int j=i+1;
                while(j < sb.length() && sb.charAt(j) != '%'){
                    j++;
                }
                String sub = s.substring(i+1, j);
                if(map.containsKey(sub)){
                    sb = sb.replace(i,j+1,map.get(sub));
                }
                // instead of making i = j  , I am doing i-- , this will cover the follow up question if the dict itself have '%' in it.
                i--; //good!!
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {

        String input = "home/usr/lib/%EXAMPLE%";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("EXAMPLE", "testfile.txt");
        String ans = solve(input,hm);
        System.out.println(ans);
    }
}
