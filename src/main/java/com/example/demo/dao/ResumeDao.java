package com.example.demo.dao;

import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    public Optional<Resume> findResumeById(Long id) {
        String sql = "SELECT * FROM resumes WHERE id = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult
                (jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), id)));
    }

    public List<Resume> getResumesMadeByUser(Long userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = ?";
        return
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Resume.class), userId);
    }

    public Long save(Resume resume) {
        String sql = "INSERT INTO resumes (name, salary, active, created_date, update_time, user_id, category_id) " +
                "VALUES (:name, :salary,:active, :createdDate, :updateTime, :userId, :categoryId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", resume.getName())
                .addValue("salary", resume.getSalary())
                .addValue("active", resume.getActive())
                .addValue("createdDate", resume.getCreatedDate())
                .addValue("updateTime", resume.getUpdateTime())
                .addValue("userId", resume.getUserId())
                .addValue("categoryId", resume.getCategoryId());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(
                sql,
                params,
                keyHolder,
                new String[]{"id"}
        );

        Number key = keyHolder.getKey();

        if (key == null) {
            throw new IllegalStateException(
                    "Не удалось получить id созданного резюме"
            );
        }

        return key.longValue();
    }

    public void update(Resume resume) {
        String sql = "UPDATE resumes SET name = :name, salary = :salary, active = :active, " +
                "update_time = :update_time, user_id = (select id from users where id = :user_id), category_id = :category_id WHERE id = :id;";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("id", resume.getId())
                        .addValue("name", resume.getName())
                        .addValue("salary", resume.getSalary())
                        .addValue("active", resume.getActive())
                        .addValue("update_time", LocalDateTime.now())
                        .addValue("category_id", resume.getCategoryId())
                        .addValue("user_id", resume.getUserId())
        );
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM resumes WHERE id = :id";

        int num = namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource().addValue("id", id)
        );
        if (num == 0) {
            throw new ResumeNotFoundException();
        }
    }
}