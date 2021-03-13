package com.mishrasoumitra.parkinglot.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String userName) {
        super(String.format("User %s is not present",userName));
    }

}