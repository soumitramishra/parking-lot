package com.mishrasoumitra.parkinglot.exceptions;

public class ParkingNotAvailableException extends Exception{
    public ParkingNotAvailableException(int parkingLotNo, String size) {
        super(String.format("No parking of size %s available in Parking Lot %d", size, parkingLotNo));
    }
}
