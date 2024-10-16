package com.Uber;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//// Non adjacent neighbours case. Dijkstra's is needed here as simple BFS would not help for non adjacent node jumps.
// ~= Cheapest Flight with K stops
class FrogPos1{
    int time;
    int energy;
    int val;

    public FrogPos1(int time, int energy, int val) {
        this.time = time;
        this.energy = energy;
        this.val = val;
    }
}
public class FrogJumpEnergy2 {

    public int solve(HashMap<Integer, List<Integer>> graph, int dest, int[] energyDrinks) {

        PriorityQueue<FrogPos1> pq = new PriorityQueue<>((FrogPos1 a, FrogPos1 b) -> a.time - b.time); //note - not sort by energy. read expln below.
        int maxEnergy = Arrays.stream(energyDrinks).max().orElse(0) + 1;

        int[][] dist = new int[graph.size()][maxEnergy];

        for (int[] dis : dist) {
            Arrays.fill(dis, Integer.MAX_VALUE);
        }
        pq.offer(new FrogPos1(0, energyDrinks[0], 0));
//        dist[0][0] = 0; //initial time
        dist[0][energyDrinks[0]] = 0; // Assuming starting energy is based on the first node's energy drink


        while (!pq.isEmpty()) {
            FrogPos1 curr = pq.poll();
            int currTime = curr.time;
            int currEnergy = curr.energy;
            int val = curr.val;

            if (val == dest) {
                return currTime;
            }

            if (currEnergy < 0) {
                continue;
            }

            for (Integer neigh : graph.get(val)) {

                int newTime = currTime + 1;
                int newEnergy = currEnergy -1 + energyDrinks[neigh]; //refill energy drink
                if (newEnergy >= 0 && newEnergy < maxEnergy) //to avoid ArrayIndxOutOfBounds excptn
                    if (newTime < dist[neigh][newEnergy]) {
                        dist[neigh][newEnergy] = newTime;
                        pq.offer(new FrogPos1(newTime, newEnergy, neigh));
                    }
            }
            }

    return -1;
    }

    public static void main(String[] args) {

        // Sample test case 1
        HashMap<Integer, List<Integer>> graph1 = new HashMap<>();
        graph1.put(0, Arrays.asList(1, 2));
        graph1.put(1, Arrays.asList(0, 3, 4));
        graph1.put(2, Arrays.asList(0, 4));
        graph1.put(3, Arrays.asList(1));
        graph1.put(4, Arrays.asList(1, 2));

        int[] energyDrinks1 = {1, 2, 0, 1, 1}; // energy available at each node
        int dest1 = 3;
        FrogJumpEnergy2 frogJumpEnergy2 = new FrogJumpEnergy2();
        int result1 = frogJumpEnergy2.solve(graph1, dest1, energyDrinks1);
        System.out.println("Minimum time to reach destination: " + result1); // Expected: 2

        // Sample test case 2
        HashMap<Integer, List<Integer>> graph2 = new HashMap<>();
        graph2.put(0, Arrays.asList(1));
        graph2.put(1, Arrays.asList(0, 2, 3));
        graph2.put(2, Arrays.asList(1));
        graph2.put(3, Arrays.asList(1, 4));
        graph2.put(4, Arrays.asList(3));

        int[] energyDrinks2 = {0, 1, 1, 1, 0}; // energy available at each node
        int dest2 = 4;
        int result2 = frogJumpEnergy2.solve(graph2, dest2, energyDrinks2);
        System.out.println("Minimum time to reach destination: " + result2); // Expected: 3


        // Sample test case 3 (unreachable destination)
        HashMap<Integer, List<Integer>> graph3 = new HashMap<>();
        graph3.put(0, Arrays.asList(1));
        graph3.put(1, Arrays.asList(0));
        int[] energyDrinks3 = {0, 0}; // energy available at each node
        int dest3 = 2; // destination not present
        int result3 = frogJumpEnergy2.solve(graph3, dest3, energyDrinks3);
        System.out.println("Minimum time to reach destination: " + result3); // Expected: -1

    }
}
/**
 * 1. Cheapest Flights Within K Stops
 * Objective: The primary goal is to minimize the cost of travel while adhering to a maximum number of stops. This makes it crucial to prioritize paths with fewer stops, as the cost of travel is often influenced more significantly by the number of legs in a journey than the distance itself.
 *
 * Sorting by Stops:
 *
 * By prioritizing fewer stops, you explore options that meet the constraints (k stops) while also checking the cost. This ensures that you don't exceed the allowed number of stops, allowing you to find the cheapest valid route effectively.
 * If you sorted solely by price, you might miss paths that fit within the stop constraint but have a higher price.
 * 2. Frog Jump Energy Problem
 * Objective: The aim is to reach a destination in the least amount of time while managing energy efficiently. This involves balancing the need to move quickly against the limited energy available for jumps.
 *
 * Sorting by Time:
 *
 * Prioritizing time allows the algorithm to focus on reaching the destination as quickly as possible while still managing energy constraints.
 * It ensures that energy is only a factor in the context of how quickly a node can be reached, which is more aligned with the objective of minimizing travel time rather than just managing energy levels.
 * Unlike the flight problem, where the number of stops is directly related to costs, energy management in the frog problem is more about reaching nodes efficiently and can be evaluated once time is prioritized.
 * Key Differences
 * Context and Constraints: The context of each problem informs the sorting criteria. In the flight problem, the number of stops is directly linked to costs, while in the frog problem, the main concern is the time taken to reach the destination while also managing energy.
 *
 * Dynamic vs. Static Constraints: In flight problems, stops are a static constraint with direct impacts on costs, while in the frog problem, energy is a dynamic constraint that affects each move. Thus, the prioritization shifts depending on whether you're looking for the cheapest route (stops) or the quickest route (time).
 */
