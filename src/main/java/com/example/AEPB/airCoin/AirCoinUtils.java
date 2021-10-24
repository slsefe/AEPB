package com.example.AEPB.airCoin;

public class AirCoinUtils {
    public static boolean compare(AirCoin airCoinOne, AirCoin airCoinTwo) {
        if (airCoinOne == null || airCoinTwo == null) {
            throw new IllegalArgumentException("Compared AirCoin should not be null.");
        }
        return airCoinOne.getAmount() == airCoinTwo.getAmount();
    }
}
