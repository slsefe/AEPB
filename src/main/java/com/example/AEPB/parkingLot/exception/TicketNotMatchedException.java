package com.example.AEPB.parkingLot.exception;

public class TicketNotMatchedException extends RuntimeException{
    public TicketNotMatchedException(String message) {
        super(message);
    }
}
