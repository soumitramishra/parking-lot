package com.mishrasoumitra.parkinglot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="parking_spots")
public class ParkingSpot {
    @EmbeddedId
    private ParkingSpotId parkingSpotId;

    private String spotSize;
    private boolean occupied;
    String vehicleNo;

    public ParkingSpotId getParkingSpotId() {
        return parkingSpotId;
    }

    public void setParkingSpotId(ParkingSpotId parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public String getSpotSize() {
        return spotSize;
    }

    public void setSpotSize(String spotSize) {
        this.spotSize = spotSize;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }
}

