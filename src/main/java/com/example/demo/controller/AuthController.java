package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }



    @PostMapping("/register")
    public String register(@Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }
        String password = userDto.getPassword();

        userService.save(userDto);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), password));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);

        new HttpSessionSecurityContextRepository().saveContext(context, request, response);


        return "redirect:/profile";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "auth/forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {

        try {
            userService.makeResetPasswdLink(request);
            model.addAttribute("message","Ссылка для восстановления отправлена на вашу почту");

        } catch (UsernameNotFoundException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getMessage());

        } catch (MessagingException ex) {
            model.addAttribute("error", "Ошибка при отправке письма");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UsernameNotFoundException ex) {
            model.addAttribute("error", "Invalid token");
        }

        return "auth/reset_password_form";
    }


    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {

        String token = request.getParameter("token");
        String password = request.getParameter("password");

        try {
            User user = userService.getByResetPasswordToken(token);

            userService.updatePassword(user, password);

            model.addAttribute(
                    "message",
                    "Пароль успешно изменён"
            );

        } catch (UsernameNotFoundException ex) {

            model.addAttribute("error", "Неверный или устаревший токен");
            model.addAttribute("token", token);
            return "auth/reset_password_form";
        }

        return "auth/login";
    }
}
