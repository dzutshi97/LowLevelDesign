package com.lld.Hackathon.searchCriteria;

import com.lld.Hackathon.model.Problem;

import java.util.List;

public class AndCriteria implements SearchCriteria {
    private SearchCriteria criteria1;
    private SearchCriteria criteria2;

    public AndCriteria(SearchCriteria criteria1, SearchCriteria criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public List<Problem> search(List<Problem> problems) {
        List<Problem> filteredByCriteria1 = criteria1.search(problems);
        return criteria2.search(filteredByCriteria1);
    }
}