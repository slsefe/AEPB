package com.example.AEPB.airCoin;

public class IllegalAirCoinAmountException extends RuntimeException{
    public IllegalAirCoinAmountException(String message) {
        super(message);
    }
}
