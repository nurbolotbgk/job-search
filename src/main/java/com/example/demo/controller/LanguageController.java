package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LanguageController {

    private final UserService userService;

    @GetMapping("/language")
    public String changeLanguage(@RequestParam String lang, @RequestParam(defaultValue = "/") String redirect, Authentication authentication) {

        if (!lang.equals("ru") && !lang.equals("en")) {
            lang = "ru";
        }

        if (authentication != null && authentication.isAuthenticated()) {
            userService.updateLanguage(authentication.getName(), lang);
        }

        return "redirect:" + redirect + "?lang=" + lang;
    }
}