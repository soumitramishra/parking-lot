package com.mishrasoumitra.parkinglot.exceptions;

public class IncorrectPasswordException extends Exception{
    public IncorrectPasswordException() {
        super("Incorrect password. Try again");
    }
}
