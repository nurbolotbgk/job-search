package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public void save(Vacancy vacancy) {
        String sql = "INSERT INTO vacancies (name, description, salary, exp_from, exp_to, is_active, created_date, update_time, category_id, user_id) " +
                "VALUES (:name, :description, :salary, :expFrom, :expTo, :isActive, :createdDate, :updateTime, :categoryId, :userId)";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", vacancy.getName())
                        .addValue("description", vacancy.getDescription())
                        .addValue("salary", vacancy.getSalary())
                        .addValue("expFrom", vacancy.getExpFrom())
                        .addValue("expTo", vacancy.getExpTo())
                        .addValue("isActive", vacancy.isActive())
                        .addValue("createdDate", vacancy.getCreatedDate())
                        .addValue("updateTime", vacancy.getUpdateTime())
                        .addValue("categoryId", vacancy.getCategoryId())
                        .addValue("userId", vacancy.getUserId())
        );
    }

    public void update(Vacancy vacancy) {
        String sql = "UPDATE vacancies SET " +
                "name = :name, " +
                "description = :description, " +
                "salary = :salary, " +
                "exp_from = :expFrom, " +
                "exp_to = :expTo, " +
                "is_active = :isActive, " +
                "update_time = :updateTime, " +
                "category_id = :categoryId " +
                "WHERE id = :id";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("id", vacancy.getId())
                        .addValue("name", vacancy.getName())
                        .addValue("description", vacancy.getDescription())
                        .addValue("salary", vacancy.getSalary())
                        .addValue("expFrom", vacancy.getExpFrom())
                        .addValue("expTo", vacancy.getExpTo())
                        .addValue("isActive", vacancy.isActive())
                        .addValue("updateTime", LocalDateTime.now()) // Актуализируем время обновления
                        .addValue("categoryId", vacancy.getCategoryId())
        );
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM vacancies WHERE id = :id";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource().addValue("id", id)
        );
    }
}
