package com.example.AEPB.parkingLot;

public class ParkingBoy {
    private final ParkingLot[] parkingLots = new ParkingLot[10];

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket park(Vehicle vehicle) {
        return parkingLots[0].park(vehicle, 1);
    }

    public Vehicle pickUp(ParkingTicket parkingTicket) {
        return null;
    }
}
