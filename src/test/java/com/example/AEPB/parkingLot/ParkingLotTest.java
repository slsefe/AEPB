package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.VehicleExistingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_parking_lot_has_no_space() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingLot.park(new Vehicle());
        }

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Vehicle()));
    }

    @Test
    void should_throw_exception_when_park_given_vehicle_is_already_in_parting_lot() {
        // given
        final Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);

        // when & then
        assertThrows(VehicleExistingException.class, () -> parkingLot.park(vehicle));
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

    @Test
    void should_throw_exception_when_pick_up_given_no_ticket() {
        // given
        ParkingTicket ticket = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_invalid_ticket() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingLot.park(new Vehicle());
        }

        // when & then
        final ParkingTicket ticket = new ParkingTicket();
        assertThrows(InValidParkingTicketException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_used_ticket() {
        // given
        final Vehicle vehicle = new Vehicle();
        final ParkingTicket ticket = parkingLot.park(vehicle);
        parkingLot.pickUp(ticket);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingLot.pickUp(ticket));
    }
}
