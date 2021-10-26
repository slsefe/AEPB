package com.example.AEPB.parkingLot;

import java.util.Objects;
import java.util.UUID;

public class ParkingTicket {

    private final String parkingTicketNo;

    private final Integer parkingLotNo;

    public ParkingTicket(int parkingLotNo) {
        this.parkingTicketNo = UUID.randomUUID().toString();
        this.parkingLotNo = parkingLotNo;
    }

    public int getParkingLotNo() {
        return parkingLotNo;
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
        return Objects.equals(parkingTicketNo, that.parkingTicketNo)
                && Objects.equals(getParkingLotNo(), that.getParkingLotNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingTicketNo, getParkingLotNo());
    }
}
