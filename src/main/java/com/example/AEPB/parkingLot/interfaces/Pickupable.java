package com.example.AEPB.parkingLot.interfaces;

import com.example.AEPB.parkingLot.ParkingTicket;
import com.example.AEPB.parkingLot.Vehicle;

public interface Pickupable {

    Vehicle pickup(ParkingTicket parkingTicket);
}
