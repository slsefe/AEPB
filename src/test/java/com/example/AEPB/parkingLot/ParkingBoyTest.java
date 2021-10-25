package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.InValidParkingTicketException;
import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingBoyTest {

    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        parkingBoy = new ParkingBoy();
    }

    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_all_parking_lot_is_empty() {
        // given

        // when & then
        assertEquals(1, parkingBoy.findParkingLotNo());
    }

    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_parking_lot_1_has_1_space() {
        // given
        for (int i = 1; i < 50; i++) {
            parkingBoy.park(new Vehicle());
        }

        // when & then
        assertEquals(1, parkingBoy.findParkingLotNo());
    }

    @Test
    void should_return_no_2_when_park_given_parking_lot_1_is_full_and_parking_lot_2_is_not_full() {
        // given
        for (int i = 1; i < 51; i++) {
            parkingBoy.park(new Vehicle());
        }

        // when & then
        assertEquals(2, parkingBoy.findParkingLotNo());
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        for (int i = 0; i < 500; i++) {
            parkingBoy.park(new Vehicle());
        }

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Vehicle()));
    }

    @Test
    void should_return_vehicle_when_pick_up_given_valid_ticket() {
        // given
        final Vehicle vehicle = new Vehicle();
        final ParkingTicket parkingTicket = parkingBoy.park(vehicle);

        // when
        final Vehicle pickedVehicle = parkingBoy.pickUp(parkingTicket);

        // then
        assertEquals(vehicle, pickedVehicle);
    }

    @Test
    void should_throw_exception_when_pick_up_given_no_ticket() {
        // given
        ParkingTicket parkingTicket = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingBoy.pickUp(parkingTicket));
    }

    @Test
    void should_throw_exception_when_pick_up_given_invalid_ticket() {
        // given
        ParkingTicket parkingTicket = new ParkingTicket();

        // when & then
        assertThrows(InValidParkingTicketException.class, () -> parkingBoy.pickUp(parkingTicket));
    }

}
