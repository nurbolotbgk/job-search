package com.example.demo.controller;


import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.ResumeFormDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("resumes", resumeService.getAllResumes());
        return "resumes/resume_list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        ResumeFormDto resumeFormDto = new ResumeFormDto();
        resumeFormDto.setActive(true);

        model.addAttribute("resumeFormDto", resumeFormDto);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "resumes/create_resume";
    }
    @PostMapping("/create")
    public String create(@Valid ResumeFormDto resumeFormDto, BindingResult bindingResult, Model model
    ) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            return "resumes/create_resume";
        }

        UserDto currentUser = userService.getCurrentUser();

        ResumeDto resumeDto = ResumeDto.builder()
                .userId(currentUser.getId())
                .name(resumeFormDto.getName())
                .categoryId(resumeFormDto.getCategoryId())
                .salary(resumeFormDto.getSalary())
                .active(
                        resumeFormDto.getActive() != null
                                && resumeFormDto.getActive()
                )
                .workExperiences(List.of())
                .educations(List.of())
                .contacts(List.of())
                .build();

        resumeService.save(resumeDto);

        return "redirect:/resumes";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Long id, Model model
    ) {
        ResumeDto resume = resumeService.findResumeById(id);
        UserDto currentUser = userService.getCurrentUser();

        if (!resume.getUserId().equals(currentUser.getId())) {
            throw new ResumeNotFoundException();
        }

        ResumeFormDto resumeFormDto = ResumeFormDto.builder()
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .salary(resume.getSalary())
                .active(resume.getActive())
                .build();

        model.addAttribute("resumeFormDto", resumeFormDto);
        model.addAttribute("resumeId", id);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "resumes/edit_resume";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @Valid ResumeFormDto resumeFormDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeId", id);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "resumes/edit_resume";
        }

        ResumeDto oldResume = resumeService.findResumeById(id);
        UserDto currentUser = userService.getCurrentUser();

        if (!oldResume.getUserId().equals(currentUser.getId())) {
            throw new ResumeNotFoundException();
        }

        ResumeDto resumeDto = ResumeDto.builder()
                .id(id)
                .userId(currentUser.getId())
                .name(resumeFormDto.getName())
                .categoryId(resumeFormDto.getCategoryId())
                .salary(resumeFormDto.getSalary())
                .active(resumeFormDto.getActive() != null && resumeFormDto.getActive())
                .workExperiences(oldResume.getWorkExperiences())
                .educations(oldResume.getEducations())
                .contacts(oldResume.getContacts())
                .build();

        resumeService.update(resumeDto);

        return "redirect:/resumes";
    }

}
