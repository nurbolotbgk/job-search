package com.example.demo.exception;

import java.util.NoSuchElementException;

public class VacancyNotFoundException extends NoSuchElementException {
    public VacancyNotFoundException() {
        super("Vacancy not found");
    }
}
