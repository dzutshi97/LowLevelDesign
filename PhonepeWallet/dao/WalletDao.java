package com.lld.PhonepeWallet.dao;

import com.lld.PhonepeWallet.Models.Account;
import com.lld.PhonepeWallet.Models.Wallet;

import java.util.HashMap;

public class WalletDao {
    //user id to account info
    HashMap<String, Wallet> walletStore;

    private static WalletDao instance;

    private WalletDao() {
        this.walletStore =  new HashMap<>();
    }

    public static WalletDao getInstance(){
        if(instance == null){
            instance = new WalletDao();
        }
        return instance;
    }

    public HashMap<String, Wallet> getWalletStore() {
        return walletStore;
    }

    public void setWalletStore(HashMap<String, Wallet> walletStore) {
        this.walletStore = walletStore;
    }
}
