package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.CarExistingException;
import com.example.AEPB.parkingLot.exception.NoTicketexception;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.TicketIsUsedException;
import com.example.AEPB.parkingLot.exception.TicketNotMatchedException;

import java.util.HashMap;

public class ParkingLot {

    private static int MAX_SPACE = 50;
    private HashMap<String, ParkingTicket> publishedTickets;
    private HashMap<String, ParkingTicket> expiredTickets;

    public ParkingLot() {
        this.publishedTickets = new HashMap<>();
        this.expiredTickets = new HashMap<>();
    }

    public ParkingTicket checkIn(String carPlateNumber) {
        if (publishedTickets.size() == MAX_SPACE) {
            throw new ParkingLotFullException("parking lot is full");
        }

        if (publishedTickets.containsKey(carPlateNumber)) {
            throw new CarExistingException("car is already in parking lot");
        }

        final ParkingTicket ticket = new ParkingTicket(carPlateNumber);
        publishedTickets.put(carPlateNumber, ticket);
        return ticket;
    }

    public boolean pickUp(ParkingTicket givenTicket) {
        if (givenTicket == null) {
            throw new NoTicketexception("ticket is null");
        }

        final String carPlateNumber = givenTicket.getCarPlateNumber();

        if (!publishedTickets.containsKey(carPlateNumber) && !expiredTickets.containsKey(carPlateNumber)) {
            throw new TicketNotMatchedException("given ticket and existing ticket is not match");
        }

        if (!publishedTickets.containsKey(carPlateNumber) && expiredTickets.containsKey(carPlateNumber)) {
            final ParkingTicket usedTicket = expiredTickets.get(carPlateNumber);
            if (usedTicket.equals(givenTicket)) {
                throw new TicketIsUsedException("ticket is already used");
            }
        }

        final ParkingTicket publishedTicket = publishedTickets.get(carPlateNumber);
        if (!publishedTicket.equals(givenTicket)) {
            throw new TicketNotMatchedException("given ticket and existing ticket is not match");
        }

        publishedTickets.remove(carPlateNumber);
        expiredTickets.put(carPlateNumber, publishedTicket);

        return true;
    }
}
