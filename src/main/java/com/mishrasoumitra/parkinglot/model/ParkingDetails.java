package com.mishrasoumitra.parkinglot.model;

import java.util.List;

public class ParkingDetails {
    private ParkingLot parkingLot;
    private List<ParkingSpot> parkingSpots;

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
