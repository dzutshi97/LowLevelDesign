package org.example;

import java.util.ArrayList;
import java.util.List;

class DisjointSet{

    int[] parent;
    int[] size;
    public DisjointSet(int V){
        parent = new int[V+1];
        size = new int[V+1];
        for(int i=0; i<=V; i++){
                this.parent[i] = i;
                this.size[i] = 1;
        }
    }

    public int findUltimateParent(int node){
        if(parent[node] == node){
            return node;
        }
        return parent[node] = findUltimateParent(parent[node]);
    }

    public void unionBySize(int u, int v){
        int ultimateParentOfU = findUltimateParent(u);
        int ultimateParentOfV = findUltimateParent(v);

        if(ultimateParentOfU == ultimateParentOfV){
            return;
        }
        if(size[ultimateParentOfU] < size[ultimateParentOfV]){
            parent[ultimateParentOfU] = ultimateParentOfV;
            size[ultimateParentOfV] += size[ultimateParentOfU];
        }else{
            parent[ultimateParentOfV] = ultimateParentOfU;
            size[ultimateParentOfU] += size[ultimateParentOfV];
        }
    }
}
public class NumberOfIslands2 {

    int[][] dirs = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}};
    List<Integer> ans = new ArrayList<>();

    public List<Integer> solve(int m, int n, int[][] positions){
        boolean[][] visited = new boolean[n][m];
        DisjointSet disjointSet = new DisjointSet(n*m);
        int cnt=0;

        for(int i=0; i<positions.length; i++){
            int row = positions[i][0];
            int col = positions[i][1];

            if(visited[row][col]){
                ans.add(cnt);
                continue;
            }
            cnt++;
            visited[row][col] = true;

            for(int[] dir: dirs){
                int nRow = row + dir[0];
                int nCol = col + dir[1];
                if(nRow < 0 || nCol < 0 || nRow >= n || nCol >= m){
                    continue;
                }
                if(visited[nRow][nCol]){
                    int currNode = row * m + col;
                    int adjNode = nRow * m + nCol;
                    if (disjointSet.findUltimateParent(currNode) !=  disjointSet.findUltimateParent(adjNode)){
                        cnt--;
                        disjointSet.unionBySize(currNode, adjNode);
                    }
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    public static void main(String[] args) {

        int[][] positions = new int[][]{
                {0,0},
                {0,1},
                {1,2},
                {2,1}
        };
        int n=3, m=3;

        NumberOfIslands2 numberOfIslands2 = new NumberOfIslands2();
        List<Integer> ans = numberOfIslands2.solve(m,n,positions);
        for(int i=0; i<ans.size(); i++){
            System.out.println(ans.get(i));
        }
    }
}
