package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping
    public String profile(Model model) {
        model.addAttribute("user", userService.findUserById(3L));
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("user", userService.findUserById(3L));
        return "profile/edit_profile";
    }

    @PostMapping("/edit")
    public String edit(UserDto userDto) {
        userService.update(3L, userDto);

        return "redirect:/profile";
    }

}
