package com.example.demo.exceptions;

/**
 * @author mingyux
 */

public class RestNotFoundException extends Exception {
    public RestNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}