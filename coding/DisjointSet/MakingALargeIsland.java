package coding.DisjointSet;

 class DisjointSet1{
     int[] parent;
     int[] size;
     public DisjointSet1(int V) {
         this.parent = new int[V+1];
         this.size = new int[V+1];
         for(int i=0; i<V; i++){
             size[i] = 1;
             parent[i] = i;
         }
     }
     public void UnionBySize(int u, int v){
         int ultimateParentOfU = findParent(u);
         int ultimateParentOfV = findParent(v);

         if(ultimateParentOfU == ultimateParentOfV){
             return;
         }
         if(size[ultimateParentOfV] > size[ultimateParentOfU]){
             parent[ultimateParentOfU] = ultimateParentOfV;
             size[ultimateParentOfV] += size[ultimateParentOfU];
         }else{
             parent[ultimateParentOfV] = ultimateParentOfU;
             size[ultimateParentOfU] += size[ultimateParentOfV];
         }
     }
     public int findParent(int node){
         if(node == parent[node]){
             return node;
         }
         return parent[node] = findParent(parent[node]);
     }
}
class Util{
    public boolean isValid(int row, int col, int[][] grid){
        if(row <0 || col < 0 || row >= grid.length || col >= grid[0].length-1){ //-1?
            return false;
        }
        return true;
    }
}
public class MakingALargeIsland {
    public static void main(String[] args) {

        int[][] grid=new int[][]{};
        int[][] dirs = new int[][]{};
        int n = grid.length;
        int m = grid[0].length;

        DisjointSet1 disjointSet1 = new DisjointSet1(grid.length*grid[0].length);
        Util util = new Util();
        //step1:- make connected componets.
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length; j++){

                if(grid[i][j] == 1){
                    int cell = i * m + j;
                    for(int[] dir: dirs){
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        int nextCell = newRow * m + newCol;
                        if(util.isValid(i,j,grid) && grid[newRow][newCol] == 1){
                            disjointSet1.UnionBySize(cell, nextCell);
                        }
                    }
                }
            }
        }
        //todo
        // step 2 - for 0 check the connected components size.
        // edge case we dont want to count the same component twice hence use set.
        //



    }
}
