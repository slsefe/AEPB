package com.example.AEPB.parkingLot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingLotTest {

    ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    void should_return_ticket_when_park_given_parking_lot_has_1_space() {
        // given
        for (int i = 0; i < 49; i++) {
            parkingLot.park(new Vehicle());
        }

        // when
        final ParkingTicket ticket = parkingLot.park(new Vehicle());

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_return_ticket_when_park_given_parking_lot_has_50_spaces() {
        // given

        // when
        final ParkingTicket ticket = parkingLot.park(new Vehicle());

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_return_vehicle_when_pick_up_given_a_ticket() {
        // given
        final Vehicle vehicle1 = new Vehicle();
        final ParkingTicket ticket = parkingLot.park(vehicle1);

        // when
        final Vehicle vehicle2 = parkingLot.pickUp(ticket);

        // then
        assertEquals(vehicle1, vehicle2);
    }

}
