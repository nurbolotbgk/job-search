package com.example.demo.service;

import com.example.demo.dto.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceInfoService {

    void saveAll(Long resumeId, List<WorkExperienceInfoDto> dtoList);

    void replaceAll(Long resumeId, List<WorkExperienceInfoDto> dtoList);

    List<WorkExperienceInfoDto> findByResumeId(Long resumeId);
}