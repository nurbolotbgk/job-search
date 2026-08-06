package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("employers/{id}")
    public UserDto findEmployerById(@PathVariable Long id) {
        return userService.findEmployerById(id);
    }



    @GetMapping("/findUsersByName/{name}")
    public List<UserDto> findUsersByName(@PathVariable String name) {
        return userService.findUsersByName(name);
    }

    @GetMapping("/findApplicantById/{id}")
    public UserDto findApplicantById(@PathVariable Long id) {
        return userService.findApplicantById(id);
    }

    @GetMapping("/phone/{phoneNumber}")
    public UserDto findUserByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
    }

    @GetMapping("/applicantsForVacancy/{vacancyId}")
    public List<UserDto> getApplicantsForVacancy(@PathVariable Long vacancyId) {
        return userService.getApplicantsForVacancy(vacancyId);
    }


    @GetMapping("/email/{email}")
    public UserDto findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/check-email")
    public String checkByEmail(@RequestParam String email) {
        boolean isExists = userService.emailExistsOrNot(email);
        if (isExists) {
            return "Такой пользователь есть";
        } else {
            return "Такого пользователя нет";
        }
    }

    @GetMapping("download")
    public ResponseEntity<?> download(@RequestParam(name = "filename") String filename) {
        return userService.download(filename);
    }

    @PostMapping("upload")
    public String upload(MultipartFile file) {
        return userService.upload(file);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto dto) {
        userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @Valid  @RequestBody UserDto dto) {
        userService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
