package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("applicant")
@RequiredArgsConstructor
public class ResponseApplicantController {
    private final List<Vacancy> vacancyStorage = new ArrayList<>();

    @PostMapping("create")
    public HttpStatus createResume(ResumeDto dto) {
        System.out.println(dto.toString());
        return HttpStatus.OK;
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateResume(@RequestParam Integer id, @RequestBody ResumeDto dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }



}
