package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.model.RespondedApplicant;
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

    private final List<RespondedApplicant> responseStorage = new ArrayList<>();
    private final VacancyService vacancyService;

    @GetMapping("/withUsers")
    public List<VacancyDto> findVacanciesWithUsers() {
        return vacancyService.getVacanciesWithUsers();
    }

    @GetMapping
    public List<VacancyDto> findAllVacancies() {
        return vacancyService.getAllVacancies();
    }

    @GetMapping("/categories/{categoryId}")
    public List<VacancyDto> findVacanciesByCategory(@PathVariable Integer categoryId) {
        return vacancyService.getVacanciesByCategoryId(categoryId);
    }


    @PutMapping("update")
    public ResponseEntity<Void> updateVacancy(@RequestParam Integer id, @RequestBody VacancyDto dto) {
        return ResponseEntity.ok().build();
    }



    @GetMapping("{vacancyId}/responses")
    public ResponseEntity<List<RespondedApplicant>> getResponsesForVacancy(@PathVariable Integer vacancyId) {
        return ResponseEntity.ok(responseStorage);
    }

    @PostMapping
    public ResponseEntity<Void> createVacancy(@RequestBody VacancyDto dto) {
        vacancyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateVacancy(@RequestBody VacancyDto dto) {
        vacancyService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    }
