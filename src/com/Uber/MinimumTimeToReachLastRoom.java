class Solution {
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,-1},{0,1}};
    public int minTimeToReach(int[][] moveTime) {
        //Dijkstra's 
        // int n = movieTime.length;
        // int m = movieTime[0].length;

        int n = moveTime.length, m = moveTime[0].length;

        int[][] time = new int[n][m];
        for(int[] row: time){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<Element> pq = new PriorityQueue<>((Element a, Element b) -> a.time - b.time);   
        pq.offer(new Element(0,0,0));
        time[0][0] = 0;
    
        while(!pq.isEmpty()){

            Element curr = pq.poll();
            int r = curr.row;
            int c= curr.col;

            if(r == moveTime.length-1 && c== moveTime[0].length-1){
                return curr.time;
            }

            for(int[] dir: dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];
                if(nr < 0 || nc < 0 || nr >= moveTime.length || nc >= moveTime[0].length){
                    continue;
                }

                int nTime = Math.max(curr.time, moveTime[nr][nc]) + 1; //wait if necessary

                if(nTime < time[nr][nc]){
                    time[nr][nc] = nTime;
                    pq.offer(new Element(nr, nc, nTime));
                }

            }

        }
        return -1; 

    }
}
class Element{
    int row;
    int col;
    int time;
    public Element(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
