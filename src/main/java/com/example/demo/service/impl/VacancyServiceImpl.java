package com.example.demo.service.impl;

import com.example.demo.dao.VacancyDao;
import com.example.demo.dto.VacancyDto;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.VacancyNotFoundException;
import com.example.demo.model.Vacancy;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    @Override
    public List<VacancyDto> getVacanciesWithResponses() {
        List<Vacancy> vacanciesWithUsers = vacancyDao.getAllVacanciesWithResponses();

        if (vacanciesWithUsers.isEmpty()) {
            throw new VacancyNotFoundException();
        }

        return vacanciesWithUsers.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .active(e.getActive())
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

        if (vacanciesWithUsers.isEmpty()) {
            throw new VacancyNotFoundException();
        }

        return vacanciesWithUsers.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .active(e.getActive())
                        .userId(e.getUserId())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategoryId(Integer categoryId) {
        List<Vacancy> vacanciesByCatId = vacancyDao.getVacanciesByCategory(categoryId);

        if (vacanciesByCatId.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return vacanciesByCatId.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategoryId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .active(e.getActive())
                        .userId(e.getUserId())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build()
                )
                .toList();
    }

    @Override
    public void save(VacancyDto dto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setActive(dto.getActive());

        vacancy.setCreatedDate(dto.getCreatedDate());
        vacancy.setUpdateTime(dto.getUpdateTime());

        vacancy.setCategoryId(dto.getCategoryId());
        vacancy.setUserId(dto.getUserId());

        vacancyDao.save(vacancy);
    }

    @Override
    public void update(Long id, VacancyDto dto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(dto.getId());
        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setActive(dto.getActive());
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancy.setCategoryId(dto.getCategoryId());
        vacancy.setUserId(dto.getUserId());
        vacancyDao.update(vacancy);
    }

    @Override
    public void deleteById(Long id) {
        vacancyDao.deleteById(id);
    }
}
