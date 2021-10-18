package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.VehicleExistingException;

import java.util.HashMap;

public class ParkingLot {

    private static final int MAX_SPACE = 50;
    private final HashMap<ParkingTicket, Vehicle> tickets;

    public ParkingLot() {
        this.tickets = new HashMap<>();
    }

    public ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        if (tickets.size() == MAX_SPACE) {
            throw new ParkingLotFullException("parking lot is full");
        }

        if (tickets.containsValue(vehicle)) {
            throw new VehicleExistingException("vehicle is already in parking lot");
        }

        final ParkingTicket ticket = new ParkingTicket();
        tickets.put(ticket, vehicle);
        return ticket;
    }

    public ParkingTicket park(Vehicle vehicle, int parkingLotNumber) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        if (tickets.size() == MAX_SPACE) {
            throw new ParkingLotFullException("parking lot is full");
        }

        if (tickets.containsValue(vehicle)) {
            throw new VehicleExistingException("vehicle is already in parking lot");
        }

        final ParkingTicket ticket = new ParkingTicket(parkingLotNumber);
        tickets.put(ticket, vehicle);
        return ticket;
    }

    public Vehicle pickUp(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException("ticket can not be null");
        }

        if (!tickets.containsKey(parkingTicket)) {
            throw new InValidParkingTicketException("given ticket and existing ticket is not match");
        }

        final Vehicle vehicle = tickets.get(parkingTicket);
        tickets.remove(parkingTicket);
        return vehicle;

    }

    public boolean isFull() {
        return tickets.size() == MAX_SPACE;
    }
}
