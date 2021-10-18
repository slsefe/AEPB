package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

public class ParkingBoy {
    private final static int NUMBER_OF_PARKING_LOT = 10;
    private final ParkingLot[] parkingLots = new ParkingLot[NUMBER_OF_PARKING_LOT];

    public ParkingBoy() {
        for (int i = 0; i < NUMBER_OF_PARKING_LOT; i++) {
            parkingLots[i] = new ParkingLot();
        }
    }

    public ParkingTicket park(Vehicle vehicle) {
        for (int i = 0; i < NUMBER_OF_PARKING_LOT; i++) {
            if (!parkingLots[i].isFull()) {
                int parkingLotNumber = i + 1;
                return parkingLots[i].park(vehicle, parkingLotNumber);
            }
        }
        throw new ParkingLotFullException("All parking lots are full");
    }

    public Vehicle pickUp(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException("parking ticket is null");
        }
        final int parkingLotNumber = parkingTicket.getParkingLotNumber();
        final ParkingLot parkingLot = parkingLots[parkingLotNumber - 1];
        return parkingLot.pickUp(parkingTicket);
    }
}
