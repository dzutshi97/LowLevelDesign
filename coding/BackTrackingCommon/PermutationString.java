package coding.BackTrackingCommon;


import java.util.ArrayList;
import java.util.List;

//find all permutation of a string
// ~= to LC: Permutations
public class PermutationString {
    public static List<String> permute(String s){
        StringBuilder tempStr = new StringBuilder();
        List<String> perms = new ArrayList<>();
        backtrack(tempStr, perms ,s);
        return perms;
    }

    private static void backtrack(StringBuilder tempStr, List<String> perms, String input){
        if(tempStr.length() == input.length()){
            perms.add(tempStr.toString());
            return;
        }

        for(int i=0; i<input.length(); i++){
            if(tempStr.toString().contains(String.valueOf(input.charAt(i)))){
                continue;
            }
            tempStr.append(input.toString().charAt(i));
            backtrack(tempStr,perms,input); //take case^
            tempStr.deleteCharAt(tempStr.length()-1); //not take case
        }

    }


    public static void main(String[] args) {

        List<String> ans = permute("abc");
        System.out.println(ans);

    }
}
