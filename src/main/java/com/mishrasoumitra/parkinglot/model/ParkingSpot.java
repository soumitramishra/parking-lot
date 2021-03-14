package com.mishrasoumitra.parkinglot.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="parking_spots")
public class ParkingSpot {
    @EmbeddedId
    private ParkingSpotId parkingSpotId;

    private String spotSize;
    private boolean occupied;
    private String vehicleNo;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return occupied == that.occupied &&
                Objects.equals(parkingSpotId, that.parkingSpotId) &&
                Objects.equals(spotSize, that.spotSize) &&
                Objects.equals(vehicleNo, that.vehicleNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parkingSpotId, spotSize, occupied, vehicleNo);
    }
}

