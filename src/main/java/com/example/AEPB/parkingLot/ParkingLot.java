package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

import java.util.HashMap;

public class ParkingLot {
    private final Integer parkingLotNo;
    private final Integer maxSpace;
    private final HashMap<ParkingTicket, Vehicle> tickets = new HashMap<>();

    public ParkingLot(Integer parkingLotNo, Integer maxSpace) {
        if (maxSpace < 1) {
            throw new IllegalArgumentException("parking lot space should large than 0");
        }
        this.parkingLotNo = parkingLotNo;
        this.maxSpace = maxSpace;
    }

    public ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        if (isFull()) {
            throw new ParkingLotFullException("the parking lot is full");
        }

        final ParkingTicket ticket = new ParkingTicket(this.parkingLotNo);
        tickets.put(ticket, vehicle);
        return ticket;
    }

    public Vehicle pickup(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException("ticket can not be null");
        }

        if (!tickets.containsKey(parkingTicket)) {
            throw new InValidParkingTicketException();
        }

        final Vehicle vehicle = tickets.get(parkingTicket);
        tickets.remove(parkingTicket);
        return vehicle;
    }

    public Integer getParkingLotNo() {
        return parkingLotNo;
    }

    public Integer vacantSpaces() {
        return maxSpace - tickets.size();
    }

    public Double calculateVacancyRate() {
        return vacantSpaces() / maxSpace * 1.0;
    }

    public boolean isFull() {
        return vacantSpaces() == 0;
    }

    public boolean isNotFull() {
        return vacantSpaces() > 0;
    }
}
