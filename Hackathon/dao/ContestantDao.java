package com.lld.Hackathon.dao;
import com.lld.Hackathon.model.Contestant;
import java.util.HashMap;

public class ContestantDao {

    private HashMap<String,Contestant> contestantDao;
    private static volatile ContestantDao instance; //todo: why static?

    private ContestantDao() { //NOTE: private keyword here!!!
        this.contestantDao = new HashMap<>();
    } //NOTE: private
    public static ContestantDao getInstance(){
        if(instance == null){
            synchronized (ContestantDao.class){
                if(instance == null) {
                    instance = new ContestantDao();
                }
            }
        }
        return instance;
    }
    public HashMap<String, Contestant> getContestantDao() {
        return contestantDao;
    }
    public void setContestantDao(HashMap<String, Contestant> contestantDao) {
        this.contestantDao = contestantDao;
    }
}
