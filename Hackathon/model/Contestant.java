package com.lld.Hackathon.model;

import java.util.List;
import java.util.UUID;

public class Contestant {

    String id;
    String name;
    double score;
    List<Problem> problems;

    public Contestant(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "Contestant{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", problems=" + problems +
                '}';
    }
}
