package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    UserDto findUserById(Integer id);

    UserDto findByPhoneNumber(String phoneNumber);

    UserDto findUserByEmail(String email);

    boolean emailExistsOrNot(String email);

    void save(UserDto dto);

    void update(UserDto dto);

    void deleteById(Long id);
}
