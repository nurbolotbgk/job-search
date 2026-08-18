package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final UserService userService;

    @GetMapping
    public String getCompanies(@RequestParam(defaultValue = "0") int page, Model model) {

        Page<UserDto> companies = userService.getCompanies(page, 5);

        model.addAttribute("companies", companies.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages", companies.getTotalPages());

        return "companies/company_list";
    }
}
