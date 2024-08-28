package coding.Atlassian;
import java.util.HashMap;

public class UrlRouter {

    public static void main(String[] args) {

        Route route = new Route();
        Route.Trie trie = route.new Trie(); // Create an instance of the Trie class

        trie.addToTrie("jira.atlassian.com/testRoute/abc", "fooData1");
        trie.addToTrie("jira.atlassian.com/testRoute/xyz", "fooData2");
        trie.addToTrie("jira.atlassian.com/testRoute/", "fooData3");
        trie.addToTrie("jira.atlassian.com/testRoute/abc/xyz/lmn", "fooData4");

        System.out.println(trie.get("jira.atlassian.com/testRoute/abc"));
        System.out.println(trie.get("jira.atlassian.com/testRoute/xyz"));
        System.out.println(trie.get("jira.atlassian.com/*/xyz"));
        System.out.println(trie.get("jira.atlassian.com/testRoute/abc/*/lmn"));

    }
}
 class Route{

    public class TrieNode{

        HashMap<String, TrieNode> children;
        boolean isWordEnd;
        String value;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWordEnd = false;
            this.value = null;
        }
    }
    TrieNode root = new TrieNode();

    class Trie{

        public void addToTrie(String url, String word){
            TrieNode node = root;
            String[] component = url.split("/");
            for(String c: component){
                if(!node.children.containsKey(c)){
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWordEnd = true;
            node.value = word;
        }

        public String get(String url){
            String[] component = url.split("/");
            return getWord(0,root,component);
        }

        public String getWord(int index, TrieNode node, String[] component){

            if(index == component.length){
                if(node!= null && node.isWordEnd){
                    return node.value;
                }
            }

            if(component[index].equals("*")){
                for(TrieNode childNode: node.children.values()){
                    String result = getWord(index+1, childNode, component);
                    if(result != null) { //note
                        return result;
                    }
                }

            } else if (node!= null && node.children.containsKey(component[index])){
                TrieNode childNode = node.children.get(component[index]);
                String result = getWord(index+1, childNode, component);
                if(result != null){
                    return result;
                }
            }
            return null;
        }
    }
}
