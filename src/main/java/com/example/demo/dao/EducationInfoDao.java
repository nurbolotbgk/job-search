package com.example.demo.dao;

import com.example.demo.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EducationInfoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(EducationInfo education) {
        String sql = "INSERT INTO education_info (institution, program, start_date, end_date, degree, resume_id) " +
                "VALUES (:institution, :program,:startDate,:endDate,:degree,:resumeId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("institution", education.getInstitution())
                .addValue("program", education.getProgram())
                .addValue("startDate", education.getStartDate())
                .addValue("endDate", education.getEndDate())
                .addValue("degree", education.getDegree())
                .addValue("resumeId", education.getResumeId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<EducationInfo> findByResumeId(Long resumeId) {
        String sql = "SELECT * FROM education_info " +
                "WHERE resume_id = :resumeId " +
                "ORDER BY id";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("resumeId", resumeId),
                new BeanPropertyRowMapper<>(EducationInfo.class)
        );
    }

    public void deleteByResumeId(Long resumeId) {
        String sql = "DELETE FROM education_info WHERE resume_id = :resumeId";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource("resumeId", resumeId)
        );
    }
}