package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();

    List<UserDto> findUsersByName(String name);

    List<UserDto> getApplicantsForVacancy(Long vacancyId);


    UserDto findUserById(Long id);

    UserDto findEmployerById(Long id);

    UserDto findApplicantById(Long id);

    UserDto findByPhoneNumber(String phoneNumber);

    UserDto findUserByEmail(String email);

    boolean emailExistsOrNot(String email);

    void save(UserDto dto);

    void update(Long id, UserDto dto);

    void deleteById(Long id);

    ResponseEntity<?> download(String filename);

    String upload(MultipartFile file);
}
