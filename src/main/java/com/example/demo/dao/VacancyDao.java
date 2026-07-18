package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> getAllVacanciesWithUser() {
        String sql = "SELECT * FROM vacancies WHERE user_id IS NOT NULL;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getAllVacancies() {
        String sql = "SELECT * FROM vacancies;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> getVacanciesByCategory(Integer category_id) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Vacancy.class), category_id);
    }
}
