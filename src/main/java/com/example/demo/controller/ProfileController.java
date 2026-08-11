package com.example.demo.controller;

import com.example.demo.dto.EditUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        EditUserDto editUserDto = EditUserDto.builder()
                .name(currentUser.getName())
                .surname(currentUser.getSurname())
                .age(currentUser.getAge())
                .phoneNumber(currentUser.getPhoneNumber())
                .build();

        model.addAttribute("editUserDto", editUserDto);
        return "profile/edit_profile";
    }


    @PostMapping("/edit")
    public String edit(@Valid EditUserDto editUserDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "profile/edit_profile";
        }

        UserDto currentUser = userService.getCurrentUser();
        userService.update(currentUser.getId(), editUserDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String profileById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "profile/profile";
    }

}
