package com.lld.Hackathon.service;

import com.lld.Hackathon.dao.ContestantDao;
import com.lld.Hackathon.dao.ProblemDao;
import com.lld.Hackathon.exception.ContestantNotFoundException;
import com.lld.Hackathon.model.*;
import com.lld.Hackathon.searchCriteria.AndCriteria;
import com.lld.Hackathon.searchCriteria.SearchByLevel;
import com.lld.Hackathon.searchCriteria.SearchByTag;
import com.lld.Hackathon.strategy.*;
import com.lld.Hackathon.searchCriteria.SearchCriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HackathonService {

    //registerUser
    //createProblem
    //filter problems based on strategy
    //solve problem
      //assign score to problems based on scoring strategy
    //get top most 10 liked problems

    ContestantDao cdao;
    ProblemDao pdao;
    public HackathonService() {
        this.cdao = ContestantDao.getInstance();
        this.pdao = ProblemDao.getInstance();
    }

    public String registerContestant(String name){
        Contestant c = new Contestant(name);
        cdao.getContestantDao().put(c.getId(),c);
        return c.getId();
    }

    public String createNewProblem(String description, Level level, Tag tag, String averageTimeTaken, int score){
        Problem problem =new Problem(description,tag,level,averageTimeTaken,score);
        pdao.getProblemDao().put(problem.getId(),problem);
        return problem.getId();
    }

    //print all contents of both the maps here to showcase the id assigned and use these ids in subsequent calls
    public void viewContestants(){
        cdao.getContestantDao().forEach((k,v) -> System.out.println("contestant id = "+k+ " contestant object =>"+v));
    }

    public void viewProblems(){
        pdao.getProblemDao().forEach((k,v) -> System.out.println("Problem id = "+k+ " Problem object =>"+v));
    }

    public List<Problem> filterProblemsByTag(Tag tag){
        SearchCriteria searchByTag = new SearchByTag(tag);
        List<Problem> problems = pdao.getProblemDao().values().stream().collect(Collectors.toList());
        List<Problem> filteredProblems = searchByTag.search(problems);
        System.out.println("List of "+ tag + "problems -> "+ filteredProblems);
        return filteredProblems;
    }

    public List<Problem> filterProblemsByLevel(Level level){
        SearchCriteria searchByLevel = new SearchByLevel(level);
//        SearchCriteria searchByTag = new SearchByTag(tag); AND
//        SearchCriteria andSearchCritera = new AndCriteria(searchByLevel, searchByTag); AND


        List<Problem> problems = pdao.getProblemDao().values().stream().collect(Collectors.toList());
        System.out.println("List of "+ level + " problems -> "+ searchByLevel.search(problems));
//        andSearchCritera.search(problems); AND
        return searchByLevel.search(problems);
    }

    public double solve(String problemId, String contestantId){
        if(!contestantIsValid(contestantId)){
            throw new ContestantNotFoundException();
        }
        ScoringContext context = new ScoringContext();
        ScoringStrategy scoringStrategy = context.getInstance(ScoringCriteria.DEFAULT);
        double score = scoringStrategy.calculateScore(problemId,contestantId);
        System.out.println("New score upon solving the problem = "+ score);
        return score;
    }

    public Contestant getLeader(){
        Contestant contestant = cdao.getContestantDao().values().stream().sorted((c1, c2)-> c2.getScore().compareTo(c1.getScore())).findFirst().orElse(null);
        System.out.println("Leaderboard winner name => "+contestant.getName()+" LeaderBoard winner score => "+ contestant.getScore());
        return contestant;
    }

    public List<Problem> getTopKMostLikedProblems(Tag tag){
        //get top 10 most liked
        HashMap<String, Problem> problems = pdao.getProblemDao();

//        List<Problem> problemList = null;
//        for(Map.Entry<String, Problem> p: problems.entrySet()){
//            assert false;
//            problemList.add(p.getValue());
//        }
        //Use below line for converting in java8 using streams
//        List<Problem> problemList = problems.values().stream().collect(Collectors.toList());
        //OR below line directly
        List<Problem> problemList = new ArrayList<>(problems.values());
        List<Problem> topMostLiked = problemList.stream().filter(l -> l.getTag().equals(tag)).sorted((p1,p2) -> Integer.compare(p2.getLikeCount(), p1.getLikeCount())).limit(10).collect(Collectors.toList());
        System.out.println("topMostLiked problems => "+topMostLiked);
        return topMostLiked;
    }

    //validations
    public boolean contestantIsValid(String contestantId) {
        Contestant contestant = cdao.getContestantDao().get(contestantId);
        if (contestant == null){
            return false;
        }
        return true;
    }

}
