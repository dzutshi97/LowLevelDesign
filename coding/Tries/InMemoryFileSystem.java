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
    File root = new File(); //note

    /**
     * 2. ls(): Traverses Until the Last Component
     * Purpose: In ls(), the goal is to list the contents at the specified path. This path could point to either a file or a directory.
     *
     * If the path points to a file, ls() should return the file name itself.
     * If the path points to a directory, it should return the contents of that directory.
     * Why Last Component?
     *
     * In ls(), we need to handle both files and directories. Therefore, we must traverse the entire path, including the last component.
     * If the last component is a file, ls() returns that file name.
     * If the last component is a directory, it returns the contents of that directory.
     *
     * Example:
     * For the path /a/b/c/file.txt, ls() should traverse all the way to file.txt and return ["file.txt"] because it's a file.
     * For the path /a/b/c/, ls() should traverse to the directory /c and return the sorted contents of that directory.
     * @param path
     * @return
     */
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

    /**
     * 1. addContentToFile(): Traverses Until the Second-to-Last Component
     * Purpose: In addContentToFile(), the goal is to add or update the content of a file at the specified path. To do this, the method needs to:
     *
     * Traverse through the directory components up to the parent directory of the file.
     * Then, handle the final component as the file itself.
     * Why Second-to-Last?
     *
     * The loop traverses up to the second-to-last component because the last component in the path represents the file, not a directory. We don't want to treat the file as a directory during the traversal.
     * Once we've reached the parent directory, we can either find the existing file in children or create a new File object for the file if it doesn't exist.
     * Example:
     *
     * For the path /a/b/c/file.txt, the traversal should stop at /a/b/c (the parent directory), so that the file file.txt can be created or updated.
     * @param path
     * @param newContent
     */
    public void addContentToFile(String path, String newContent) {
        File node = root;
        String[] p = path.split("/");

        // Traverse the directory structure (up to the second-to-last component)
        for (int i = 1; i < p.length - 1; i++) {
            node.children.putIfAbsent(p[i], new File());
            node = node.children.get(p[i]);
        }

        // Ensure the final component is a file
        node.children.putIfAbsent(p[p.length - 1], new File());
        File file = node.children.get(p[p.length - 1]);
        file.isFile = true;

        // Append content (use StringBuilder for efficiency)
        file.content = new StringBuilder(file.content).append(newContent).toString();
    }

    public String readContentFromFile(String path) {
        File node = root;
        String[] p = path.split("/");
        for (int i = 1; i < p.length; i++) {
            if (!node.children.containsKey(p[i])) {
                throw new IllegalArgumentException("Invalid path: " + p[i]);
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

        // Traverse to the source path's parent node
        String[] src = sourcePath.split("/");
        for (int i = 1; i < src.length - 1; i++) {
            if (!node.children.containsKey(src[i])) {
                throw new IllegalArgumentException("Source path does not exist.");
            }
            node = node.children.get(src[i]);
        }

        // Get and remove the file or directory from its original location
        File fileToMove = node.children.remove(src[src.length - 1]);
        if (fileToMove == null) {
            throw new IllegalArgumentException("File or directory to move does not exist.");
        }

        // Prevent moving to the same location
        if (sourcePath.equals(destPath)) {
            throw new IllegalArgumentException("Source and destination paths are the same.");
        }

        // Traverse to the destination path's parent node
        node = root;
        String[] dest = destPath.split("/");
        for (int i = 1; i < dest.length - 1; i++) {
            node.children.putIfAbsent(dest[i], new File());
            node = node.children.get(dest[i]);
        }

        // Prevent overwriting an existing file or directory
        if (node.children.containsKey(dest[dest.length - 1])) {
            throw new IllegalArgumentException("Destination file or directory already exists.");
        }

        // Place the file or directory at the destination path
        node.children.put(dest[dest.length - 1], fileToMove);

    }

    // Method to remove a file or directory
    public void rm(String path) {
        File node = root;
        String[] p = path.split("/");

        // Traverse the path to the parent of the target file/directory
        for (int i = 1; i < p.length - 1; i++) {
            if (!node.children.containsKey(p[i])) {
                throw new IllegalArgumentException("Invalid path: " + p[i]);
            }
            node = node.children.get(p[i]);
        }

        // Check if the file or directory exists before removing to avoid NPE
        if (!node.children.containsKey(p[p.length - 1])) {
            throw new IllegalArgumentException("File or directory not found: " + p[p.length - 1]);
        }

        // Optionally, check if it is a directory and ensure it is empty before deleting
        File toRemove = node.children.get(p[p.length - 1]);
        if (!toRemove.isFile && !toRemove.children.isEmpty()) {
            throw new IllegalArgumentException("Cannot remove non-empty directory: " + p[p.length - 1]);
        }

        node.children.remove(p[p.length - 1]);
    }

    public static void main(String[] args) {

        InMemoryFileSystem fs = new InMemoryFileSystem();

        // Test case 1: ls() on root directory
        System.out.println("Test case 1: ls() on root directory");
        System.out.println(fs.ls("/"));  // Expected: []

        // Test case 2: mkdir() to create a directory /a/b/c
        System.out.println("\nTest case 2: mkdir /a/b/c");
        fs.mkdir("/a/b/c");
        System.out.println(fs.ls("/a"));  // Expected: [b]

        // Test case 3: addContentToFile() to create file /a/b/c/file.txt and add content
        System.out.println("\nTest case 3: addContentToFile /a/b/c/file.txt");
        fs.addContentToFile("/a/b/c/file.txt", "Hello, World!");
        System.out.println(fs.ls("/a/b/c"));  // Expected: [file.txt]

        // Test case 4: add more content to the existing file
        System.out.println("\nTest case 4: addContentToFile (append content) /a/b/c/file.txt");
        fs.addContentToFile("/a/b/c/file.txt", " Appended content.");
        System.out.println(fs.ls("/a/b/c"));  // Expected: [file.txt]

        // Test case 5: mv() move file /a/b/c/file.txt to /a/file.txt
        System.out.println("\nTest case 5: mv /a/b/c/file.txt to /a/file.txt");
        fs.mv("/a/b/c/file.txt", "/a/file.txt");
        System.out.println(fs.ls("/a"));  // Expected: [b, file.txt]
        System.out.println(fs.ls("/a/b/c"));  // Expected: []

        // Test case 6: rm() to remove file /a/file.txt
        System.out.println("\nTest case 6: rm /a/file.txt");
        fs.rm("/a/file.txt");
        System.out.println(fs.ls("/a"));  // Expected: [b]

        // Test case 7: mkdir() to create a nested directory and ls() on the newly created directory
        System.out.println("\nTest case 7: mkdir /a/x/y");
        fs.mkdir("/a/x/y");
        System.out.println(fs.ls("/a"));  // Expected: [b, x]

        // Test case 8: Create a file and remove content from it (new method - removeContentFromFile)
        System.out.println("\nTest case 8: Create and remove content from a file");
        fs.addContentToFile("/a/x/y/test.txt", "Some content here.");
        System.out.println("Content before removal: " + fs.ls("/a/x/y"));  // Expected: [test.txt]
//        fs.removeContentFromFile("/a/x/y/test.txt");
//        System.out.println("Content after removal: " + fs.ls("/a/x/y"));  // Expected: []

        // Test case 9: ls() on non-existing path
        System.out.println("\nTest case 9: ls() on non-existing path");
        System.out.println(fs.ls("/non_existing_path"));  // Expected: []

        // Test case 10: rm() on non-existing file or directory
        System.out.println("\nTest case 10: rm() on non-existing file");
        fs.rm("/a/b/non_existing_file");  // Expected: nothing happens, no error

        // Test case 11: mv() to move non-existing file (should handle properly)
        System.out.println("\nTest case 11: mv non-existing file");
        try {
            fs.mv("/a/b/non_existing_file", "/a/moved_file");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception for mv on non-existing file: " + e.getMessage());
        }
    }

}
