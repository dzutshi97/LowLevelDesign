package com.lld.PhonepeWallet.offer.Strategy;

public class CashBackContext {

    public CashBackStrategy getInstance(CashBackType cashBackType){

        switch (cashBackType){
            case EQUAL_BALANCE:
                return new EqualBalanceCashbackStrategy();
            case FIRST_TIME_USER:
                return new FirstTimeUserCashBackStr();
            default:
                throw new RuntimeException("Cashback strategy not supported!");
        }
    }
}
