package com.example.AEPB.parkingLot;

import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {
    private final String ticketNumber;

    public ParkingTicket() {
        this.ticketNumber = UUID.randomUUID().toString();
    }

    public String getCarPlateNumber() {
        return ticketNumber;
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
        return ticketNumber.equals(that.ticketNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNumber);
    }
}
