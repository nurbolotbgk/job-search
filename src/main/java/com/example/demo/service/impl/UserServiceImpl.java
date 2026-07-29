package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
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
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .surname(e.getSurname())
                        .age(e.getAge())
                        .email(e.getEmail())
                        .password(e.getPassword())
                        .avatar(e.getAvatar())
                        .roleId(e.getRoleId())
                        .build()
                )
                .toList();
    }


    @Override
    public List<UserDto> findUsersByName(String name) {
        List<User> users = userDao.findUserByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .surname(e.getSurname())
                        .age(e.getAge())
                        .email(e.getEmail())
                        .password(e.getPassword())
                        .avatar(e.getAvatar())
                        .roleId(e.getRoleId())
                        .build()
                )
                .toList();
    }

    @Override
    public UserDto findUserById(Integer id) {
        User user = userDao.findUserById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .roleId(user.getRoleId())
                .build();
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        User user = userDao.getUserByPhoneNum(phoneNumber)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .roleId(user.getRoleId())
                .build();
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userDao.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .avatar(user.getAvatar())
                .roleId(user.getRoleId())
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

    @Override
    public void save(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar(dto.getAvatar());
        user.setRoleId(dto.getRoleId());
        userDao.save(user);
    }

    @Override
    public void update(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar(dto.getAvatar());
        user.setRoleId(dto.getRoleId());
        userDao.update(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
