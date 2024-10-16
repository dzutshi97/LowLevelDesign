package com.Uber;

import java.util.*;

//LC: Bus routess

/**
 * Note: This is a working code. It is Accepted in LC
 */
public class BusRoutes {

    public int numBusesToDestination(int[][] routes, int source, int target){

        //build adj graph of: stop -> List<routes>
        // maintain a visited list to avoid visting same route again as we visit all stops when we process a route/bus
        //multi source BFS to find out bus counts. Each level in BFS will represent the no of bus taken

        HashMap<Integer, List<Integer>> adj = new HashMap<>();
        boolean[] visitedRoutes = new boolean[routes.length];
        if(source == target){
            return 0;
        }
        /**
         * Build adj: source -> List<Routes> i.e. routeIdx's
         */
        for(int i=0; i<routes.length;i++){
            for(int j=0; j<routes[i].length; j++){
                if(!adj.containsKey(routes[i][j])){
                    adj.put(routes[i][j], new ArrayList<>());
                }
                List<Integer> list =  adj.get(routes[i][j]);
                list.add(i);
            }
        }
        /**
         * Multi source BFS
         */
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (routes[i][j] == source) {
                    queue.offer(i); //routeIdx
                    visitedRoutes[i] = true;
                }
            }
        }
        int busCount =1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size;i++){
                int currBus = queue.poll(); //bus or call it route
                for(int stop: routes[currBus]){
                    if(stop == target){
                        return busCount;
                    }
                    if(adj.containsKey(stop)){
                        for(Integer adjRoute: adj.get(stop)){
                            if(!visitedRoutes[adjRoute]){
                                visitedRoutes[adjRoute] = true;
                                queue.offer(adjRoute);
                            }
                        }
                    }
                }

            }
            busCount++;
        }
        return -1;
    }
    public static void main(String[] args) {

    }
}
