package coding.Atlassian.EntryExit;


//Using TreeMap (more efficient)
import java.util.*;
class File1 {
    String name;
    int size;
    public File1(String name, int size) {
        this.name = name;
        this.size = size;
    }
}
class Tag1 {
    String name;
    List<File1> files;
    public Tag1(String name) {
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
public class TopNTagsEfficient {
    public static List<String> getTopNTags(List<Tag1> tags, int N) {
        TreeMap<Integer, List<String>> tagSizeMap = new TreeMap<>(Collections.reverseOrder());
        for (Tag1 tag : tags) {
            int totalSize = tag.getTotalSize();
            tagSizeMap.computeIfAbsent(totalSize, k -> new ArrayList<>()).add(tag.name);
        }
        List<String> result = new ArrayList<>();
        for (List<String> tagNames : tagSizeMap.values()) {
            for (String tagName : tagNames) {
                if (result.size() < N) {
                    result.add(tagName);
                } else {
                    return result;
                }
            }
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
