package com.example.AEPB;

public class IllegalAirCoinAmountException extends RuntimeException{
    public IllegalAirCoinAmountException(String message) {
        super(message);
    }
}
