package com.example.demo.dao;

import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;

    public List<RespondedApplicant> getAllRespondedApplicants() {
        String sql = "SELECT * FROM responded_applicants;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RespondedApplicant.class));
    }
}
