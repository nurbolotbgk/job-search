package com.example.demo.controller;

import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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



    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

}
