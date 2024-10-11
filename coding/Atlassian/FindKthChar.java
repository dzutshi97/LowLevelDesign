package coding.Atlassian;

public class FindKthChar {


    public static char findKthCharacter(String encryptedString, int K){

        StringBuilder decryptedStr = new StringBuilder();
        for(int i=0; i<encryptedString.length();){

            StringBuilder newStr = new StringBuilder();
            while(i < encryptedString.length() && Character.isLetter(encryptedString.charAt(i)) ){
                newStr.append(encryptedString.charAt(i));
                i++;
            }

            StringBuilder digitStr = new StringBuilder();
            while( i < encryptedString.length() && Character.isDigit(encryptedString.charAt(i))){
                digitStr.append(encryptedString.charAt(i));
                i++;
            }

            if(digitStr.length() > 0){
                int freq = Integer.parseInt(digitStr.toString());
                if(freq > 0){
                    for(int j=0; j<freq; j++){
                        decryptedStr.append(newStr);
                        if(decryptedStr.length() >= K){
                            return decryptedStr.charAt(K -1);
                        }
                    }
                }
            }
        }
        return encryptedString.charAt(K);
    }

    public static void main(String[] args) {
         String encryptedString = "a2b3cd3"; //aabbbcdcdcd
         int K = 8;
        System.out.println("The " + K + "th character is: " + findKthCharacter(encryptedString, K));  // Output: c
//        System.out.println(findKthCharacter(encryptedString, K)); // output should be - aabbbcdcdcd
    }
}
/**
 * https://www.ambitionbox.com/interviews/atlassian-question/find-kth-character-of-decrypted-string-you-have-been-given-an-encrypted-string-where-repetitions-of-substrings-are-represented-as-substring-followed-by-the-count-of-jqZ9OPYR
 * public class FindKthCharacter {
 *
 *     public static char findKthCharacter(String S, int K) {
 *         StringBuilder decryptedString = new StringBuilder();
 *         int i = 0;
 *
 *         while (i < S.length()) {
 *             // Read the substring
 *             StringBuilder substring = new StringBuilder();
 *             while (Character.isLetter(S.charAt(i))) {
 *                 substring.append(S.charAt(i));
 *                 i++;
 *             }
 *
 *             // Read the number (frequency)
 *             int freqStart = i;
 *             while (i < S.length() && Character.isDigit(S.charAt(i))) {
 *                 i++;
 *             }
 *             int freq = Integer.parseInt(S.substring(freqStart, i));
 *
 *             // Append the substring 'freq' times
 *             for (int j = 0; j < freq; j++) {
 *                 decryptedString.append(substring);
 *                 if (decryptedString.length() >= K) {
 *                     return decryptedString.charAt(K - 1);
 *                 }
 *             }
 *         }
 *
 *         // If we somehow reach here, the input was invalid
 *         return '\0'; // Indicating an error (though the input is assumed to be valid)
 *     }
 *
 *     public static void main(String[] args) {
 *         String encryptedString = "a2b3cd3";
 *         int K = 8;
 *         System.out.println("The " + K + "th character is: " + findKthCharacter(encryptedString, K));  // Output: c
 *     }
 * }
 */
