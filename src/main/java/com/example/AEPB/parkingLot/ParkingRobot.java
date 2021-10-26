package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.interfaces.Parkable;

import java.util.Comparator;
import java.util.List;

public class ParkingRobot implements Parkable {

    private final List<ParkingLot> parkingLots;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("vehicle can not be null");
        }

        final ParkingLot parkingLot = parkingLots.stream()
                .filter(ParkingLot::isNotFull)
                .min(Comparator.comparing(ParkingLot::calculateVacancyRate, Comparator.reverseOrder())
                        .thenComparing(ParkingLot::getParkingLotNo))
                .orElseThrow(() -> new ParkingLotFullException("All parking lots are full."));

        return parkingLot.park(vehicle);
    }

}
