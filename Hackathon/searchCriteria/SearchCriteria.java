package com.lld.Hackathon.searchCriteria;

import com.lld.Hackathon.model.Problem;

import java.util.List;

public interface SearchCriteria {
    public List<Problem> search(List<Problem> problems);
}
