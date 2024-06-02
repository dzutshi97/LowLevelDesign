package coding.DisjointSet;
import java.util.HashSet;

//Q: 323. Number of Connected Components in an Undirected Graph
class DisjointSet{
    int[] parent;
    int[] size;

    public DisjointSet(int V) {
        this.parent = new int[V+1];
        this.size = new int[V+1];
        for(int i=0; i<=V;i++){
            parent[i] = i;
            size[i] = 1;
        }
    }
    public void unionBySize(int u, int v){
        int ultimateParentOfU = findParent(u);
        int ultimateParentOfV = findParent(v);

        if( ultimateParentOfU == ultimateParentOfV){
            return;
        }

        if(size[ultimateParentOfU] < size[ultimateParentOfV]){
            parent[ultimateParentOfU] = parent[ultimateParentOfV];
            size[ultimateParentOfV] += size[ultimateParentOfU];
        }else{
            parent[ultimateParentOfV] = parent[ultimateParentOfU];
            size[ultimateParentOfU] += size[ultimateParentOfV];
        }

    }
    public int findParent(int node){
        if(node == parent[node]){
            return node;
        }
        return parent[node] = findParent(parent[node]); //understand / rev
    }
}
class Solution{

    HashSet<Integer> set = new HashSet();
    public int solve(int n, int[][] edges){
        DisjointSet disjointSet = new DisjointSet(n);
        for(int[] edge: edges){
            disjointSet.unionBySize(edge[0],edge[1]);
            disjointSet.unionBySize(edge[1],edge[0]);
        }
        for(int i=0; i<n; i++){
            set.add(disjointSet.findParent(i));
        }
        return set.size();
    }

}
 class ConnectedCompInGraph {

     public static void main(String[] args) {
         int n=5;
         int[][] edges = new int[][]{{0,1}, {1,2}, {3,4}};
         Solution solution = new Solution();
         int res = solution.solve(n,edges);
         System.out.println(res);
     }

}
