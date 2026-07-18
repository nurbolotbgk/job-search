package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.model.Vacancy;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getVacanciesWithUsers() {
        List<Vacancy> vacanciesWithUsers = vacancyDao.getAllVacanciesWithUser();

        return vacanciesWithUsers.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .isActive(e.isActive())
                        .userId(e.getUserId())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }

    @Override
    public List<VacancyDto> getAllVacancies() {
        List<Vacancy> vacanciesWithUsers = vacancyDao.getAllVacancies();

        return vacanciesWithUsers.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .isActive(e.isActive())
                        .userId(e.getUserId())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }
}
