package com.example.demo.service;


import com.example.demo.dto.ResumeDto;

import java.util.List;

public interface ResumeService {


    List<ResumeDto> getResumesByCategoryId(Integer categoryId);

    List<ResumeDto> getAllResumes();

    ResumeDto findResumeById(Long id);

    List<ResumeDto> getResumesMadeByUser(Long userId);

    void save(ResumeDto dto);

    void update(ResumeDto dto);

    void deleteById(long id);
}
