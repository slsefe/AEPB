package com.example.AEPB;

import java.util.Objects;

public class AirCoin {
    public static int MIN_AMOUNT = 0;
    public static int MAX_AMOUNT = 1000000000;

    private int amount;

    public AirCoin() {
    }

    public AirCoin(Integer amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalAirCoinAmountException("air coin amount should between " + MIN_AMOUNT + " and " + MAX_AMOUNT + ".");
        }
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AirCoin)) {
            return false;
        }
        AirCoin airCoin = (AirCoin) o;
        return getAmount() == airCoin.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount());
    }
}
