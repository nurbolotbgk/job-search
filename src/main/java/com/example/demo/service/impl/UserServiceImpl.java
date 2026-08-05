package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream()
                .map(e -> UserDto.builder()
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
        List<User> users = userDao.findUsersByName(name);
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream()
                .map(e -> UserDto.builder()
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
    public List<UserDto> getApplicantsForVacancy(Long vacancyId) {
        List<User> users = userDao.getApplicantsForVacancy(vacancyId);
        if (users.isEmpty()) {
            throw new UserNotFoundException();
        }

        return users.stream()
                .map(e -> UserDto.builder()
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
    public UserDto findUserById(Long id) {
        User user = userDao.findUserById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
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
    public UserDto findEmployerById(Long id) {
        User user = userDao.findEmployerById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
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
    public UserDto findApplicantById(Long id) {
        User user = userDao.findApplicantById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
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
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar(dto.getAvatar());
        user.setRoleId(dto.getRoleId());
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public void update(Long id, UserDto dto) {
        User user = new User();
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

    @Override
    public ResponseEntity<?> download(String filename) {
        try {
            byte[] file = Files.readAllBytes(Paths.get("data/images/" + filename));

            Resource resource = new ByteArrayResource(file);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .contentLength(resource.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        }
    }

    @Override
    public String upload(MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String resultFilename = uuid + "_" + file.getOriginalFilename();

        try {
            Path pathDir = Paths.get("data/images");
            Files.createDirectories(pathDir);

            Path filePath = Paths.get(pathDir + "/" + resultFilename);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            try (OutputStream out = Files.newOutputStream(filePath)) {
                out.write(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultFilename;
    }
}
