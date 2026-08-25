package com.example.demo.controller;

import com.example.demo.dto.EditUserDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.service.ImageService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

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
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profile(@RequestParam(defaultValue = "0") int page, Model model, HttpSession session) {

        UserDto currentUser = userService.getCurrentUser();

        model.addAttribute("user", currentUser);

        if (currentUser.getRoleId() == 1) {

            Page<ResumeDto> resumes = resumeService.getResumesMadeByUser(currentUser.getId(), page, 5);

            model.addAttribute("resumes", resumes.getContent());

            model.addAttribute("currentPage", page);

            model.addAttribute("totalPages", resumes.getTotalPages());

        } else if (currentUser.getRoleId() == 2) {
            Page<VacancyDto> vacancies = vacancyService.getVacanciesByUserId(currentUser.getId(), page, 5);

            model.addAttribute("vacancies", vacancies.getContent());

            model.addAttribute("currentPage", page);

            model.addAttribute("totalPages", vacancies.getTotalPages());
        }

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
        userService.update(currentUser.getId(), editUserDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String profileById(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "profile/profile";
    }


}
