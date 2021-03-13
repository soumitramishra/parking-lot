package com.mishrasoumitra.parkinglot.model;

public class Response {
    private String message;
    private boolean successStatus;
    public Response() {

    }

    public boolean isSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(boolean successStatus) {
        this.successStatus = successStatus;
    }

    public Response(String message) {
        this.message = message;
        this.successStatus=true;
    }
    public Response(String message, boolean successStatus) {
        this.message = message;
        this.successStatus=successStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
