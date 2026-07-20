package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("main")  // http://localhost:8089/main
@RequiredArgsConstructor
public class MainController {
//    private final MainService mainService;
//
//           // http://localhost:8089/main/index
//    public MainDto index() {
//        return mainService.getinfo();
//    }
@GetMapping("index")
public String index() {
    return "Hello world!!!";
}
}
