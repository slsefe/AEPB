package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.interfaces.Parkable;
import com.example.AEPB.parkingLot.interfaces.Pickupable;

import java.util.Comparator;
import java.util.List;

/**
 * parking boy can park and pickup vehicle
 * if all parking lots are full, throw exception
 * else, park vehicle in first not-full parking lot
 */
public class ParkingBoy implements Parkable, Pickupable {

    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        final ParkingLot parkingLot = parkingLots.stream()
                .filter(ParkingLot::isNotFull)
                .min(Comparator.comparingInt(ParkingLot::getParkingLotNo))
                .orElseThrow(() -> new ParkingLotFullException("All parking lots are full"));

        return parkingLot.park(vehicle);
    }

    @Override
    public Vehicle pickup(ParkingTicket parkingTicket) {
        final ParkingLot parkingLot = parkingLots.stream()
                .filter(lot -> lot.getParkingLotNo().equals(parkingTicket.getParkingLotNo()))
                .findFirst()
                .orElseThrow(InValidParkingTicketException::new);

        return parkingLot.pickup(parkingTicket);
    }

}
