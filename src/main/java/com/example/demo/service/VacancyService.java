package com.example.demo.service;

import com.example.demo.dto.VacancyDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getVacanciesWithResponses();

    Page<VacancyDto> getAllVacancies(int page, int size, String sort);

    Page<VacancyDto> getVacanciesByUserId(Long userId, int page, int size);

    List<VacancyDto> getVacanciesByCategoryId(Integer categoryId);

    void save(VacancyDto dto);

    void update(Long id, VacancyDto dto);

    void deleteById(Long id);

    VacancyDto findById(Long id);

}
