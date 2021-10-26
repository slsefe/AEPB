package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {

    ParkingLot parkingLot;

    @Test
    void should_throw_exception_when_create_parking_lot_given_space_smaller_than_1() {
        // given
        Integer space = 0;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new ParkingLot(1, space));
    }

    @Test
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        parkingLot = new ParkingLot(1, 10);
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_parking_lot_has_no_space() {
        // given
        parkingLot = new ParkingLot(1, 1);
        parkingLot.park(new Vehicle());

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Vehicle()));
    }

    @Test
    void should_return_ticket_when_park_given_parking_lot_has_more_than_zero_space() {
        // given
        parkingLot = new ParkingLot(1, 1);

        // when
        final ParkingTicket ticket = parkingLot.park(new Vehicle());

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_throw_exception_when_pick_up_given_no_ticket() {
        // given
        parkingLot = new ParkingLot(1, 1);
        ParkingTicket ticket = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLot.pickup(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_a_faked_ticket() {
        // given
        parkingLot = new ParkingLot(1, 1);
        final ParkingTicket ticket = new ParkingTicket(1);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingLot.pickup(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_a_used_ticket() {
        // given
        parkingLot = new ParkingLot(1, 1);
        final ParkingTicket ticket = parkingLot.park(new Vehicle());
        parkingLot.pickup(ticket);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingLot.pickup(ticket));
    }

    @Test
    void should_return_same_vehicle_when_pick_up_given_a_ticket() {
        // given
        parkingLot = new ParkingLot(1, 1);
        final Vehicle vehicle1 = new Vehicle();
        final ParkingTicket ticket = parkingLot.park(vehicle1);

        // when
        final Vehicle vehicle2 = parkingLot.pickup(ticket);

        // then
        assertEquals(vehicle1, vehicle2);
    }

}
