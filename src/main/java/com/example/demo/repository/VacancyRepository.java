package com.example.demo.repository;

import com.example.demo.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    Page<Vacancy> findByUser_Id(Long userId, Pageable pageable);

    List<Vacancy> findByCategory_Id(Integer categoryId);

    List<Vacancy> findByActiveTrueOrderByUpdateTimeDesc();

    List<Vacancy> findDistinctByRespondedApplicantsIsNotEmpty();

    Page<Vacancy> findByActiveTrue(Pageable pageable);

    @Query("SELECT v FROM Vacancy v " +
            "WHERE v.active = true " +
            "ORDER BY SIZE(v.respondedApplicants) DESC")
    Page<Vacancy> findActiveOrderByResponsesDesc(Pageable pageable);

    @Query("SELECT v FROM Vacancy v " +
            "WHERE v.active = true " +
            "ORDER BY SIZE(v.respondedApplicants) ASC")
    Page<Vacancy> findActiveOrderByResponsesAsc(Pageable pageable);
}