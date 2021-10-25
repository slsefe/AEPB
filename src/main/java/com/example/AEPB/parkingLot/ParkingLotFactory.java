package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.VehicleExistingException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLotFactory {
    private final static int NUMBER_OF_PARKING_LOT = 10;

    private final Map<Integer, ParkingLot> parkingLots = new HashMap<>();

    public ParkingLotFactory() {
        for (int i = 1; i <= NUMBER_OF_PARKING_LOT; i++) {
            parkingLots.put(i, new ParkingLot());
        }
    }

    public Map<Integer, ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket park(Integer parkingLotNo, Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        if (parkingLotNo < 1 || parkingLotNo > NUMBER_OF_PARKING_LOT) {
            throw new IllegalArgumentException("Parking lot number should be between 1 and " + NUMBER_OF_PARKING_LOT);
        }

        if (parkingLots.get(parkingLotNo).isFull()) {
            throw new ParkingLotFullException("the parking lot is full");
        }

        final Optional<HashMap<ParkingTicket, Vehicle>> ticket = parkingLots.values().stream()
                .map(ParkingLot::getTickets)
                .filter(tickets -> tickets.containsValue(vehicle))
                .findFirst();
        if (ticket.isPresent()) {
            throw new VehicleExistingException("the vehicle is already in parking lot.");
        }

        return parkingLots.get(parkingLotNo).park(vehicle);
    }

    public Vehicle pickUp(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException("ticket can not be null");
        }

        final Integer parkingLotNo = findParkingLotNoWithTicket(parkingTicket);

        return parkingLots.get(parkingLotNo).pickUp(parkingTicket);
    }

    private Integer findParkingLotNoWithTicket(ParkingTicket parkingTicket) {
        return parkingLots.entrySet().stream()
                .filter(entry -> entry.getValue().getTickets().containsKey(parkingTicket))
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(InValidParkingTicketException::new);
    }

    public Integer getMaxSpace() {
        return parkingLots.values().stream()
                .map(ParkingLot::curSpace)
                .max(Comparator.comparingInt(curSpace -> curSpace))
                .orElseThrow(IllegalArgumentException::new);
    }
}
