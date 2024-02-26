package com.example.pruebatec.error;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String message) {
        super(message);
    }
}
