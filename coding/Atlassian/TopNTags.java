package coding.Atlassian.EntryExit;

import java.util.*;

/**
 * - File tag problem - https://leetcode.com/discuss/interview-experience/3350713/Atlassian-or-Sr-SDE-or-Banglore-or-March-2023
 *     - DSA Round:For DSA Interviewer asked about the file tagging problem.I am given files, file size and the tag e.g.<f1, 100>, <f2, 200> <f3, 10>
 *     -
 *     - <tag1, <f1, f2>> , <tag2, f3>
 *     -
 *     - As output result top N tags where to files size is highest.
 *     -
 *     - I shared the optimised approach, could code 1 approach as well.
 *     - Soln in comment - https://leetcode.com/discuss/interview-question/4327785/Hierarchical-collection-of-files
 *
 */
class File {
    String name;
    int size;
    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
class Tag {
    String name;
    List<File1> files;
    public Tag(String name) {
        this.name = name;
        this.files = new ArrayList<>();
    }
    public void addFile(File1 file) {
        files.add(file);
    }
    public int getTotalSize() {
        return files.stream().mapToInt(file -> file.size).sum();
    }
}
public class TopNTags {

    public static List<String> getTopNTags(List<Tag1> tags, int N) {
        PriorityQueue<Tag1> maxHeap = new PriorityQueue<>((tag1, tag2) -> Integer.compare(tag2.getTotalSize(), tag1.getTotalSize()));
        for (Tag1 tag : tags) {
            maxHeap.offer(tag);
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < N && !maxHeap.isEmpty(); i++) {
            result.add(maxHeap.poll().name);
        }
        return result;
    }
    public static void main(String[] args) {
        // Sample data
        File1 f1 = new File1("f1", 100);
        File1 f2 = new File1("f2", 200);
        File1 f3 = new File1("f3", 10);
        Tag1 tag1 = new Tag1("tag1");
        tag1.addFile(f1);
        tag1.addFile(f2);
        Tag1 tag2 = new Tag1("tag2");
        tag2.addFile(f3);
        List<Tag1> tags = Arrays.asList(tag1, tag2);
        // Get top N tags
        List<String> topTags = getTopNTags(tags, 1);
        // Display the result
        System.out.println("Top Tags: " + topTags);
    }
}

//Using TreeMap (more efficient)
//import java.util.*;
//class File {
//    String name;
//    int size;
//    public File(String name, int size) {
//        this.name = name;
//        this.size = size;
//    }
//}
//class Tag {
//    String name;
//    List<File> files;
//    public Tag(String name) {
//        this.name = name;
//        this.files = new ArrayList<>();
//    }
//    public void addFile(File file) {
//        files.add(file);
//    }
//    public int getTotalSize() {
//        return files.stream().mapToInt(file -> file.size).sum();
//    }
//}
//public class TopNTags {
//    public static List<String> getTopNTags(List<Tag> tags, int N) {
//        TreeMap<Integer, List<String>> tagSizeMap = new TreeMap<>(Collections.reverseOrder());
//        for (Tag tag : tags) {
//            int totalSize = tag.getTotalSize();
//            tagSizeMap.computeIfAbsent(totalSize, k -> new ArrayList<>()).add(tag.name);
//        }
//        List<String> result = new ArrayList<>();
//        for (List<String> tagNames : tagSizeMap.values()) {
//            for (String tagName : tagNames) {
//                if (result.size() < N) {
//                    result.add(tagName);
//                } else {
//                    return result;
//                }
//            }
//        }
//        return result;
//    }
//    public static void main(String[] args) {
//        // Sample data
//        File f1 = new File("f1", 100);
//        File f2 = new File("f2", 200);
//        File f3 = new File("f3", 10);
//        Tag tag1 = new Tag("tag1");
//        tag1.addFile(f1);
//        tag1.addFile(f2);
//        Tag tag2 = new Tag("tag2");
//        tag2.addFile(f3);
//        List<Tag> tags = Arrays.asList(tag1, tag2);
//        // Get top N tags
//        List<String> topTags = getTopNTags(tags, 1);
//        // Display the result
//        System.out.println("Top Tags: " + topTags);
//    }
//}
