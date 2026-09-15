package com.example.demo.service;

import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.model.Resume;

import java.util.List;

public interface WorkExperienceInfoService {

    void saveAll(Resume resume, List<WorkExperienceInfoDto> dtoList);

    void replaceAll(Resume resume, List<WorkExperienceInfoDto> dtoList);

    List<WorkExperienceInfoDto> findByResumeId(Long resumeId);
}