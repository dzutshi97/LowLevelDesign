package coding.Tries;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

// Implement - mkdir, rm, mv, ls
public class InMemoryFileSystem {

    class File {
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

    public List<String> ls(String path) {
        File node = root;
        List<String> fileNamesListed = new ArrayList<>();
        String[] p = path.split("/");
        for (int i = 1; i < p.length; i++) {
            /** Why start from 1?
             * Ans:
             * The path string is split by the delimiter /, resulting in an array of path components. The first element in the array is an empty string because the path starts with /. Thus, starting the loop from 1 skips this empty element and correctly traverses the path components.
             * Example
             * Consider the path /a/b/c:
             * Splitting this path by / results in the array ["", "a", "b", "c"].
             */
            if (!node.children.containsKey(p[i])) {
                return fileNamesListed; // Path does not exist
            }
            node = node.children.get(p[i]);
        }
        if (node.isFile) {
            fileNamesListed.add(p[p.length - 1]);
            return fileNamesListed;
        }
        List<String> result = new ArrayList<>(node.children.keySet());
        Collections.sort(result);
        return result;
    }

    public void addContentToFile(String path, String newContent) {
        File node = root;
        String[] p = path.split("/");
        for (int i = 1; i < p.length - 1; i++) {
            node.children.putIfAbsent(p[i], new File());
            node = node.children.get(p[i]);
        }
        node.children.putIfAbsent(p[p.length - 1], new File());
        File file = node.children.get(p[p.length - 1]);
        file.isFile = true;
        file.content = file.content + newContent;
    }

    public String readContentFromFile(String path) {
        File node = root;
        String[] p = path.split("/");
        for (int i = 1; i < p.length; i++) {
            if (!node.children.containsKey(p[i])) {
                return ""; // Path does not exist
            }
            node = node.children.get(p[i]);
        }
        return node.isFile ? node.content : "";
    }

    // Method to create a directory
    public void mkdir(String path) {
        File node = root;
        String[] p = path.split("/");
        for (int i = 1; i < p.length; i++) {
            node.children.putIfAbsent(p[i], new File());
            node = node.children.get(p[i]);
        }
    }

    // Method to move a file or directory
    public void mv(String sourcePath, String destPath) {
        File node = root;
        String[] src = sourcePath.split("/");
        for (int i = 1; i < src.length - 1; i++) {
            node = node.children.get(src[i]);
        }
        File fileToMove = node.children.remove(src[src.length - 1]);

        node = root;
        String[] dest = destPath.split("/");
        for (int i = 1; i < dest.length - 1; i++) {
            node.children.putIfAbsent(dest[i], new File());
            node = node.children.get(dest[i]);
        }
        node.children.put(dest[dest.length - 1], fileToMove);
    }

    // Method to remove a file or directory
    public void rm(String path) {
        File node = root;
        String[] p = path.split("/");
        for (int i = 1; i < p.length - 1; i++) {
            node = node.children.get(p[i]);
        }
        node.children.remove(p[p.length - 1]);
    }
}
