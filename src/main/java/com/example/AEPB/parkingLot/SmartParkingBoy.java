package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.Map;

/**
 * smart parking boy can park and pickup vehicle.
 * smart parking boy should park vehicle in first most parking place parking lot.
 */
public class SmartParkingBoy extends ParkingBoy {

    private final ParkingLotFactory parkingLotFactory = new ParkingLotFactory();

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        final Integer parkingLotNo = findParkingLotNo();
        return parkingLotFactory.park(parkingLotNo, vehicle);
    }

    @Override
    public Integer findParkingLotNo() {
        final Integer curMaxSpace = parkingLotFactory.getMaxSpace();
        return parkingLotFactory.getParkingLots().entrySet().stream()
                .filter(parkingLot -> parkingLot.getValue().curSpace().equals(curMaxSpace))
                .min(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new ParkingLotFullException("All parking lots are full"));
    }
}
