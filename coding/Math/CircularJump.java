/**
https://leetcode.com/discuss/interview-question/5185522/Circular-Jump-Problem/
There were N stones arranged in a circle numbered from 0 to N−1 in a clockwise direction. Alice observes peculiar frog resting on stone 0. The frog is peculiar because it jumps distance of i in a clockwise direction for every ith minute.
i.e at the 0th minute the frog is at stone 0.
at the 1st minute the frog is at stone 1.
at the 2nd minute the frog is at stone 3.
at the 3rd minute the frog is at stone 6.
ans so on...

You need to check if all stones will be visited by the frog at some point in time or not. You are given an integer N denoting the number of stones and the frog will keep jumping till infinity.
**/
//Approach 1 - Logic

import java.util.*;

public class FrogJump {
    public static boolean willVisitAllStones(int N) {
        Set<Integer> visitedStones = new HashSet<>();
        int currentPosition = 0;
        int i = 0;

        while (visitedStones.size() < N) { //or should we go till 2n?
            if (!visitedStones.add(currentPosition)) {
                // If we revisit a position before visiting all stones, it means we're stuck in a loop
                return false;
            }
            currentPosition = (currentPosition + i) % N;
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        int N1 = 7; // Example input
        System.out.println("For N = " + N1 + ": The frog will visit all stones: " + willVisitAllStones(N1));

        int N2 = 8; // Another example input
        System.out.println("For N = " + N2 + ": The frog will visit all stones: " + willVisitAllStones(N2));
    }
}




//Approach 2 - Math
/**
If N is a power of 2, the frog will not visit all stones.
If N is not a power of 2, the frog will visit all stones.
**/
public class FrogJump {
    public static boolean willVisitAllStones(int N) {
        // Check if N is a power of 2
        return (N & (N - 1)) != 0;
    }

    public static void main(String[] args) {
        int N = 7; // Example input
        if (willVisitAllStones(N)) {
            System.out.println("The frog will visit all stones.");
        } else {
            System.out.println("The frog will not visit all stones.");
        }
    }
}
