package coding.Salesforce;
import java.util.*;
public class kMostFreqWords {

    public static void main(String[] args) {
        String[] words = {"i","love","codingninjas","i","love","coding"};
        int k=2;
        System.out.println(kMostFreqWords(words, k));
    }


    public static List<String> kMostFreqWords(String[] words, int K) {
        // Write your code here.
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> freq = new HashMap<>();

        for(int i=0; i<words.length; i++){
            int count = freq.getOrDefault(words[i], 0) + 1;
            freq.put(words[i],count);
        }
        //todo: understand this please
        Queue<String> pq = new PriorityQueue<>((String str1, String str2) -> (freq.get(str2) == freq.get(str1)) ? str1.compareTo(str2) : freq.get(str2) - freq.get(str1)) ;
        for(String word: freq.keySet()){
            pq.offer(word);
        }

        for(int i=0; i<K;i++){
            ans.add(pq.poll());
        }
        return ans;
    }
}
