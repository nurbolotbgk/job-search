package com.example.demo.service;

import com.example.demo.dto.MainDto;
import com.example.demo.model.Main;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    public MainDto getinfo() {
        Main main = new Main("Hello world!!!");
        MainDto dto = new MainDto();
        dto.setTitle(main.getTitle());
        return dto;
    }
}
