package com.example.demo.dao;

import com.example.demo.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContactInfoDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(ContactInfo contactInfo) {
        String sql = "INSERT INTO contacts_info (contact_value, type_id, resume_id) " +
                "VALUES (:contactValue,:typeId,:resumeId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("contactValue", contactInfo.getContactValue())
                .addValue("typeId", contactInfo.getTypeId())
                .addValue("resumeId", contactInfo.getResumeId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<ContactInfo> findByResumeId(Long resumeId) {
        String sql = "SELECT * FROM contacts_info WHERE resume_id = :resumeId";

        return namedParameterJdbcTemplate.query(
                sql,
                new MapSqlParameterSource("resumeId", resumeId),
                new BeanPropertyRowMapper<>(ContactInfo.class)
        );
    }

    public void deleteByResumeId(Long resumeId) {
        String sql = "DELETE FROM contacts_info WHERE resume_id = :resumeId";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource("resumeId", resumeId)
        );
    }
}