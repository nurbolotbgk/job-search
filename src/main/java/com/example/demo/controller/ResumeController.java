package com.example.demo.controller;

import com.example.demo.model.Resumes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("resumes")
public class ResumeController {
    private final List<Resumes> storage = new ArrayList<>();

    @GetMapping("resumes")
    public ResponseEntity<List<Resumes>> getAllresumes() {
        return ResponseEntity.ok(storage);
    }

    @GetMapping("{id}")
    public ResponseEntity<Resumes> searchResumeById(@PathVariable Integer id) {
        for (Resumes resume : storage) {
            if (resume.getCategory_id() == id) {
                return ResponseEntity.ok(resume);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("{applicationId}")
    public ResponseEntity<Resumes> searchAppliacant(@PathVariable Integer applicationId) {
        for (Resumes resume : storage) {
            if (resume.getApplication_id() == applicationId) {
                return ResponseEntity.ok(resume);
            }
        }

        return ResponseEntity.notFound().build();
    }

}
