package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final ImageService imageService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;
    private final RespondedApplicantService respondedApplicantService;


    @GetMapping
    public String profile(
            @RequestParam(defaultValue = "0") int page,
            Model model,
            HttpSession session
    ) {

        UserDto currentUser = userService.getCurrentUser();

        model.addAttribute("user", currentUser);

        EditUserDto editUserDto = EditUserDto.builder()
                .name(currentUser.getName())
                .surname(currentUser.getSurname())
                .age(currentUser.getAge())
                .phoneNumber(currentUser.getPhoneNumber())
                .avatar(currentUser.getAvatar())
                .build();

        model.addAttribute("editUserDto", editUserDto);


        if (currentUser.getRoleId() == 1) {

            Page<ResumeDto> resumes =
                    resumeService.getResumesMadeByUser(
                            currentUser.getId(),
                            page,
                            5
                    );

            model.addAttribute("resumes", resumes.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resumes.getTotalPages());


            List<RespondedApplicantDto> responses =
                    respondedApplicantService.getResponsesByUserId(
                            currentUser.getId()
                    );

            model.addAttribute("responses", responses);

        } else if (currentUser.getRoleId() == 2) {

            Page<VacancyDto> vacancies =
                    vacancyService.getVacanciesByUserId(
                            currentUser.getId(),
                            page,
                            5
                    );

            model.addAttribute("vacancies", vacancies.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", vacancies.getTotalPages());
        }

        return "profile/profile";
    }


    @PostMapping("/edit")
    public String edit(
            @Valid EditUserDto editUserDto,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile file,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {

        UserDto currentUser = userService.getCurrentUser();

        if (bindingResult.hasErrors()) {

            model.addAttribute("user", currentUser);
            model.addAttribute("openEditModal", true);


            if (currentUser.getRoleId() == 1) {

                Page<ResumeDto> resumes =
                        resumeService.getResumesMadeByUser(
                                currentUser.getId(),
                                page,
                                5
                        );

                model.addAttribute("resumes", resumes.getContent());
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", resumes.getTotalPages());


                List<RespondedApplicantDto> responses =
                        respondedApplicantService.getResponsesByUserId(
                                currentUser.getId()
                        );

                model.addAttribute("responses", responses);

            } else if (currentUser.getRoleId() == 2) {

                Page<VacancyDto> vacancies =
                        vacancyService.getVacanciesByUserId(
                                currentUser.getId(),
                                page,
                                5
                        );

                model.addAttribute("vacancies", vacancies.getContent());
                model.addAttribute("currentPage", page);
                model.addAttribute("totalPages", vacancies.getTotalPages());
            }

            return "profile/profile";
        }


        if (!file.isEmpty()) {

            String fileName = imageService.save(file);
            editUserDto.setAvatar(fileName);

        } else {

            editUserDto.setAvatar(currentUser.getAvatar());
        }


        userService.update(
                currentUser.getId(),
                editUserDto
        );

        return "redirect:/profile";
    }


    @GetMapping("/{id}")
    public String profileById(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "user",
                userService.findUserById(id)
        );

        return "profile/profile";
    }
}