package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
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

    @GetMapping("id/{id}")
    public ResumeDto findResumeById(@PathVariable Integer id) {
        return resumeService.findResumeById(id);
    }

    @GetMapping("/resumesByUsers/{userId}")
    public List<ResumeDto> getResumesMadeByUser(@PathVariable Long userId) {
        return resumeService.getResumesMadeByUser(userId);
    }

    @GetMapping("/categories/{categoryId}")
    public List<ResumeDto> findResumeByCategory(@PathVariable Integer categoryId) {
        return resumeService.getResumesByCategoryId(categoryId);
    }

    @PostMapping
    public HttpStatus createResume(@RequestBody ResumeDto dto) {
        resumeService.save(dto);
        return HttpStatus.OK;
    }

    @PutMapping
    public HttpStatus updateResume(@RequestBody ResumeDto dto) {
        resumeService.update(dto);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        resumeService.deleteById(id);
        return HttpStatus.OK;
    }
}
