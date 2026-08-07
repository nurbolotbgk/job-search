package com.example.demo.controller;

import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacController {
    private final VacancyService vacancyService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllVacancies());
        return "vacancies/vacancy_list";
    }

}
