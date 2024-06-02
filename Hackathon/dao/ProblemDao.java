package com.lld.Hackathon.dao;

import com.lld.Hackathon.model.Contestant;
import com.lld.Hackathon.model.Problem;

import java.util.HashMap;

public class ProblemDao {

    private HashMap<String, Problem> problemDao;
    private static volatile ProblemDao instance; //todo: why static & volatile?

    private ProblemDao() {//todo: NOTE: private keyword here!!!
        this.problemDao = new HashMap<>();
    }

    //todo: make this thread safe. DONE
    public static ProblemDao getInstance(){
        if(instance == null){
            synchronized (ProblemDao.class){
                if(instance == null) {
                    instance = new ProblemDao();
                }
            }
        }
        return instance;
    }

    public HashMap<String, Problem> getProblemDao() {
        return problemDao;
    }

    public void setProblemDao(HashMap<String, Problem> problemDao) {
        this.problemDao = problemDao;
    }
}
