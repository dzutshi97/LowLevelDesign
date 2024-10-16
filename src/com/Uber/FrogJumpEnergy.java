package com.Uber;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/5817816/Frog-DSA-round-question
 * Given a frog with limited energy capacity standing on a source node. Initially, it has full energy. Moving from one node to another result in a reduction in energy by 1 and an increase in time by 1. There are energy drinks present at some nodes that it can make use of to increase energy. If its energy reduces to 0, it dies. Find the minimum time taken by the frog to reach the destination node. If it is impossible return -1.
 *
 *
 * Help me to solve this
 */
/**
 *
 If the frog could move to non-adjacent nodes in a graph, the problem complexity increases, and using Dijkstra's algorithm would become more relevant. Here’s why:

 When to Use Dijkstra's Algorithm
 Graph Representation: In this scenario, the frog is navigating a graph where nodes represent positions (or states) and edges represent valid jumps, which may not be restricted to adjacent nodes. This makes it necessary to consider various paths, which Dijkstra's algorithm excels at handling.

 Variable Weights: If each edge (the jump between nodes) has a different weight (for example, the energy cost associated with jumping to certain nodes), then Dijkstra's algorithm is appropriate. This algorithm effectively finds the shortest path in graphs with weighted edges.

 Efficient Pathfinding: Dijkstra’s algorithm systematically explores the most promising paths first, making it well-suited for finding the minimum time or energy cost needed to reach a specific node when multiple paths are available. If the frog can jump across non-adjacent nodes with different energy costs, Dijkstra’s approach ensures that all possibilities are considered optimally.

 Example Scenario
 Consider a scenario where the frog can jump from position
 𝑖
 i to position
 𝑗
 j where
 𝑗
 j is not necessarily
 𝑖
 +
 1
 i+1 or
 𝑖
 −
 1
 i−1. The cost of jumping from
 𝑖
 i to
 𝑗
 j may depend on the positions in between or could vary based on energy drinks available at specific nodes
 */
// Adjacent neighbours case (i+1 and i-1). Dijkstra's not needed. A simple BFS would work
//// COnsider the scenario that frog can move one position at a time forward or backward. (For backward case, DFS cannot be used only BFS) Dijkstra's might be an overkill here
class FrogPos{
    int time;
    int energy;
    int pos;

    public FrogPos(int time, int energy, int pos) {
        this.time = time;
        this.energy = energy;
        this.pos = pos;
    }
}
public class FrogJumpEnergy {

    public int findMinTimeFrogJump(int[] energyDrinks, int[] frogPositions, int dest, boolean[] visited){
        Queue<FrogPos> q = new LinkedList<>();

        // Start from position 0 with full energy
        q.offer(new FrogPos(0, frogPositions[0], 0));
        visited[0] = true;


        while(!q.isEmpty()){
            FrogPos curr = q.poll();
            int currPos = curr.pos;
            int currEnergy = curr.energy;
            int currTime = curr.time;

            if(currPos == dest){
                return currTime;
            }
            if(currEnergy <0){
                continue;
            }

            //move forward
            if(currPos + 1 < frogPositions.length)
            {       //if(energyDrinks[currPos + 1] != 0)
                    //currEnergy  = currEnergy + energyDrinks[currPos + 1];
                if(!visited[currPos + 1]) {
                    q.offer(new FrogPos(currPos + 1, energyDrinks[currPos + 1] + currEnergy - 1, currTime + 1));
                    visited[currPos + 1] = true;
                }
            }

            //move backward
            if(currPos -1 >= 0)
            {
                if(!visited[currPos - 1]){
                    q.offer(new FrogPos(currPos-1, energyDrinks[currPos - 1]+ currEnergy -1, currTime +1));
                    visited[currPos - 1] = true;
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) {
        FrogJumpEnergy frogJump = new FrogJumpEnergy();

        // Test case 1: Simple scenario
        int[] energyDrinks1 = {0, 1, 0, 0, 1}; // Energy drinks available at positions 1 and 4
        int[] frogPositions1 = {3, 2, 1, 2, 3}; // Frog energy at start
        int dest1 = 4; // Destination is at position 4
        boolean[] visited1 = new boolean[frogPositions1.length];
        int result1 = frogJump.findMinTimeFrogJump(energyDrinks1, frogPositions1, dest1, visited1);
        System.out.println("Test Case 1 Result: " + result1); // Expected: 5

        // Test case 2: Impossible to reach destination
        int[] energyDrinks2 = {0, 0, 0, 0, 0}; // No energy drinks
        int[] frogPositions2 = {1, 1, 1, 1, 1}; // Frog energy at start
        int dest2 = 4; // Destination is at position 4
        boolean[] visited2 = new boolean[frogPositions2.length];
        int result2 = frogJump.findMinTimeFrogJump(energyDrinks2, frogPositions2, dest2, visited2);
        System.out.println("Test Case 2 Result: " + result2); // Expected: -1

        // Test case 3: Large number of positions with varying energy drinks
        int[] energyDrinks3 = {0, 2, 0, 3, 0, 1, 2}; // Energy drinks at various positions
        int[] frogPositions3 = {5, 5, 5, 5, 5, 5, 5}; // Frog energy at start
        int dest3 = 6; // Destination is at position 6
        boolean[] visited3 = new boolean[frogPositions3.length];
        int result3 = frogJump.findMinTimeFrogJump(energyDrinks3, frogPositions3, dest3, visited3);
        System.out.println("Test Case 3 Result: " + result3); // Expected: 6 or less

        // Test case 4: Start with maximum energy
        int[] energyDrinks4 = {0, 0, 0, 0, 5}; // Energy drinks only at destination
        int[] frogPositions4 = {10, 10, 10, 10, 10}; // Frog energy at start
        int dest4 = 4; // Destination is at position 4
        boolean[] visited4 = new boolean[frogPositions4.length];
        int result4 = frogJump.findMinTimeFrogJump(energyDrinks4, frogPositions4, dest4, visited4);
        System.out.println("Test Case 4 Result: " + result4); // Expected: 4

    }
}
