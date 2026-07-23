package com.example.demo.exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {
    public UserNotFoundException() {
        super("User not found");
    }
}
