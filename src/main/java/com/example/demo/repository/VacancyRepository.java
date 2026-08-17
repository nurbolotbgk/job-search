package com.example.demo.repository;

import com.example.demo.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    List<Vacancy> findByUser_Id(Long userId);

    List<Vacancy> findByCategory_Id(Integer categoryId);

    List<Vacancy> findByActiveTrueOrderByUpdateTimeDesc();

    List<Vacancy> findDistinctByRespondedApplicantsIsNotEmpty();

    Page<Vacancy> findByActiveTrue(Pageable pageable);
}
