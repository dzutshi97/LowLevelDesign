package coding.Stack;

import java.util.Stack;

//https://leetcode.com/discuss/interview-experience/1937742/bitgo-sde2-feb-2022-reject

/** Question:
 * A valid string is any assortment of alphanumeric characters with parentheses balanced
 *
 * ( comes before )
 * each ( needs a )
 *
 * s = ")))a("
 * "a"
 *
 * s = "((()"
 * "()"
 *
 * s = "c(ry(pt(o)"
 * "crypt(o)" or "c(rypto)" or "cry(pto)" ```
 *  Solution
 * *   Use stack to store the parenthesis and validate the parenthesis and iterate the strings. (Let me know if you want me to share the code)
 */
public class AssortedString {

    static Stack<Integer> st = new Stack<>();
    static int closing = -1;
    static  int start = -1;
    
    public static String solve(String s){
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                st.push(i);
            } else if (s.charAt(i) == ')' && !st.isEmpty() && s.charAt(st.peek()) == '(') {
                st.pop();
                closing = i;
                start = st.peek();
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            if(Character.isAlphabetic(s.charAt(i)) || i== start || i== closing){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String res = solve("c(ry(pt(o)");
        System.out.println(res);
    }
}
