package com.mishrasoumitra.parkinglot.exceptions;

public class ParkingLotNotFoundException extends Exception{
    public ParkingLotNotFoundException(int id) {
        super(String.format("Parking lot with ID %d not found",id));
    }
}
