package com.example.demo.controller.api;

import com.example.demo.dto.RespondedApplicantDto;
import com.example.demo.service.RespondedApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("responded-applicants")
@RequiredArgsConstructor
public class RespondedApplicantController {

    private final RespondedApplicantService respondedApplicantService;


    @PostMapping("/createResponse")
    public ResponseEntity<Void> createUser(@Valid @RequestBody RespondedApplicantDto dto) {
        respondedApplicantService.createResponse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




}
