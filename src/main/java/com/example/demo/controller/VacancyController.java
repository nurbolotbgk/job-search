package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.RespondedApplicants;
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

    private final List<RespondedApplicants> responseStorage = new ArrayList<>();
    private final VacancyService vacancyService;

    @GetMapping("/withUsers")
    public List<VacancyDto> findVacanciesWithUsers() {
        return vacancyService.getVacanciesWithUsers();
    }

    @GetMapping
    public List<VacancyDto> findAllVacancies() {
        return vacancyService.getAllVacancies();
    }

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
    public ResponseEntity<List<RespondedApplicants>> getResponsesForVacancy(@PathVariable Integer vacancyId) {
        return ResponseEntity.ok(responseStorage);
    }
    }
