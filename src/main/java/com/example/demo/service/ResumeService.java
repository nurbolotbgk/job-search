package com.example.demo.service;

import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ResumeService {

    List<ResumeDto> getResumesByCategoryId(Integer categoryId);

    Page<ResumeDto> getAllResumes(int page, int size);

    ResumeDto findResumeById(Long id);

    Resume findEntityById(Long id);

    Page<ResumeDto> getResumesMadeByUser(Long userId, int page, int size);

    void save(ResumeDto dto);

    @Transactional
    void updateTime(Long id, Long userId);

    void update(ResumeDto dto);

    @Transactional
    void toggleActive(Long id, Long userId);

    void deleteById(long id);
}