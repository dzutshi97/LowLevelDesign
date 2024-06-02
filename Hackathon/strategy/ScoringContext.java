package com.lld.Hackathon.strategy;

import com.lld.Hackathon.model.ScoringCriteria;

public class ScoringContext {

    public ScoringStrategy getInstance(ScoringCriteria criteria){
        switch (criteria){
            case DEFAULT:
                return new ProblemBasedScoring();
        }
        throw new RuntimeException("Provided scoring strategy not yet supported");
    }
}
