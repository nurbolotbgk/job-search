package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.dto.VacancyFormDto;
import com.example.demo.exception.VacancyNotFoundException;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacController {

    private final VacancyService vacancyService;
    private final UserService userService;
    private final CategoryService categoryService;

    @GetMapping
    public String getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "date_desc") String sort, Model model) {

        Page<VacancyDto> vacancies = vacancyService.getAllVacancies(page, 5, sort);

        model.addAttribute("vacancies", vacancies.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", vacancies.getTotalPages());

        model.addAttribute("sort", sort);

        return "vacancies/vacancy_list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        VacancyFormDto vacancyFormDto = new VacancyFormDto();
        vacancyFormDto.setActive(true);

        model.addAttribute("vacancyFormDto", vacancyFormDto);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "vacancies/create_vacancy";
    }

    @PostMapping("/create")
    public String create(@Valid VacancyFormDto vacancyFormDto, BindingResult bindingResult, Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            return "vacancies/create_vacancy";
        }

        UserDto currentUser = userService.getCurrentUser();

        VacancyDto vacancyDto = VacancyDto.builder()
                .name(vacancyFormDto.getName())
                .description(vacancyFormDto.getDescription())
                .categoryId(vacancyFormDto.getCategoryId())
                .salary(vacancyFormDto.getSalary())
                .expFrom(vacancyFormDto.getExpFrom())
                .expTo(vacancyFormDto.getExpTo())
                .active(
                        vacancyFormDto.getActive() != null
                                && vacancyFormDto.getActive()
                )
                .userId(currentUser.getId())
                .build();

        vacancyService.save(vacancyDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {

        VacancyDto vacancy = vacancyService.findById(id);
        UserDto currentUser = userService.getCurrentUser();

        if (!vacancy.getUserId().equals(currentUser.getId())) {
            throw new VacancyNotFoundException();
        }

        VacancyFormDto vacancyFormDto = VacancyFormDto.builder()
                .name(vacancy.getName())
                .description(vacancy.getDescription())
                .categoryId(vacancy.getCategoryId())
                .salary(vacancy.getSalary())
                .expFrom(vacancy.getExpFrom())
                .expTo(vacancy.getExpTo())
                .active(vacancy.getActive())
                .build();

        model.addAttribute("vacancyFormDto", vacancyFormDto);
        model.addAttribute("vacancyId", id);
        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "vacancies/edit_vacancy";
    }

    @PostMapping("/{id}/edit")
    public String edit(
            @PathVariable Long id,
            @Valid VacancyFormDto vacancyFormDto,
            BindingResult bindingResult,
            Model model
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyId", id);
            model.addAttribute("categories", categoryService.getAllCategories());

            return "vacancies/edit_vacancy";
        }

        VacancyDto oldVacancy = vacancyService.findById(id);
        UserDto currentUser = userService.getCurrentUser();

        if (!oldVacancy.getUserId().equals(currentUser.getId())) {
            throw new VacancyNotFoundException();
        }

        VacancyDto vacancyDto = VacancyDto.builder()
                .id(id)
                .name(vacancyFormDto.getName())
                .description(vacancyFormDto.getDescription())
                .categoryId(vacancyFormDto.getCategoryId())
                .salary(vacancyFormDto.getSalary())
                .expFrom(vacancyFormDto.getExpFrom())
                .expTo(vacancyFormDto.getExpTo())
                .active(
                        vacancyFormDto.getActive() != null
                                && vacancyFormDto.getActive()
                )
                .userId(currentUser.getId())
                .build();

        vacancyService.update(id, vacancyDto);

        return "redirect:/profile";
    }

    @GetMapping("/{id}")
    public String vacancyDetails(
            @PathVariable Long id,
            Model model
    ) {

        VacancyDto vacancy =
                vacancyService.findById(id);

        UserDto employer =
                userService.findEmployerById(
                        vacancy.getUserId()
                );

        model.addAttribute("vacancy", vacancy);
        model.addAttribute("employer", employer);

        return "vacancies/vacancy_details";
    }

    @PostMapping("/{id}/update-time")
    public String updateTime(@PathVariable Long id) {

        UserDto currentUser = userService.getCurrentUser();
        vacancyService.updateTime(id, currentUser.getId());

        return "redirect:/profile";
    }

    @PostMapping("/{id}/toggle-active")
    public String toggleActive(@PathVariable Long id) {

        UserDto currentUser = userService.getCurrentUser();
        vacancyService.toggleActive(id, currentUser.getId());

        return "redirect:/profile";
    }
}