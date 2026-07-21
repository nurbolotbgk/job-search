package com.example.demo.dao;

import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Resume> getResumeByCategory(Integer category_id) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category_id);
    }


    public List <Resume> getAllResumes() {
        String sql = "SELECT * FROM resumes;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }

    public Optional<Resume> findResumeById(Integer id) {
        String sql = "SELECT * FROM resumes WHERE id = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult
                (jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)));
    }

    public List<Resume> getResumesMadeByUser(Long userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = ?;";
        return
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public void save(Resume resume) {
        String sql = "insert into resumes(name, salary, is_active, created_date, update_time, user_id, category_id) " +
                "values (:name, :salary, :is_active, :created_date, :update_time, (select id from users where id = :user_id), :category_id)";
        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", resume.getName())
                        .addValue("salary", resume.getSalary())
                        .addValue("is_active", resume.isActive())
                        .addValue("created_date", resume.getCreatedDate())
                        .addValue("update_time", resume.getUpdateTime())
                        .addValue("user_id", resume.getUserId())
                        .addValue("category_id", resume.getCategoryId())
        );
    }
}