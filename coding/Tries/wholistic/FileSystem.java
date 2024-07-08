package coding.Tries.wholistic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//https://leetcode.com/discuss/interview-question/4279643/atlassian-file-system-design/2422840
public class FileSystem {

    //ls, addContentToFile, readContentFromFile, mv, rm, mkdir, getSize, calcSize
    class Node{
        private boolean isFile;
        private HashMap<String,Node> children;
        private int size;
        private Node parent; //maintain backlink to parent for quick traversals up the hierarchy
        private String content;

        public Node() {
            this.isFile = false;
            this.children = new HashMap<>();
            this.size = 0;
            this.parent = null;
            this.content = "";
        }
    }
    Node root = new Node();

    //mkdir - /a/b/abc.txt (abc.txt -> size=10KB)
    public void mkdir(String path, int size){
        Node node = root;
        String[] p = path.split("/");
        for(int i=1; i<p.length-1;i++){
            if(!node.children.containsKey(p[i])){
                Node newNode = new Node();
                newNode.parent = node;
                node.children.put(p[i],newNode);
            }
            node = node.children.get(p[i]);
        }

        if(!node.children.containsKey(p[p.length-1])){
            Node newNode = new Node();
            newNode.isFile = p[p.length-1].contains("."); //this is a FILE and not a directory
            newNode.parent = node;
            newNode.size = size;
            node.children.put(p[p.length-1],newNode);
            if(newNode.isFile){
                updateParentSize(node,size);
            }
        }
    }

    public void updateParentSize(Node node, int size){
        if(node == null){
            return;
        }
        int newSize = node.size + size;
        node.size = newSize;
        updateParentSize(node.parent, newSize);
    }

    public int getSize(String path){
        String[] p = path.split("/");
        Node node = root;
        if(!node.children.containsKey(p[p.length - 1])){
            return 0;
        }
        return node.children.get(p[p.length - 1]).size;
    }

    public List<String> ls(String path){
        String[] p = path.split("/");
        Node node = root;
        for(int i=1; i<= p.length; i++){
            if(!node.children.containsKey(p[i])){
                return null;
            }
            node = node.children.get(p[i]);
        }
        if(node.isFile){
            List<String> fileNamesListed = new ArrayList<>();
            fileNamesListed.add(p[p.length-1]);
            return fileNamesListed;
        }
        List<String> childNodesLS = new ArrayList<>(node.children.keySet());
        return childNodesLS;
    }

    public void addContentToFile(String path, String content){
        String[] p = path.split("/");
        Node node = root;
        for(int i=1; i< p.length; i++){
            if(!node.children.containsKey(p[i])){
                return;
            }
            node = node.children.get(p[i]);
        }
        if(!node.children.containsKey(p[p.length-1])){
            return;
        }
        if(!node.isFile){
            return;
        }
        node.content = node.content + content;
    }

    public String readContentFromFile(String path){
        String[] p = path.split("/");
        Node node = root;
        for(int i=1; i< p.length; i++){
            if(!node.children.containsKey(p[i])){
                return null;
            }
            node = node.children.get(p[i]);
        }
        if(!node.children.containsKey(p[p.length-1])){
            return null;
        }
        if(!node.isFile){
            return null;
        }
        return node.content;
    }

    public void mv(){

    }

    public void rm(){

    }
}
