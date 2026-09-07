package com.example.demo.service.impl;

import com.example.demo.dto.EditUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.util.Utility;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();;
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
                        .roleId(e.getRole().getId().intValue())
                        .build()
                )
                .toList();
    }


    @Override
    public List<UserDto> findUsersByName(String name) {
        List<User> users = userRepository.findByName(name);

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
                        .phoneNumber(e.getPhoneNumber())
                        .avatar(e.getAvatar())
                        .roleId(e.getRole().getId().intValue())
                        .build())
                .toList();
    }

    @Override
    public List<UserDto> getApplicantsForVacancy(Long vacancyId) {
        List<User> users =
                userRepository.findApplicantsForVacancy(vacancyId);

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
                        .phoneNumber(e.getPhoneNumber())
                        .avatar(e.getAvatar())
                        .roleId(e.getRole().getId().intValue())
                        .build())
                .toList();
    }

    @Override
    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id)
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
                .roleId(user.getRole().getId().intValue())
                .build();
    }

    @Override
    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDto findEmployerById(Long id) {
        User user = userRepository.findByIdAndRole_Id(id, 2)
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
                .roleId(user.getRole().getId().intValue())
                .build();
    }

    @Override
    public UserDto findApplicantById(Long id) {
        User user = userRepository.findByIdAndRole_Id(id, 1)
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
                .roleId(user.getRole().getId().intValue())
                .build();
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber)
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
                .roleId(user.getRole().getId().intValue())
                .build();
    }

    @Override
    public UserDto getCurrentUser() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return findUserByEmail(email);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
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
                .roleId(user.getRole().getId().intValue())
                .build();
    }

    @Override
    public boolean emailExistsOrNot(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(UserDto dto) {

        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow();

        User user = new User();

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar(dto.getAvatar());
        user.setRole(role);
        user.setEnabled(true);

        user.setLanguage(LocaleContextHolder.getLocale().getLanguage());

        userRepository.save(user);
    }

    @Override
    public void update(Long id, EditUserDto dto) {

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setAvatar(dto.getAvatar());

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
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

    @Override
    public Page<UserDto> getCompanies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<User> companies = userRepository.findByRole_Id(2, pageable);

        return companies.map(u -> UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .avatar(u.getAvatar())
                .roleId(u.getRole().getId())
                .build());
    }

    @Override
    public void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Пользователь с email " + email + " не найден"));

        user.setResetPasswordToken(token);
        userRepository.save(user);
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Пользователь не найден"
                        ));
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        String encodedPassword = encoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }

    @Override
    public void makeResetPasswdLink(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);
        String resetPasswordLink = Utility.getSiteURL(request) + "/auth/reset_password?token=" + token;
        emailService.sendEmail(email, resetPasswordLink);
    }

    @Override
    public void updateLanguage(String email, String language) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.setLanguage(language);
        userRepository.save(user);
    }

    @Override
    public String getLanguageByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return user.getLanguage();
    }
}
