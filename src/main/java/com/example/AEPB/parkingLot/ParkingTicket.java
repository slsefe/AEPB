package com.example.AEPB.parkingLot;

import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {
    private static final int MIN_NUMBER_OF_PARKING_LOT = 1;
    private static final int MAX_NUMBER_OF_PARKING_LOT = 10;

    private final String ticketNumber;
    private int parkingLotNumber = 1;

    public ParkingTicket() {
        this.ticketNumber = UUID.randomUUID().toString();
    }

    public ParkingTicket(int parkingLotNumber) {
        if (parkingLotNumber < MIN_NUMBER_OF_PARKING_LOT || parkingLotNumber > MAX_NUMBER_OF_PARKING_LOT) {
            throw new IllegalArgumentException("parking lot number should between " + MIN_NUMBER_OF_PARKING_LOT + " and " + MAX_NUMBER_OF_PARKING_LOT);
        }
        this.ticketNumber = UUID.randomUUID().toString();
        this.parkingLotNumber = parkingLotNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public int getParkingLotNumber() {
        return parkingLotNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParkingTicket)) {
            return false;
        }
        ParkingTicket that = (ParkingTicket) o;
        return parkingLotNumber == that.parkingLotNumber && ticketNumber.equals(that.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNumber, parkingLotNumber);
    }
}
