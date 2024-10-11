package com.Uber;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem statement
 * You are given two words START and END and a dictionary of words WORDDIC. All the words in WORDDIC are unique. START and END may or may not be present in WORDDIC.
 *
 * You can make a transition from one word to another if both the words are present in WORDDIC and both words have exactly one letter differ. Your task is to find the number of minimum distance paths from START to END using the transitions explained above.
 *
 * The distance between two words is the number of transitions used to go from one word to another.
 *
 * For example:
 *
 * START = “cow” END = “mot”
 * WORDDIC = [“cow”, “mow”, “cot”, “mot”]
 * Here answer is 2 there are two paths of distance 2 ,
 * “cow” -> “mow” -> “mot”
 * “cow” -> “cot”  -> “mot”
 * https://www.naukri.com/code360/problems/minimum-words-distance_1266044?topList=top-salesforce-coding-interview-questions&problemListRedirection=true&page=2
 *
 */
    public class MinWordsDistance {

    public static void main(String[] args) {
        String[] words = new String[]{"cow", "mow", "cot", "mot"};
        String start = "cow";
        String end = "mot";
        Solve1 solve1 = new Solve1();
        System.out.println(solve1.minWordsDistance(words,start,end));
    }
}
class Pair{
    String word;
    int stepsSoFar;

    public Pair(String word, int stepsSoFar) {
        this.word = word;
        this.stepsSoFar = stepsSoFar;
    }
}
class Solve1{

    public int minWordsDistance(String[] words, String start, String end){
        return bfs(words, start,end);
    }



    public int bfs(String[] words, String start, String end){
        Queue<Pair> q = new LinkedList<>();
        HashSet<String> wordsCheck = new HashSet<>();
        for(String word: words){
            wordsCheck.add(word);
        }
        int steps=0;
        q.offer(new Pair(start,0));

        while(!q.isEmpty()){
            Pair pair = q.poll();
            String word = pair.word;
            int currSteps = pair.stepsSoFar;
            if(word.equals(end)){
                return currSteps;
            }
            for(int i=0; i<word.length(); i++){
                for(char c ='a'; c<'z'; c++){

                    char[] wordCh = word.toCharArray();
                    wordCh[i] = c;

                    String newWord = new String(wordCh);

                    if(wordsCheck.contains(newWord)){
                        wordsCheck.remove(newWord);
                        q.offer(new Pair(newWord,currSteps+1));
//                        break;
                    }
                }
            }
//            steps++;
        }
        return -1;
    }
}
