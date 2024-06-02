package com.lld.PhonepeWallet.offer.Strategy;

public class FirstTimeUserCashBackStr implements CashBackStrategy{
    @Override
    public double calculateCashBack(double amount) {
        return (amount * 10)/100;    }
}
