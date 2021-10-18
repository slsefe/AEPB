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

}
