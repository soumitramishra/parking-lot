package com.mishrasoumitra.parkinglot.exceptions;

public class InvalidParkingSpotSizeException extends Exception{
    public InvalidParkingSpotSizeException(String size) {
        super(String.format("Invalid Size %s, please enter one of the valid sizes:small,medium or large", size));
    }
}
