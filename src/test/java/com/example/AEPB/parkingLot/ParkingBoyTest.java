package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @Test
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        parkingBoy = new ParkingBoy(List.of());
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingBoy.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        parkingBoy = new ParkingBoy(List.of(new ParkingLot(1, 1)));
        parkingBoy.park(new Vehicle());

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Vehicle()));
    }


    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_parking_lot_has_space() {
        // given
        parkingBoy = new ParkingBoy(List.of(
                new ParkingLot(1, 1),
                new ParkingLot(2, 1)
        ));

        // when
        final ParkingTicket ticket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(1, ticket.getParkingLotNo());
    }


    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_1_is_full_and_parking_lot_2_is_not_full() {
        // given
        parkingBoy = new ParkingBoy(List.of(
                new ParkingLot(1, 1),
                new ParkingLot(2, 1)
        ));
        parkingBoy.park(new Vehicle());

        // when
        final ParkingTicket ticket = parkingBoy.park(new Vehicle());

        // then
        assertEquals(2, ticket.getParkingLotNo());
    }

    @Test
    void should_throw_exception_when_pickup_given_no_ticket() {
        // given
        parkingBoy = new ParkingBoy(List.of(new ParkingLot(1, 1)));
        final ParkingTicket ticket = new ParkingTicket(1);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingBoy.pickup(ticket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_invalid_ticket() {
        // given
        parkingBoy = new ParkingBoy(List.of(new ParkingLot(1, 1)));
        ParkingTicket ticket = new ParkingTicket(1);

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingBoy.pickup(ticket));
    }

    @Test
    void should_return_vehicle_when_pick_up_given_valid_ticket() {
        // given
        parkingBoy = new ParkingBoy(List.of(new ParkingLot(1, 1)));
        final Vehicle parkedVehicle = new Vehicle();
        final ParkingTicket parkingTicket = parkingBoy.park(parkedVehicle);

        // when
        final Vehicle pickupVehicle = parkingBoy.pickup(parkingTicket);

        // then
        assertEquals(parkedVehicle, pickupVehicle);
    }

}
