package com.example.AEPB.parkingLot;

public class ParkingBoy {
    private final static int NUMBER_OF_PARKING_LOT = 10;
    private final ParkingLot[] parkingLots = new ParkingLot[NUMBER_OF_PARKING_LOT];

    public ParkingBoy() {
        for (int i = 0; i < NUMBER_OF_PARKING_LOT; i++) {
            parkingLots[i] = new ParkingLot();
        }
    }

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
