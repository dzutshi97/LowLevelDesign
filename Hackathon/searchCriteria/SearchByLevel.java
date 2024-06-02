package com.lld.Hackathon.searchCriteria;

import com.lld.Hackathon.model.Level;
import com.lld.Hackathon.model.Problem;
import com.lld.Hackathon.searchCriteria.SearchCriteria;

import java.util.*;
import java.util.stream.Collectors;

public class SearchByLevel implements SearchCriteria {
    private Level level;

    public SearchByLevel(Level level) {
        this.level = level;
    }

    @Override
    public List<Problem> search(List<Problem> problems) {
        boolean res = problems.stream().anyMatch(p -> p.getLevel().equals(this.level));
        if(!res){
            throw new RuntimeException("No problem found for the passed level - "+this.level);
        }
//        HashMap<String, Problem> problems = problemdao.getProblemDao();
//        List<Problem> problemList = null;
//        for(Map.Entry<String, Problem> p: problems.entrySet()){
//            assert false;
//            problemList.add(p.getValue());
//        }
//
//        assert false;
        //use below line to smoothly convert
//        List<Problem> problemList = new ArrayList<>(problems.values());
        //filter out only easy problems
        List<Problem> filteredSortedList = problems.stream().filter(l -> l.getLevel().equals(this.level)).collect(Collectors.toList());
        //sort by easy,medium and hard wise
        //Collections.sort(problemList, (p1, p2) -> p1.getLevel().compareTo(p2.getLevel()));
        return filteredSortedList;
    }
}
