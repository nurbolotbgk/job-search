package com.example.demo.controller;


import com.example.demo.dto.*;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAll(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<ResumeDto> resumes = resumeService.getAllResumes(page, 5);

        model.addAttribute("resumes", resumes.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", resumes.getTotalPages());

        return "resumes/resume_list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        ResumeFormDto resumeFormDto = new ResumeFormDto();

        resumeFormDto.setActive(true);

        resumeFormDto.setWorkExperiences(
                new ArrayList<>(List.of(new WorkExperienceInfoDto()))
        );

        resumeFormDto.setEducations(
                new ArrayList<>(List.of(new EducationInfoDto()))
        );

        resumeFormDto.setContacts(
                new ArrayList<>(List.of(new ContactInfoDto()))
        );

        model.addAttribute("resumeFormDto", resumeFormDto);
        model.addAttribute("categories", categoryService.getAllCategories()
        );

        return "resumes/create_resume";
    }


    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> create(@Valid @RequestBody ResumeFormDto resumeFormDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            List<String> errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .toList();

            return ResponseEntity
                    .badRequest()
                    .body(errors);
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
                .workExperiences(
                        resumeFormDto.getWorkExperiences() != null
                                ? resumeFormDto.getWorkExperiences()
                                : List.of()
                )
                .educations(
                        resumeFormDto.getEducations() != null
                                ? resumeFormDto.getEducations()
                                : List.of()
                )
                .contacts(
                        resumeFormDto.getContacts() != null
                                ? resumeFormDto.getContacts()
                                : List.of()
                )
                .build();

        resumeService.save(resumeDto);

        return ResponseEntity.ok().build();
    }



    @GetMapping("/{id}/edit")
    public String editPage(
            @PathVariable Long id,
            Model model
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
                .workExperiences(resume.getWorkExperiences())
                .educations(resume.getEducations())
                .contacts(resume.getContacts())
                .build();

        model.addAttribute("resumeFormDto", resumeFormDto);
        model.addAttribute("resumeId", id);
        model.addAttribute("categories", categoryService.getAllCategories()
        );

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
                .active(
                        resumeFormDto.getActive() != null
                                && resumeFormDto.getActive()
                )
                .workExperiences(
                        resumeFormDto.getWorkExperiences() != null
                                ? resumeFormDto.getWorkExperiences()
                                : List.of()
                )
                .educations(
                        resumeFormDto.getEducations() != null
                                ? resumeFormDto.getEducations()
                                : List.of()
                )
                .contacts(
                        resumeFormDto.getContacts() != null
                                ? resumeFormDto.getContacts()
                                : List.of()
                )
                .build();

        resumeService.update(resumeDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String resumeDetails(@PathVariable Long id, Model model) {
        ResumeDto resume = resumeService.findResumeById(id);
        UserDto applicant = userService.findUserById(resume.getUserId());

        model.addAttribute("resume", resume);
        model.addAttribute("applicant", applicant);

        return "resumes/resume_details";
    }


    @PostMapping("/{id}/edit/add-experience")
    public String addExperienceEdit(
            @PathVariable Long id,
            @ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto,
            Model model
    ) {

        if (resumeFormDto.getWorkExperiences() == null) {
            resumeFormDto.setWorkExperiences(new ArrayList<>());
        }

        resumeFormDto.getWorkExperiences()
                .add(new WorkExperienceInfoDto());

        model.addAttribute("resumeId", id);
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "resumes/edit_resume";
    }


    @PostMapping("/{id}/edit/add-education")
    public String addEducationEdit(
            @PathVariable Long id,
            @ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto,
            Model model
    ) {

        if (resumeFormDto.getEducations() == null) {
            resumeFormDto.setEducations(new ArrayList<>());
        }

        resumeFormDto.getEducations()
                .add(new EducationInfoDto());

        model.addAttribute("resumeId", id);
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "resumes/edit_resume";
    }


    @PostMapping("/{id}/edit/add-contact")
    public String addContactEdit(@PathVariable Long id, @ModelAttribute("resumeFormDto") ResumeFormDto resumeFormDto,
                                 Model model
    ) {

        if (resumeFormDto.getContacts() == null) {
            resumeFormDto.setContacts(new ArrayList<>());
        }

        resumeFormDto.getContacts()
                .add(new ContactInfoDto());

        model.addAttribute("resumeId", id);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "resumes/edit_resume";
    }

    @PostMapping("/{id}/update-time")
    public String updateTime(@PathVariable Long id) {
        UserDto currentUser = userService.getCurrentUser();
        resumeService.updateTime(id, currentUser.getId());
        return "redirect:/profile";
    }

    @PostMapping("/{id}/toggle-active")
    public String toggleActive(@PathVariable Long id) {
        UserDto currentUser = userService.getCurrentUser();
        resumeService.toggleActive(id, currentUser.getId());
        return "redirect:/profile";
    }
}
