package com.lld.Hackathon.strategy;

import com.lld.Hackathon.dao.ContestantDao;
import com.lld.Hackathon.dao.ProblemDao;
import com.lld.Hackathon.model.Contestant;
import com.lld.Hackathon.model.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProblemBasedScoring implements ScoringStrategy{
    ProblemDao problemDao = ProblemDao.getInstance();
    ContestantDao contestantDao = ContestantDao.getInstance();

    @Override
    public double calculateScore(String problemId, String contestantId) {
        Contestant contestant = contestantDao.getContestantDao().get(contestantId);
        //if contestant has already solved the problem, do nothing
        //Optional<Problem> problemExists = contestantDao.getContestantDao().get(contestantId).getProblems().stream().filter(p -> p.getId().equals(problemId)).findAny();
        //isPresent()
        //OR
        //todo: what is method referncing? And since contestant.getProblems() was null, to avoid NPE we use Optional as below & instruct it to return empty list instead.
        boolean problemExists = Optional.ofNullable(contestant.getProblems()).orElseGet(Collections::emptyList).stream().anyMatch(p -> p.getId().equals(problemId));
        if (problemExists){
            return 0.0;
        }
        //if not, add problem to contestant's solved lists
        if(Optional.ofNullable(contestant.getProblems()).isPresent()){
            contestant.getProblems().add(problemDao.getProblemDao().get(problemId));
        }else{
            List<Problem> ps = new ArrayList<>();
            ps.add(problemDao.getProblemDao().get(problemId));
            contestant.setProblems(ps);
        }
        //update max score variable of the candidate
        Double candidateCurrentScore = contestant.getScore();
        double candidateFinalScore = candidateCurrentScore + problemDao.getProblemDao().get(problemId).getScore();
        contestant.setScore(candidateFinalScore);
        return candidateFinalScore;
    }


}
