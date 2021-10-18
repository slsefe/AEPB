package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
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

    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_parking_lot_1_is_not_full_and_parking_lot_2_is_full() {
        // given
        final ParkingBoy parkingBoy = new ParkingBoy();
        final Vehicle firstVehicle = new Vehicle();
        final ParkingTicket firstParkingTicket = parkingBoy.park(firstVehicle);
        for (int i = 1; i < 51; i++) {
            parkingBoy.park(new Vehicle());
        }
        parkingBoy.pickUp(firstParkingTicket);

        // when
        final ParkingTicket parkingTicket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(1, parkingTicket.getParkingLotNumber());
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        final ParkingBoy parkingBoy = new ParkingBoy();
        for (int i = 0; i < 500; i++) {
            parkingBoy.park(new Vehicle());
        }

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Vehicle()));
    }

}
