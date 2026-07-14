package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")  // http://localhost:8089/main
@RequiredArgsConstructor
public class UserController {

    @GetMapping("index")
    public String index() {
        return "Hello!!!";
    }
}
