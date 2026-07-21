package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Resume> getResumeByCategory(Integer category_id) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), category_id);
    }


    public List <Resume> getAllResumes() {
        String sql = "SELECT * FROM resumes;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class));
    }


}
