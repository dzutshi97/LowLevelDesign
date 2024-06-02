package com.lld.UrlRouter2;


import java.util.HashMap;
import java.util.Map;

interface Router {
        public void add(String route, String data);
        public String get(String route);
    }

    class RouterImpl implements Router {

        static class TrieNode {
            Map<String, TrieNode> children;
            String value;
            boolean endOfWord;


            public TrieNode() {
                children = new HashMap<>();
                value = "";
                endOfWord = false;
            }
        }
        static TrieNode root = new TrieNode();

        @Override
        public void add(String route, String data) {
            TrieNode pCrawl = root;
            String[] urlComponents = route.split("/");
            for(String component:urlComponents) {
                if(!pCrawl.children.containsKey(component)) {
                    pCrawl.children.put(component, new TrieNode());
                }
                pCrawl = pCrawl.children.get(component);
            }
            pCrawl.endOfWord = true;
            pCrawl.value = data;
        }

        private String searchTrie(String[] urlComponents, int index, TrieNode node) {
            int size = urlComponents.length;
            if(index == size)
            {
                if(node != null && node.endOfWord) {
                    return node.value;
                }
                else {
                    return null;
                }
            }
            TrieNode pCrawl = node;

            String component = urlComponents[index];
            if("*".equals(component)) {
                String result = null;
                for(Map.Entry<String, TrieNode>entry:pCrawl.children.entrySet()) {
                    TrieNode temp = entry.getValue();
                    result = searchTrie(urlComponents, index+1, temp);
                    if(result != null) {
                        return result;
                    }
                }
                return result;
            }
            else if(pCrawl != null && pCrawl.children != null && pCrawl.children.containsKey(component)) {
                pCrawl = pCrawl.children.get(component);
                String result = searchTrie(urlComponents, index+1, pCrawl);
                return result;
            }
            else
            {
                return null;
            }

        }

        @Override
        public String get(String route) {
            TrieNode pCrawl = root;
            String[] urlComponents = route.split("/");
            String result = searchTrie(urlComponents, 0, root);
            if(result == null) {
                result = "notFound!";
            }
            return result;
        }
    }
    public class driver {
        public static void main(String[] args) {
            Router rm = new RouterImpl();
            rm.add("jira.atlassian.com/testRoute/abc", "fooData1");
//            rm.add("jira.atlassian.com/testRoute/xyz", "fooData2");
            rm.add("jira.atlassian.com/testRoute/", "fooData3");
            rm.add("jira.atlassian.com/testRoute/abc/xyz", "fooData4");
            rm.add("jira.atlassian.com/testRoute/abc/lmn/xyz", "fooData4");
            String v = rm.get("jira.atlassian.com/testRoute/*/xyz");
            String w = rm.get("jira.atlassian.com/testRoute");
            String x = rm.get("jira.atlassian.com/testRoute/abc");
            String y = rm.get("jira.atlassian.com/testRoute/*");
            String z = rm.get("jira.atlassian.com/*/xyz");
            System.out.println(v);
            System.out.println(w);
            System.out.println(x);
            System.out.println(y);
            System.out.println(z);
        }
    }

