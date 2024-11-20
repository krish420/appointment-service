package com.hackathon.appointments.exception;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message){
        super(message);
    }
}
