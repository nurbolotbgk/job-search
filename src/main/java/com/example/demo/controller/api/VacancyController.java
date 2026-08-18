package com.example.demo.controller.api;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @GetMapping("/vacanciesWithResponses")
    public List<VacancyDto> findVacanciesWithResponses() {
        return vacancyService.getVacanciesWithResponses();
    }

    /*@GetMapping
    public List<VacancyDto> findAllVacancies() {
        return vacancyService.getAllVacancies();
    }*/

    @GetMapping("/categories/{categoryId}")
    public List<VacancyDto> findVacanciesByCategory(@PathVariable Integer categoryId) {
        return vacancyService.getVacanciesByCategoryId(categoryId);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createVacancy(@Valid @RequestBody VacancyDto dto) {
        vacancyService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateVacancy(@PathVariable Long id, @Valid  @RequestBody VacancyDto dto) {

        vacancyService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    }
