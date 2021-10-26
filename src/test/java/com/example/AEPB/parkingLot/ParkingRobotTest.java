package com.example.AEPB.parkingLot;

import com.example.AEPB.parkingLot.exception.ParkingLotFullException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingRobotTest {

    private ParkingRobot parkingRobot;

    @Test
    void should_throw_exception_when_park_given_no_vehicle() {
        // given
        parkingRobot = new ParkingRobot(List.of());
        Vehicle vehicle = null;

        // when & then
        assertThrows(IllegalArgumentException.class, () -> parkingRobot.park(vehicle));
    }

    @Test
    void should_throw_exception_when_park_given_all_parking_lots_are_full() {
        // given
        parkingRobot = new ParkingRobot(List.of(new ParkingLot(1, 1)));
        // park a new vehicle in parking lot no_1 so the parking lot no_1 is full
        parkingRobot.park(new Vehicle());

        // when & then
        assertThrows(ParkingLotFullException.class, () -> parkingRobot.park(new Vehicle()));
    }

    @Test
    void should_park_vehicle_in_parking_lot_1_when_park_given_all_parking_lots_are_empty() {
        // given
        parkingRobot = new ParkingRobot(List.of(
                new ParkingLot(1, 1),
                new ParkingLot(2, 2),
                new ParkingLot(3, 3)
        ));

        // when
        final ParkingTicket ticket = parkingRobot.park(new Vehicle());

        // then
        assertEquals(1, ticket.getParkingLotNo());
    }

    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_1_vacancy_rate_is_two_thirds_and_parking_lot_2_vacancy_rate_is_one() {
        // given
        parkingRobot = new ParkingRobot(List.of(
                new ParkingLot(1, 3),
                new ParkingLot(2, 1)
        ));
        // park the vehicle in parking lot no 1 so that its vacancy rate is 2/3
        parkingRobot.park(new Vehicle());

        // when
        final ParkingTicket ticket = parkingRobot.park(new Vehicle());

        // then
        assertEquals(2, ticket.getParkingLotNo());
    }


    @Test
    void should_park_vehicle_in_parking_lot_2_when_park_given_parking_lot_2_and_parking_lot_3_both_has_most_space() {
        // given
        parkingRobot = new ParkingRobot(List.of(
                new ParkingLot(1, 3),
                new ParkingLot(2, 1),
                new ParkingLot(3, 1)
        ));
        // park the vehicle in parking lot no 1 so that its vacancy rate is 2/3
        parkingRobot.park(new Vehicle());

        // when
        final ParkingTicket ticket = parkingRobot.park(new Vehicle());

        // then
        assertEquals(2, ticket.getParkingLotNo());
    }

}
