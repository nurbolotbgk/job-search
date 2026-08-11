package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }



    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        userService.save(userDto);
        return "auth/login";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
