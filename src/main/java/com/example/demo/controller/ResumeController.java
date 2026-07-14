package com.example.demo.controller;

import com.example.demo.model.Resumes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {
    private final List<Resumes> storage = new ArrayList<>();
    @GetMapping("resumes")
    public ResponseEntity<List<Resumes>> getAllresumes() {
        return ResponseEntity.ok(storage);
    }
}
