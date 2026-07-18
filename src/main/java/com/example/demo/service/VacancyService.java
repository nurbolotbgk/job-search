package com.example.demo.service;

import com.example.demo.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getVacanciesWithUsers();

    List<VacancyDto> getAllVacancies();

    List<VacancyDto> getVacanciesByCategoryId(Integer categoryId);
}
