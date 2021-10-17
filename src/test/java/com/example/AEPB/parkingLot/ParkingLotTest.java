package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.CarExistingException;
import com.example.AEPB.parkingLot.exception.NoTicketexception;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import com.example.AEPB.parkingLot.exception.TicketIsUsedException;
import com.example.AEPB.parkingLot.exception.TicketNotMatchedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    void should_success_when_parking_given_parking_lot_has_1_space() {
        // given
        String carPlateNumberTemplate = "00000";
        String newCarPlateNumber;
        for (int i = 0; i < 49; i++) {
            newCarPlateNumber = carPlateNumberTemplate + String.format("%03d", i);
            parkingLot.checkIn(newCarPlateNumber);
        }

        // when
        final ParkingTicket ticket = parkingLot.checkIn("99999");

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_success_when_parking_given_parking_lot_has_50_spaces() {
        // given

        // when
        final ParkingTicket ticket = parkingLot.checkIn("99999");

        // then
        assertNotNull(ticket);
    }

    @Test
    void should_throw_exception_when_parking_given_parking_lot_has_no_space() {
        // given
        String carPlateNumberTemplate = "00000";
        String newCarPlateNumber;
        for (int i = 0; i < 50; i++) {
            newCarPlateNumber = carPlateNumberTemplate + String.format("%03d", i);
            parkingLot.checkIn(newCarPlateNumber);
        }

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingLot.checkIn("99999"));

    }

    @Test
    void should_throw_exception_when_parking_given_car_is_already_in_parting_lot() {
        // given
        String carPlateNumber = "77777";
        parkingLot.checkIn(carPlateNumber);

        // when & then
        assertThrows(CarExistingException.class, () -> parkingLot.checkIn(carPlateNumber));
    }

    @Test
    void should_success_when_pick_up_given_a_valid_ticket() {
        // given
        final ParkingTicket ticket = parkingLot.checkIn("5555");

        // when & then
        assertTrue(parkingLot.pickUp(ticket));
    }

    @Test
    void should_fail_when_pick_up_given_no_ticket() {
        // given
        ParkingTicket ticket = null;

        // when & then
        assertThrows(NoTicketexception.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_ticket_number_and_published_ticket_number_is_not_match() {
        // given
        final String carPlateNumber = "55555";
        parkingLot.checkIn(carPlateNumber);
        final ParkingTicket ticket = new ParkingTicket(carPlateNumber);

        // when & then
        assertThrows(TicketNotMatchedException.class, () -> parkingLot.pickUp(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_a_already_used_ticket() {
        // given
        final String carPlateNumber = "55555";
        final ParkingTicket ticket = parkingLot.checkIn(carPlateNumber);
        parkingLot.pickUp(ticket);

        // when & then
        assertThrows(TicketIsUsedException.class, () -> parkingLot.pickUp(ticket));

    }
}
