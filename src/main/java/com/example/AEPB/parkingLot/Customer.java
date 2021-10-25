package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * customer can park and pickup vehicle
 * customer can park vehicle in any not-full parking lot
 */
public class Customer implements Parkable, PickUp {

    private final ParkingLotFactory parkingLotFactory = new ParkingLotFactory();
    private final Random random = new Random();

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        final Integer parkingLotNo = findParkingLotNo();
        return parkingLotFactory.park(parkingLotNo, vehicle);
    }

    @Override
    public Vehicle pickUp(ParkingTicket parkingTicket) {
        return parkingLotFactory.pickUp(parkingTicket);
    }

    private Integer findParkingLotNo() {
        final List<Integer> parkingLotNos = parkingLotFactory.getParkingLots().entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .filter(parkingLot -> parkingLot.getValue().isNotFull())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (parkingLotNos.isEmpty()) {
            throw new ParkingLotFullException("All parking lots are full");
        }
        return parkingLotNos.get(random.nextInt(parkingLotNos.size()));
    }
}
