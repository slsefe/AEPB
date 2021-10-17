package com.example.AEPB.parkingLot;

import java.util.UUID;

public class ParkingTicket {
    private final String carPlateNumber;
    private final String ticketNumber;

    public ParkingTicket(String carPlateNumber) {
        this.carPlateNumber = carPlateNumber;
        this.ticketNumber = UUID.randomUUID().toString();
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }
}
