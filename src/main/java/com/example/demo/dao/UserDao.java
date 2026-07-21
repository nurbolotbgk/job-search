package com.example.demo.dao;

import com.example.demo.model.Resume;
import com.example.demo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public List <User> getAllUsers() {
        String sql = "SELECT * FROM users;";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findUserById(Integer id) {
        String sql = "SELECT * FROM users Where id = ?;";
        return Optional.ofNullable(DataAccessUtils.singleResult(
                jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),id)
        ));
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
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE email = ?;)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }


}
