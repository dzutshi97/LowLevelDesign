package com.lld.Hackathon.searchCriteria;

import com.lld.Hackathon.model.Problem;
import com.lld.Hackathon.model.Tag;
import com.lld.Hackathon.searchCriteria.SearchCriteria;

import java.util.List;
import java.util.stream.Collectors;

public class SearchByTag implements SearchCriteria {

    private Tag tag;

    public SearchByTag(Tag tag) {
        this.tag = tag;
    }
    @Override
    public List<Problem> search(List<Problem> problems) {
        boolean res = problems.stream().anyMatch(p -> p.getTag().equals(this.tag));
        if(!res){
            throw new RuntimeException("No problems found for the given tag");
        }
       return problems.stream().filter(p -> p.getTag().equals(this.tag)).collect(Collectors.toList());
    }
}
