package coding.BFS;
import java.util.*;

//Expln in TUF article.
public class WordLadder {

    class Pair{
        String word;
        int steps;
        public Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
    PriorityQueue<Pair> prq = new PriorityQueue<>((a, b) -> a.steps - b.steps);

    public int start(String beginWord, String endWord, List<String> wordList){
        Set<String> wordSet = new HashSet<>(wordList);
        prq.offer(new Pair(beginWord, 1));
        wordSet.remove(beginWord);
        return wordLadder(endWord, wordSet);
    }
    public int wordLadder(String endWord, Set<String> wordSet){

        while(!prq.isEmpty()){
            Pair pair = prq.poll();
            String currWord = pair.word;
            int currSteps = pair.steps;

            if(currWord.equals(endWord)){
                return currSteps;
            }

            for(int i=0; i<currWord.length(); i++){
                for(char ch ='a'; ch <= 'z'; ch++){

                    char[] newWord = currWord.toCharArray();
                    newWord[i] = ch;
//                   String nWord = newWord.toString();
                    String nWord = new String(newWord); //todo: note!

                    if(wordSet.contains(nWord)){
                        prq.offer(new Pair(nWord, currSteps + 1));
                        wordSet.remove(nWord);
                    }

                }

            }

        }
        return 0;
    }

}
class  Solution{

    public static void main(String[] args) {

        String[] words = {"hot","dot","dog","lot","log","cog"};
        String beginWord = "hit";
        String endWord = "cog";
        //ans - 5
        WordLadder wordLadder = new WordLadder();
        List<String> wordList = Arrays.asList(words);

        int ans = wordLadder.start(beginWord,endWord,wordList);
        System.out.println(ans);
    }

}
