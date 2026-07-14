package com.example.demo.controller;

import com.example.demo.dto.VacancyDto;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {
    @PostMapping("create")
    public HttpStatus createVacancy(VacancyDto dto) {
        System.out.println(dto.toString());
        return HttpStatus.OK;
    }

    @PutMapping("update")
    public HttpStatus updateVacancy(@RequestParam Integer id, VacancyDto dto) {
        System.out.println(dto.toString());
        return HttpStatus.OK;
    }

    @DeleteMapping("delete")
    public HttpStatus deleteVacancy(@RequestParam Integer id) {

        return HttpStatus.OK;
    }

    }
