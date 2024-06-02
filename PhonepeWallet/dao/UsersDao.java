package com.lld.PhonepeWallet.dao;

import com.lld.PhonepeWallet.Models.User;

import java.util.HashMap;

public class UsersDao {

    HashMap<String, User> usersStore;
    private static UsersDao instance;

    private UsersDao() {
        this.usersStore = new HashMap<>();
    }

    public static UsersDao getInstance(){
        if(instance == null){
            instance = new UsersDao();
        }
        return instance;
    }

    public HashMap<String, User> getUsersStore() {
        return usersStore;
    }

    public void setUsersStore(HashMap<String, User> usersStore) {
        this.usersStore = usersStore;
    }
}
