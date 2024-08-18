package com.security.exceptionhandler;

public class CustomExceptionClass extends RuntimeException{

    public CustomExceptionClass(String message) {
        super(message);
    }
}
