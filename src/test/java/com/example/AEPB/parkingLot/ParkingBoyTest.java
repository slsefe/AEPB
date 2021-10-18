package com.example.AEPB.parkingLot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_all_parking_lot_is_empty() {
        // given
        final ParkingBoy parkingBoy = new ParkingBoy();

        // when
        final ParkingTicket parkingTicket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(1, parkingTicket.getParkingLotNumber());
    }

    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_1_is_full_and_parking_lot_2_is_empty() {
        // given
        final ParkingBoy parkingBoy = new ParkingBoy();
        for (int i = 1; i < 51; i++) {
            parkingBoy.park(new Vehicle());
        }

        // when
        final ParkingTicket parkingTicket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(2, parkingTicket.getParkingLotNumber());
    }

}
