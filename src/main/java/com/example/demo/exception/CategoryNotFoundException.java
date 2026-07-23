package com.example.demo.exception;

import java.util.NoSuchElementException;

public class CategoryNotFoundException extends NoSuchElementException {
    public CategoryNotFoundException() {
        super("Category like not found");
    }
}
