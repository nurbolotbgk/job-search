package com.example.demo.dao;

import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RespondedApplicantDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void createResponse(RespondedApplicant respondedApplicant) {
        String sql = "INSERT INTO RESPONDED_APPLICANTS (RESUME_ID, VACANCY_ID) " +
                "VALUES (:resumeId, :vacancyId)";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("resumeId", respondedApplicant.getResumeId())
                        .addValue("vacancyId", respondedApplicant.getVacancyId())
        );
    }
}
