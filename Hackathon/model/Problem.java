package com.lld.Hackathon.model;

import java.util.UUID;

public class Problem {

    String id;
    String description;
    Tag tag;
    Level level; //difficulty level
    String avgTimeTaken;
    int likeCount;
    double score;

    public Problem(String description, Tag tag, Level level, String avgTimeTaken, int score) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.tag = tag;
        this.level = level;
        this.avgTimeTaken = avgTimeTaken;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getAvgTimeTaken() {
        return avgTimeTaken;
    }

    public void setAvgTimeTaken(String avgTimeTaken) {
        this.avgTimeTaken = avgTimeTaken;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", tag=" + tag +
                ", level=" + level +
                ", avgTimeTaken='" + avgTimeTaken + '\'' +
                ", likeCount=" + likeCount +
                ", score=" + score +
                '}';
    }
}
