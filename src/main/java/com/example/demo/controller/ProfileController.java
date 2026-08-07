package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        UserDto currentUser = userService.getCurrentUser();
        model.addAttribute("user", userService.findUserByEmail(currentUser.getEmail()));
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        UserDto currentUser = userService.getCurrentUser();
        model.addAttribute("user", userService.findUserByEmail(currentUser.getEmail()));
        return "profile/edit_profile";
    }

    @PostMapping("/edit")
    public String edit(UserDto userDto) {

        UserDto currentUser = userService.getCurrentUser();
        userService.update(currentUser.getId(), userDto);

        return "redirect:/profile";
    }

}
