package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;

/**
 * smart parking boy can park and pickup vehicle.
 * smart parking boy should park vehicle in first most parking place parking lot.
 */
public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        final ParkingLot parkingLot = getParkingLots().stream()
                .filter(ParkingLot::isNotFull)
                .min(Comparator.comparing(ParkingLot::vacantSpaces, Comparator.reverseOrder())
                        .thenComparing(ParkingLot::vacantSpaces))
                .orElseThrow(() -> new ParkingLotFullException("All parking lots are full."));

        return parkingLot.park(vehicle);
    }

}
