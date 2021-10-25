package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.Map;

/**
 * parking boy can park and pickup vehicle
 * if all parking lots are full, throw exception
 * else, park vehicle in first not-full parking lot
 */
public class ParkingBoy implements Parkable, PickUp {

    private final ParkingLotFactory parkingLotFactory = new ParkingLotFactory();

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        final Integer parkingLotNo = findParkingLotNo();
        return parkingLotFactory.park(parkingLotNo, vehicle);
    }

    @Override
    public Vehicle pickUp(ParkingTicket parkingTicket) {
        return parkingLotFactory.pickUp(parkingTicket);
    }

    public Integer findParkingLotNo() {
        return parkingLotFactory.getParkingLots().entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .filter(parkingLot -> parkingLot.getValue().isNotFull())
                .findFirst()
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new ParkingLotFullException("All parking lots are full"));
    }

}
