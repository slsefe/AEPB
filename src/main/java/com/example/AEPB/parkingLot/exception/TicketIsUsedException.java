package com.example.AEPB.parkingLot.exception;

public class TicketIsUsedException extends RuntimeException{
    public TicketIsUsedException(String message) {
        super(message);
    }
}
