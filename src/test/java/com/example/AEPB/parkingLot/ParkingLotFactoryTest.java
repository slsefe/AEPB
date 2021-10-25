package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.VehicleExistingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotFactoryTest {

    ParkingLotFactory parkingLotFactory;

    @BeforeEach
    void setUp() {
        parkingLotFactory = new ParkingLotFactory();
    }

    @Test
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLotFactory.park(1, vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_invalid_parking_lot_number() {
        // given
        Integer parkingLotNumber = 11;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLotFactory.park(parkingLotNumber, new Vehicle()));
    }

    @Test
    void should_throw_exception_when_park_given_parking_lot_has_no_space() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingLotFactory.park(2, new Vehicle());
        }

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingLotFactory.park(2, new Vehicle()));
    }

    @Test
    void should_throw_exception_when_park_given_vehicle_is_already_in_parting_lot() {
        // given
        final Vehicle vehicle = new Vehicle();
        parkingLotFactory.park(3, vehicle);

        // when & then
        assertThrows(VehicleExistingException.class, () -> parkingLotFactory.park(3, vehicle));
    }

    @Test
    void should_throw_exception_when_pick_up_given_no_ticket() {
        // given
        ParkingTicket ticket = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingLotFactory.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_invalid_ticket() {
        // given
        for (int i = 0; i < 50; i++) {
            parkingLotFactory.park(4, new Vehicle());
        }

        // when & then
        final ParkingTicket ticket = new ParkingTicket();
        assertThrows(InValidParkingTicketException.class, () -> parkingLotFactory.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_used_ticket() {
        // given
        final ParkingTicket ticket = parkingLotFactory.park(5, new Vehicle());
        parkingLotFactory.pickUp(ticket);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingLotFactory.pickUp(ticket));
    }

}
