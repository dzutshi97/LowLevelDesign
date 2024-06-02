package com.lld.Hackathon;

import com.lld.Hackathon.model.Level;
import com.lld.Hackathon.model.Tag;
import com.lld.Hackathon.service.HackathonService;

public class Main {

    public static void main(String[] args) {

        HackathonService service = new HackathonService();
        //register user
        String c1 = service.registerContestant("Deeps");
        String c2 = service.registerContestant("Shady");
        //view users created
        service.viewContestants();

        //create problems
        String p1 = service.createNewProblem("Koko eating bananas", Level.MEDIUM, Tag.ARRAYS,"5HRs",20);
        String p2 = service.createNewProblem("Jump Game 3", Level.HARD, Tag.DFS,"10HRs",50);
        String p3 = service.createNewProblem("2 sum problem", Level.EASY, Tag.ARRAYS,"1HR",10);
        String p4 = service.createNewProblem("BFS", Level.EASY, Tag.BFS,"1HR",10);
        String p5 = service.createNewProblem("Sort an array", Level.EASY, Tag.ARRAYS,"1HR",10);
        //view priblmes created
        service.viewProblems();

        //filter problems by Tag
        service.filterProblemsByTag(Tag.ARRAYS);

        //filter problem by level
        service.filterProblemsByLevel(Level.EASY);

        //solve problems
        service.solve(p1,c1);
        service.solve(p2,c1);
        service.solve(p3,c2);

        //get contestant with max score - leader
        service.getLeader();

    }
}
