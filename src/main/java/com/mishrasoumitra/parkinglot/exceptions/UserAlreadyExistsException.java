package com.mishrasoumitra.parkinglot.exceptions;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String userName) {
        super(String.format("Username %s already exists. Try another", userName));
    }
}
