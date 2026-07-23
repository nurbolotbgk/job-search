package com.example.demo.service;

import com.example.demo.exception.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(BindingResult result);

    ErrorResponseBody makeResponse(Exception e);
}
