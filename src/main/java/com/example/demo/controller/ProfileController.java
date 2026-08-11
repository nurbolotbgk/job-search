package com.example.demo.controller;

import com.example.demo.dto.EditUserDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.ImageService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    private final ImageService imageService;

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
                .avatar(currentUser.getAvatar())
                .build();

        model.addAttribute("editUserDto", editUserDto);
        return "profile/edit_profile";
    }


    @PostMapping("/edit")
    public String edit(@Valid EditUserDto editUserDto, BindingResult bindingResult, @RequestParam("file") MultipartFile file) {

        bindingResult.getFieldErrors()
                .forEach(error ->
                        System.out.println(
                                "FIELD: " + error.getField()
                                        + " | VALUE: " + error.getRejectedValue()
                                        + " | MESSAGE: " + error.getDefaultMessage()
                        )
                );

        if (bindingResult.hasErrors()) {
            return "profile/edit_profile";
        }

        UserDto currentUser = userService.getCurrentUser();

        if (!file.isEmpty()) {
            String fileName = imageService.save(file);
            editUserDto.setAvatar(fileName);
        } else {
            editUserDto.setAvatar(currentUser.getAvatar());
        }
        System.out.println("AVATAR: " + editUserDto.getAvatar());
        userService.update(currentUser.getId(), editUserDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String profileById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "profile/profile";
    }

}
