package com.lld.Hackathon.strategy;

import com.lld.Hackathon.model.Contestant;
import com.lld.Hackathon.model.Problem;

import java.util.List;

public interface ScoringStrategy {
    public double calculateScore(String problemId, String contestantId);
}
