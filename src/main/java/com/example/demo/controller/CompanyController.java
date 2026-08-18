package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.VacancyDto;
import com.example.demo.service.UserService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final UserService userService;
    private final VacancyService vacancyService;

    @GetMapping
    public String getCompanies(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<UserDto> companies = userService.getCompanies(page, 5);

        model.addAttribute("companies", companies.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", companies.getTotalPages());

        return "companies/company_list";
    }

    @GetMapping("/{id}")
    public String getCompany(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            Model model
    ) {

        UserDto company = userService.findEmployerById(id);

        Page<VacancyDto> vacancies = vacancyService.getVacanciesByUserId(id, page, 5);

        model.addAttribute("company", company);
        model.addAttribute("vacancies", vacancies.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacancies.getTotalPages());

        return "companies/company_details";
    }
}
