package com.lld.MiddleWareRouter3;

import sun.text.normalizer.Trie;

import javax.swing.tree.TreeNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MiddleWareRouterImpl implements MiddleWareRouter {
    class TrieNode {
        HashMap<String, TrieNode> children;
        String value;
        boolean isWordEnd;
        public TrieNode() {
            this.children = new HashMap<>();
        }
    }

    TrieNode root = new TrieNode();

    public void add(String urlPath, String value) {
        String[] urlPathArr = urlPath.split("/");
        TrieNode node = root;
        for (int i = 0; i < urlPathArr.length; i++) {
            if (!node.children.containsKey(urlPathArr[i])) {
                node.children.put(urlPathArr[i], new TrieNode());
            }
            node = node.children.get(urlPathArr[i]);
        }
        node.value = value;
        node.isWordEnd = true;
    }

    public void searchPattern(String[] urlComponents, int index, TrieNode node) {
//        if(node == null){
//            return;
//        }
        if (index == urlComponents.length) {
            if (node != null && node.isWordEnd ) {
                System.out.println("Pattern found: "+node.value);
                return;
            }
        }
        String component = urlComponents[index];
        if((component.equals("*"))){ //TODO: Check if this solution works => https://github.com/lavakumarThatisetti/Machine-Coding-Round/blob/master/src/com/lavakumar/middlewarerouter/RouterImpl.java
            // This is the key statement!! -> https://leetcode.com/discuss/interview-question/object-oriented-design/2167390/atlassian-ood-design-a-middelware-router
            // Check the root's children to see if it is the next path after *
            searchPattern(urlComponents, index + 1, node);
            // Goto to each children and check if their children is the next path after *
            for(Map.Entry<String, TrieNode> itr: node.children.entrySet()){
//                System.out.println("Key of the mp =>"+itr.getKey());
                TrieNode childNode = itr.getValue();
//            TrieNode childNode = node.children.get(component);
                 searchPattern(urlComponents, index, childNode);
            }

        } else if ((component.equals("?"))) {
            for(Map.Entry<String, TrieNode> itr: node.children.entrySet()){
                TrieNode childNode = itr.getValue();
                searchPattern(urlComponents, index + 1, childNode);
            }
        } else if((node.children.containsKey(component))){
            node = node.children.get(component);
             searchPattern(urlComponents, index + 1, node);
        }
    }

    public void get(String urlPath) {
        String[] components = urlPath.split("/");
        searchPattern(components, 0, root);
    }
}
