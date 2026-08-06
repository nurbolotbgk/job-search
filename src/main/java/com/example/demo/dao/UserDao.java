package com.example.demo.dao;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
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
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List <User> getAllUsers() {
        String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public List <User> getApplicantsForVacancy(Long vacancyId) {
        String sql = "SELECT DISTINCT u.* FROM users u " +
                "INNER JOIN PUBLIC.RESUMES r " +
                "             ON r.USER_ID = u.ID " +
                "INNER JOIN PUBLIC.RESPONDED_APPLICANTS ra " +
                "ON ra.RESUME_ID = r.id " +
                "WHERE u.ROLE_ID = 1 AND ra.VACANCY_ID = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), vacancyId);
    }

    public Optional<User> findUserById(Long id) {
        String sql = "SELECT * FROM users Where id = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),id)
        ));
    }

    public Optional<User> findEmployerById(Long id) {
        String sql = "SELECT * FROM users Where ROLE_ID = 2 AND id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),id)
        ));
    }

    public Optional<User> findApplicantById(Long id) {
        String sql = "SELECT * FROM users u Where u.ROLE_ID = 1 AND id = ?";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),id)
        ));
    }

    public List <User> findUsersByName(String name) {
        String sql = "SELECT * FROM users Where name = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),name);
    }

    public Optional<User> getUserByPhoneNum(String phoneNumber) {
        String sql = "SELECT * FROM users WHERE phone_number = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), phoneNumber)
        ));

    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), email)
                ));
    }

    public boolean userExistsOrNot(String email) {
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    public void save(User user) {
        String sql = "INSERT INTO users (name, surname, age, email, password, phone_number, avatar, role_id, enabled) " +
                "VALUES (:name, :surname, :age, :email, :password, :phoneNumber, :avatar, :roleId, :enabled)";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", user.getName())
                        .addValue("surname", user.getSurname())
                        .addValue("age", user.getAge())
                        .addValue("email", user.getEmail())
                        .addValue("password", user.getPassword())
                        .addValue("phoneNumber", user.getPhoneNumber())
                        .addValue("avatar", user.getAvatar())
                        .addValue("roleId", user.getRoleId())
                        .addValue("enabled", user.getEnabled())
        );
    }

    public void update(User user) {
        String sql = "UPDATE users SET " +
                "name = :name, " +
                "surname = :surname, " +
                "age = :age, " +
                "email = :email, " +
                //"password = :password, " +
                "phone_number = :phoneNumber, " +
                "avatar = :avatar " +
                //"role_id = :roleId " +
                "WHERE id = :id";

        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("id", user.getId())
                        .addValue("name", user.getName())
                        .addValue("surname", user.getSurname())
                        .addValue("age", user.getAge())
                        .addValue("email", user.getEmail())
                        //.addValue("password", user.getPassword())
                        .addValue("phoneNumber", user.getPhoneNumber())
                        .addValue("avatar", user.getAvatar())
                        //.addValue("roleId", user.getRoleId())
        );
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";

        int num = namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource().addValue("id", id)
        );

        if (num == 0) {
            throw new UserNotFoundException();
        }
    }
}
