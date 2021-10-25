package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SmartParkingBoyTest {

    private SmartParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        parkingBoy = new SmartParkingBoy();
    }

    @Test
    void should_park_in_parking_lot_no_1_when_park_given_all_parking_lots_are_empty() {
        // given

        // when & then
        assertEquals(1, parkingBoy.findParkingLotNo());
    }

    @Test
    void should_park_in_parking_lot_no_2_when_parking_lot_no_has_49_space_and_others_have_50_space() {
        // given
        parkingBoy.park(new Vehicle());

        // when & then
        assertEquals(2, parkingBoy.findParkingLotNo());
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        parkFiveHundredVehicles();

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingBoy.park(new Vehicle()));
    }

    private void parkFiveHundredVehicles() {
        for (int i = 0; i < 500; i++) {
            parkingBoy.park(new Vehicle());
        }
    }

}
