package com.example.demo.service.impl;

import com.example.demo.dto.VacancyDto;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VacancyNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.User;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<VacancyDto> getVacanciesWithResponses() {
        List<Vacancy> vacancies =
                vacancyRepository.findDistinctByRespondedApplicantsIsNotEmpty();

        if (vacancies.isEmpty()) {
            throw new VacancyNotFoundException();
        }

        return vacancies.stream()
                .map(v -> VacancyDto.builder()
                        .id(v.getId())
                        .name(v.getName())
                        .description(v.getDescription())
                        .categoryId(v.getCategory().getId())
                        .salary(v.getSalary())
                        .expFrom(v.getExpFrom())
                        .expTo(v.getExpTo())
                        .active(v.getActive())
                        .userId(v.getUser().getId())
                        .createdDate(v.getCreatedDate())
                        .updateTime(v.getUpdateTime())
                        .build())
                .toList();
    }

    @Override
    public Page<VacancyDto> getAllVacancies(int page, int size, String sort) {

        Page<Vacancy> vacancies;

        if ("date_asc".equals(sort)) {

            Pageable pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("createdDate").ascending()
            );

            vacancies = vacancyRepository.findByActiveTrue(pageable);

        } else if ("responses_asc".equals(sort)) {

            Pageable pageable = PageRequest.of(page, size);

            vacancies =
                    vacancyRepository.findActiveOrderByResponsesAsc(pageable);

        } else if ("responses_desc".equals(sort)) {

            Pageable pageable = PageRequest.of(page, size);

            vacancies =
                    vacancyRepository.findActiveOrderByResponsesDesc(pageable);

        } else {

            Pageable pageable = PageRequest.of(
                    page,
                    size,
                    Sort.by("createdDate").descending()
            );

            vacancies = vacancyRepository.findByActiveTrue(pageable);
        }

        return vacancies.map(v -> VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategory().getId())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .active(v.getActive())
                .userId(v.getUser().getId())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .build());
    }

    @Override
    public Page<VacancyDto> getVacanciesByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Vacancy> vacancies = vacancyRepository.findByUser_Id(userId, pageable);
        return vacancies.map(v -> VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategory().getId())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .active(v.getActive())
                .userId(v.getUser().getId())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .build());
    }

    @Override
    public List<VacancyDto> getVacanciesByCategoryId(Integer categoryId) {
        List<Vacancy> vacancies =
                vacancyRepository.findByCategory_Id(categoryId);

        if (vacancies.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return vacancies.stream()
                .map(e -> VacancyDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .description(e.getDescription())
                        .categoryId(e.getCategory().getId())
                        .salary(e.getSalary())
                        .expFrom(e.getExpFrom())
                        .expTo(e.getExpTo())
                        .active(e.getActive())
                        .userId(e.getUser().getId())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .build())
                .toList();
    }

    @Override
    public void save(VacancyDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Vacancy vacancy = new Vacancy();

        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setActive(dto.getActive());
        vacancy.setCreatedDate(
                dto.getCreatedDate() != null
                        ? dto.getCreatedDate()
                        : LocalDateTime.now()
        );
        vacancy.setUpdateTime(
                dto.getUpdateTime() != null
                        ? dto.getUpdateTime()
                        : LocalDateTime.now()
        );
        vacancy.setCategory(category);
        vacancy.setUser(user);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void update(Long id, VacancyDto dto) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        vacancy.setName(dto.getName());
        vacancy.setDescription(dto.getDescription());
        vacancy.setSalary(dto.getSalary());
        vacancy.setExpFrom(dto.getExpFrom());
        vacancy.setExpTo(dto.getExpTo());
        vacancy.setActive(dto.getActive());
        vacancy.setUpdateTime(LocalDateTime.now());
        vacancy.setCategory(category);

        vacancyRepository.save(vacancy);
    }

    @Override
    public void deleteById(Long id) {
        if (!vacancyRepository.existsById(id)) {
            throw new VacancyNotFoundException();
        }

        vacancyRepository.deleteById(id);
    }

    @Override
    public VacancyDto findById(Long id) {
        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        return VacancyDto.builder()
                .id(vacancy.getId())
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategory().getId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .active(vacancy.getActive())
                .userId(vacancy.getUser().getId())
                .createdDate(vacancy.getCreatedDate())
                .updateTime(vacancy.getUpdateTime())
                .build();
    }


}
