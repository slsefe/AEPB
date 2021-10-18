package com.example.AEPB.parkingLot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingTicketTest {
    @Test
    void should_throw_exception_when_create_parking_ticket_given_parking_lot_number_is_0() {
        // given
        int parkingLotNumber = 0;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new ParkingTicket(parkingLotNumber));
    }

    @Test
    void should_throw_exception_when_create_parking_ticket_given_parking_lot_number_is_11() {
        // given
        int parkingLotNumber = 11;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> new ParkingTicket(parkingLotNumber));
    }

    @Test
    void should_create_ticket_when_create_parking_ticket_given_parking_lot_number_is_1() {
        // given
        int parkingLotNumber = 1;

        // when
        final ParkingTicket parkingTicket = new ParkingTicket(parkingLotNumber);

        // then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_create_ticket_when_create_parking_ticket_given_parking_lot_number_is_10() {
        // given
        int parkingLotNumber = 10;

        // when
        final ParkingTicket parkingTicket = new ParkingTicket(parkingLotNumber);

        // then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_create_ticket_when_create_parking_ticket_given_no_parking_lot_number() {
        // given

        // when
        final ParkingTicket parkingTicket = new ParkingTicket();

        // then
        assertNotNull(parkingTicket);
    }

}
