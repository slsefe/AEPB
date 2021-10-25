package com.example.AEPB.parkingLot;

import java.util.HashMap;

public class ParkingLot {

    private static final Integer MAX_SPACE = 50;
    private final HashMap<ParkingTicket, Vehicle> tickets;

    public ParkingLot() {
        this.tickets = new HashMap<>();
    }

    public ParkingTicket park(Vehicle vehicle) {
        final ParkingTicket ticket = new ParkingTicket();
        tickets.put(ticket, vehicle);
        return ticket;
    }

    public Vehicle pickUp(ParkingTicket parkingTicket) {
        final Vehicle vehicle = tickets.get(parkingTicket);
        tickets.remove(parkingTicket);
        return vehicle;

    }

    public boolean isFull() {
        return tickets.size() == MAX_SPACE;
    }

    public boolean isNotFull() {
        return !isFull();
    }

    public Integer curSpace() {
        return MAX_SPACE - tickets.size();
    }

    public HashMap<ParkingTicket, Vehicle> getTickets() {
        return tickets;
    }
}
