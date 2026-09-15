package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResumeService {

    List<ResumeDto> getResumesByCategoryId(Integer categoryId);

    Page<ResumeDto> getAllResumes(int page, int size);

    ResumeDto findResumeById(Long id);

    Resume findEntityById(Long id);

    Page<ResumeDto> getResumesMadeByUser(Long userId, int page, int size);

    void save(ResumeDto dto);

    void update(ResumeDto dto);

    void deleteById(long id);
}