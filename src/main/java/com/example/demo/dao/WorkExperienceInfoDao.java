package com.example.demo.dao;

import com.example.demo.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WorkExperienceInfoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(WorkExperienceInfo experience) {
        String sql = " INSERT INTO work_experience_info (years, company_name, position, responsibilities, resume_id) " +
                "VALUES (:years, :companyName, :position, :responsibilities,:resumeId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("years", experience.getYears())
                .addValue("companyName", experience.getCompanyName())
                .addValue("position", experience.getPosition())
                .addValue("responsibilities", experience.getResponsibilities())
                .addValue("resumeId", experience.getResumeId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<WorkExperienceInfo> findByResumeId(Long resumeId) {
        String sql = "SELECT * FROM work_experience_info WHERE resume_id = :resumeId ORDER BY id";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("resumeId", resumeId),
                new BeanPropertyRowMapper<>(WorkExperienceInfo.class)
        );
    }

    public void deleteByResumeId(Long resumeId) {
        String sql = "DELETE FROM work_experience_info WHERE resume_id = :resumeId";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource("resumeId", resumeId)
        );
    }
}