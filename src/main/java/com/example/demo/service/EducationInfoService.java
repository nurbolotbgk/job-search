package com.example.demo.service;

import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.Resume;

import java.util.List;

public interface EducationInfoService {

    void saveAll(Resume resume, List<EducationInfoDto> dtoList);

    void replaceAll(Resume resume, List<EducationInfoDto> dtoList);

    List<EducationInfoDto> findByResumeId(Long resumeId);
}