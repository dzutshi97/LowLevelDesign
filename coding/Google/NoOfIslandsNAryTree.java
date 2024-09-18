package coding.Google;

import java.util.ArrayList;
import java.util.List;

class TreeNode1{

    List<TreeNode1> children;
    int val;
    public TreeNode1(int val) {
        this.val = val;
        this.children = null;
    }
}

public class NoOfIslandsNAryTree {

    public int countIslands(TreeNode1 node){
        if(node == null){ //|| node.children == null
            return 0;
        }

        int islands = 0;
        if(node.val == 1){
            islands++;
            dfs(node); //mark all connected i.e child nodes with val 1 as visited (0)
        }
        // Recursively check each child
        if (node.children != null) {
            for (TreeNode1 child : node.children) {
                islands += countIslands(child);
            }
        }
        return islands;
    }

    public void dfs(TreeNode1 node){
//        if(node == null || node.val ==0 || node.children == null){
        if(node == null || node.val == 0){
            return;
        }

        //mark current node as visited
        node.val = 0;

        // Recur for all children
        if(node.children != null)
        {
            for(TreeNode1 child: node.children) {
                if (child.val == 1) {
                    child.val = 0;
                    dfs(child);
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}
