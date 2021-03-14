package com.mishrasoumitra.parkinglot.model;

import javax.persistence.*;

@Entity
@Table(name = "parking_lots")
public class ParkingLot {

    private int parkingLotId;
    private String address;
    private int noOfFloors;
    private int noOfRows;
    private int noOfSpots;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int id) {
        this.parkingLotId = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNoOfFloors() {
        return noOfFloors;
    }

    public void setNoOfFloors(int noOfFloors) {
        this.noOfFloors = noOfFloors;
    }

    public int getNoOfRows() {
        return noOfRows;
    }

    public void setNoOfRows(int noOfRows) {
        this.noOfRows = noOfRows;
    }

    public int getNoOfSpots() {
        return noOfSpots;
    }

    public void setNoOfSpots(int noOfSpots) {
        this.noOfSpots = noOfSpots;
    }
}
