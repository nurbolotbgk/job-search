package com.example.demo.controller;

import com.example.demo.dto.RespondedApplicantDto;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Vacancy;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("applicants")
@RequiredArgsConstructor
public class RespondedApplicantController {
    private final List<Vacancy> vacancyStorage = new ArrayList<>();
    private final RespondedApplicantService respondedApplicantService;
    private final List<RespondedApplicant> responseStorage = new ArrayList<>();


    @GetMapping
    public List<RespondedApplicantDto> getAllUsers() {
        return respondedApplicantService.getAllRespondedApplicants();
    }

    @PostMapping("create")
    public HttpStatus createResume(ResumeDto dto) {
        System.out.println(dto.toString());
        return HttpStatus.OK;
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateResume(@RequestParam Integer id, @RequestBody ResumeDto dto) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{vacancyId}/responses")
    public ResponseEntity<List<RespondedApplicant>> getResponsesForVacancy(@PathVariable Integer vacancyId) {
        return ResponseEntity.ok(responseStorage);
    }

}
