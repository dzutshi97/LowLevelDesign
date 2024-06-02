package com.lld.MiddleWareRouter3;

import java.util.HashMap;
import java.util.HashSet;


interface  MiddleWareRouter{
    public void add(String urlPath, String value);
    public void get(String urlPath);

}

public class Main {
    public static void main(String[] args) {
        MiddleWareRouter mwr = new MiddleWareRouterImpl();
//        mwr.add("jira.atlassian.com/testRoute/abc","1");
//        mwr.add("jira.atlassian.com/testRoute/xyz","2");
        mwr.add("jira.atlassian.com/lmn/cot","3");
//        mwr.get("jira.atlassian.com/testRoute/abc");
//       mwr.get("jira.atlassian.com/testRoute/xyz");
//        mwr.get("jira.atlassian.com/*/xyz");
//        mwr.get("jira.atlassian.com/*/abc");
//        mwr.get("jira.atlassian.com/testRoute/lmn/*"); //todo: this test case fails
//         mwr.get("jira.atlassian.com/*/cot");
         mwr.get("jira.atlassian.com/?/cot");
//        System.out.println(v);
//        System.out.println(w);
//        System.out.println(x);
//        System.out.println(y);
//        System.out.println(z);
    }
}
