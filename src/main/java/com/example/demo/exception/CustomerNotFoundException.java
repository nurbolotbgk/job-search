package com.example.demo.exception;

import java.util.NoSuchElementException;

public class CustomerNotFoundException extends NoSuchElementException {
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}

