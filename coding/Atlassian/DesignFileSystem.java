package coding.Atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface DesignFileSystem{
     void mkdir(String path, int size);
     int getSize(String path);
     void rm(String path);
     List<String> ls(String path);
}

class FileSystem implements DesignFileSystem{

    /**
     *  /a/b/c/xyz.txt
     *
     *  a,N
     *    |
     *    b,N
     *      |
     *      c, N
     *         |
     *         xyz.txt, N
     *
     *
     */
    class Node{
        HashMap<String,Node> children;
//        String nodeName;
        int size;
        Node parent;
        boolean isFile;
        public Node() {
            this.children = new HashMap<>();
        }
    }
    Node root = new Node();

    public void mkdir(String path, int size){
        Node node = root;
        String[] content = path.split("/");

        for(int i=1; i<content.length-1; i++) {
            if (!node.children.containsKey(content[i])) {
                Node newNode = new Node();
                newNode.parent = node;
                node.children.put(content[i], newNode);
            }
            node = node.children.get(content[i]);
        }

        if(!node.children.containsKey(content[content.length-1])){
            Node newNode = new Node();
            newNode.parent = node;
            newNode.isFile = content[content.length-1].contains(".");
            node.children.put(content[content.length-1], newNode);
            if(newNode.isFile && size > 0){
                updateSize(newNode,size);
            }
        }

    }
    public void updateSize(Node node, int size){
        while(node != null){
            node.size += size;
            node = node.parent;
        }
    }

    public int getSize(String path){
        Node node = root;
        String[] components = path.split("/");
        for(int i=1; i<components.length; i++){
            if(!node.children.containsKey(components[i])){
                return 0;
            }
            node = node.children.get(components[i]);
        }
        return node.size;
    }

    public void rm(String path){ //remove a file or a sub/directory
        Node node = root;
        String[] components = path.split("/");
        for(int i=1; i<components.length; i++){
            if(!node.children.containsKey(components[i])) {
                return;
            }
            node = node.children.get(components[i]);
        }
        if(node.isFile){
            updateSize(node, -node.size);
        }
        node.parent.children.remove(components[components.length-1]);
    }

    public List<String> ls(String path){
        Node node = root;
        String[] components = path.split("/");
        for(int i=1; i<components.length; i++){
            if(!node.children.containsKey(components[i])){
                return null;
            }
            node = node.children.get(components[i]);
        }
        List<String> childNodesLS = new ArrayList<>(node.children.keySet());
        return childNodesLS;
    }

    public static void main(String[] args) {

        DesignFileSystem designFileSystem = new FileSystem();
        //add a file
        designFileSystem.mkdir("/a/b/c/xyz.txt", 100);
        System.out.println(designFileSystem.getSize("/a/b/c")); //100
        System.out.println(designFileSystem.getSize("/a/")); //100
        //add a file
        designFileSystem.mkdir("/a/b/c/lmn.txt", 200);
        System.out.println(designFileSystem.getSize("/a/b")); //300
        System.out.println(designFileSystem.getSize("/a/"));  //300

        //list all files/directories
        System.out.println(designFileSystem.ls("/a/b/c/")); //xyz.txt, lmn.txt

        //remove a file
        designFileSystem.rm("/a/b/c/lmn.txt");
        System.out.println(designFileSystem.getSize("/a/"));  //100

        //list all files/directories
        System.out.println(designFileSystem.ls("/a/b/c/")); //xyz.txt

    }
}
