package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")  // http://localhost:8089/main
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/phone/{phoneNumber}")
    public UserDto findUserByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
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

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto dto) {
        userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Void> updateUser(@Valid @PathVariable Long id, @RequestBody UserDto dto) {
        dto.setId(id);
        userService.update(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
