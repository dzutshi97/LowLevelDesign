package com.lld.PhonepeWallet.offer.Strategy;

public class EqualBalanceCashbackStrategy implements CashBackStrategy{
    @Override
    public double calculateCashBack(double amount) {
        return (amount * 5)/100;
    }
}
