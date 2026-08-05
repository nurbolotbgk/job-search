package com.example.demo.service;

import com.example.demo.dto.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {

    void saveAll(Long resumeId, List<EducationInfoDto> dtoList);

    void replaceAll(Long resumeId, List<EducationInfoDto> dtoList);

    List<EducationInfoDto> findByResumeId(Long resumeId);
}