package coding.Salesforce;

public class reverseString {

    public static void main(String[] args) {
        String s = "Welcome to Coding Ninjas";
        //approach 1
//         System.out.println(reverseString(s));
         //approach 2
         System.out.println(reverseString2(s));
    }

    public static String reverseString(String str)
    {
        //Write your code here
        StringBuilder sb = new StringBuilder();
        for(int i=str.length()-1; i>=0; i--){

            StringBuilder sub = new StringBuilder();
            while(i>=0 && !Character.isWhitespace(str.charAt(i))){
                sub.append(str.charAt(i));
                i--;
            }
            System.out.println(sub);
            sb.append(sub.reverse()).append(' ');
        }
        String res = sb.toString();
        return res;
    }

    public static String reverseString2(String str)
    {
        //Write your code here
        String[] strings = str.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=strings.length-1; i>=0; i--){
            sb.append(strings[i]).append(' ');
        }
        String ans = sb.toString().trim();
        return ans;
    }

}
