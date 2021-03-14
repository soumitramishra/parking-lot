package com.mishrasoumitra.parkinglot.model;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ParkingSpotId implements Serializable {

    private int parkingLotId;

    private int floorNo;
    private int rowNo;
    private int spotNo;
    public ParkingSpotId() {

    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getSpotNo() {
        return spotNo;
    }

    public void setSpotNo(int spotNo) {
        this.spotNo = spotNo;
    }
}
