package com.example.AEPB.parkingLot.exception;

public class InValidParkingTicketException extends RuntimeException{

    public InValidParkingTicketException(String message) {
        super(message);
    }
}