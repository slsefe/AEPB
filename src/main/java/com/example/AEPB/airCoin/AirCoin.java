package com.example.AEPB.airCoin;

public class AirCoin {
    public static int MIN_AMOUNT = 0;
    public static int MAX_AMOUNT = 1000000000;

    private final int amount;

    public AirCoin(Integer amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalAirCoinAmountException("air coin amount should between " + MIN_AMOUNT + " and " + MAX_AMOUNT + ".");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public Boolean compare(AirCoin airCoin) {
        return this.getAmount() == airCoin.getAmount();
    }


}
