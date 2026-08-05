package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {
    private final List<Resume> storage = new ArrayList<>();
    private final ResumeService resumeService;

    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResumeDto findResumeById(@PathVariable Long id) {
        return resumeService.findResumeById(id);
    }

    @GetMapping("/getResumesByUsers/{userId}")
    public List<ResumeDto> getResumesMadeByUser(@PathVariable Long userId) {
        return resumeService.getResumesMadeByUser(userId);
    }

    @GetMapping("/categories/{categoryId}")
    public List<ResumeDto> findResumeByCategory(@PathVariable Integer categoryId) {
        return resumeService.getResumesByCategoryId(categoryId);
    }

    @PostMapping("create")
    public HttpStatus createResume(@Valid @RequestBody ResumeDto dto) {
        resumeService.save(dto);
        return HttpStatus.OK;
    }

    @PutMapping("update/{id}")
    public HttpStatus updateResume(@Valid @PathVariable Long id, @RequestBody ResumeDto dto) {
        resumeService.update(dto);
        return HttpStatus.OK;
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        resumeService.deleteById(id);
        return HttpStatus.OK;
    }
}
