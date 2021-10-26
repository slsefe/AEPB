package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SmartParkingBoyTest {

    private SmartParkingBoy parkingBoy;

    @Test
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        parkingBoy = new SmartParkingBoy(List.of());
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingBoy.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        parkingBoy = new SmartParkingBoy(List.of(new ParkingLot(1, 1)));
        parkingBoy.park(new Vehicle());

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Vehicle()));
    }

    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_2_has_most_space() {
        // given
        parkingBoy = new SmartParkingBoy(List.of(
                new ParkingLot(1, 1),
                new ParkingLot(2, 2)
        ));

        // when
        final ParkingTicket ticket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(2, ticket.getParkingLotNo());
    }


    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_2_and_parking_lot_3_both_has_most_space() {
        // given
        parkingBoy = new SmartParkingBoy(List.of(
                new ParkingLot(1, 1),
                new ParkingLot(2, 2),
                new ParkingLot(3, 2)
        ));

        // when
        final ParkingTicket ticket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(2, ticket.getParkingLotNo());
    }

}
