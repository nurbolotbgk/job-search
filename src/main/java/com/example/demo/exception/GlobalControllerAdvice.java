package com.example.demo.exception;

import com.example.demo.service.ErrorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
//@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final ErrorService errorService;


    @ExceptionHandler(UserNotFoundException.class)
    public String userNotFound(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());

        return "errors/error";
    }

    @ExceptionHandler(VacancyNotFoundException.class)
    public String vacancyNotFound(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", "Вакнсия не найдена");
        model.addAttribute("details", request.getRequestURI());

        return "errors/error";
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public String resumeNotFound(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", "Резюме не найдено");
        model.addAttribute("details", request.getRequestURI());

        return "errors/error";
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public String categoryNotFound(HttpServletRequest request, Model model) {
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());

        model.addAttribute("reason", "Категория не найдена");
        model.addAttribute("details", request.getRequestURI());

        return "errors/error";
    }

//    @ExceptionHandler(CustomerNotFoundException.class)
//    private ResponseEntity<ErrorResponseBody> noSuchCustomerHandler(CustomerNotFoundException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e), HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    private ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
//        return new ResponseEntity<>(errorService.makeResponse(e.getBindingResult()), HttpStatus.BAD_REQUEST);
//    }
}
