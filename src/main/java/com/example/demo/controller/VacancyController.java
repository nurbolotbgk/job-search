package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.model.Responded_applicants;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final List<Responded_applicants> responseStorage = new ArrayList<>();

    @PostMapping("create")
    public HttpStatus createVacancy(VacancyDto dto) {
        System.out.println(dto.toString());
        return HttpStatus.OK;
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateVacancy(@RequestParam Integer id, @RequestBody VacancyDto dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{vacancyId}/responses")
    public ResponseEntity<List<Responded_applicants>> getResponsesForVacancy(@PathVariable Integer vacancyId) {
        return ResponseEntity.ok(responseStorage);
    }
    }
