package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();

        return users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .surname(e.getSurname())
                        .age(e.getAge())
                        .email(e.getEmail())
                        .password(e.getPassword())
                        .avatar(e.getAvatar())
                        .role_id(e.getRole_id())
                        .build()
                )
                .toList();

    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userDao.findUserById(id)
                .orElseThrow(RuntimeException::new);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone_number(user.getPhone_number())
                .avatar(user.getAvatar())
                .role_id(user.getRole_id())
                .build();
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        User user = userDao.getUserByPhoneNum(phoneNumber)
                .orElseThrow(RuntimeException::new);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone_number(user.getPhone_number())
                .avatar(user.getAvatar())
                .role_id(user.getRole_id())
                .build();
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userDao.findByEmail(email).orElseThrow(RuntimeException::new);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone_number(user.getPhone_number())
                .avatar(user.getAvatar())
                .role_id(user.getRole_id())
                .build();

    }

    @Override
    public boolean emailExistsOrNot(String email) {
        if (userDao.userExistsOrNot(email)) {
            return true;
        } else {
            return false;
        }
    }
}
