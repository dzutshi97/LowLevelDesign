package coding.Tries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
// https://leetcode.com/discuss/interview-question/4279643/atlassian-file-system-design/2422840
//implement - mkdir , rm, mv, ls
public class InMemoryFileSystem2 {

    class File{
        boolean isFile;
        HashMap<String, File> children;
        String content;

        public File() {
            this.isFile = false;
            this.children = new HashMap<>();
            this.content = "";
        }
    }
    File root = new File();

    public List<String> ls(String path){
        File node = root;
        List<String> fileNamesListed = new ArrayList<>();
        String[] p = path.split("/");
        for(int i=1; i<= p.length; i++){
            /** Why start from 1?
             * Ans:
             * The path string is split by the delimiter /, resulting in an array of path components. The first element in the array is an empty string because the path starts with /. Thus, starting the loop from 1 skips this empty element and correctly traverses the path components.
             * Example
             * Consider the path /a/b/c:
             * Splitting this path by / results in the array ["", "a", "b", "c"].
             */
            node = node.children.get(p[i]);
        }
        if(node.isFile){
            fileNamesListed.add(p[p.length-1]);
        }
        List<String> result = new ArrayList<>(node.children.keySet());
        Collections.sort(result);
        return result;
    }

    public void addContentToFile(String path, String newContent){
        File node = root;
        String[] p = path.split("/");
        for(int i=1; i<p.length-1; i++){
            node = node.children.get(p[i]);
        }
        if(!node.children.containsKey(p[p.length-1])){
            node.children.put(p[p.length-1], new File());
        }
        File file = node.children.get(p[p.length-1]);
        file.isFile = true;
        file.content = file.content + newContent;
    }

    public String readContentFromFile(String path){
        File node = root;
        String[] p = path.split("/");
        for(int i=1; i<p.length-1; i++){
            node = node.children.get(p[i]);
        }
        File file = node.children.get(p[p.length-1]);
        if(file.isFile){
            return file.content;
        }
        return "";
    }
}
