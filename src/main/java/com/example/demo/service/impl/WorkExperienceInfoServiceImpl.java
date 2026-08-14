package com.example.demo.service.impl;

import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.model.Resume;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.WorkExperienceInfoRepository;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {

    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void saveAll(Long resumeId, List<WorkExperienceInfoDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);

        for (WorkExperienceInfoDto dto : dtoList) {
            WorkExperienceInfo experience =
                    WorkExperienceInfo.builder()
                            .years(dto.getYears())
                            .companyName(dto.getCompanyName())
                            .position(dto.getPosition())
                            .responsibilities(dto.getResponsibilities())
                            .resume(resume)
                            .build();

            workExperienceInfoRepository.save(experience);
        }
    }

    @Override
    @Transactional
    public void replaceAll(Long resumeId, List<WorkExperienceInfoDto> dtoList) {
        workExperienceInfoRepository.deleteByResume_Id(resumeId);
        saveAll(resumeId, dtoList);
    }

    @Override
    public List<WorkExperienceInfoDto> findByResumeId(Long resumeId) {
        List<WorkExperienceInfo> experienceList =
                workExperienceInfoRepository.findByResume_Id(resumeId);

        if (experienceList.isEmpty()) {
            return Collections.emptyList();
        }

        return experienceList.stream()
                .map(experience -> WorkExperienceInfoDto.builder()
                        .id(experience.getId())
                        .years(experience.getYears())
                        .companyName(experience.getCompanyName())
                        .position(experience.getPosition())
                        .responsibilities(experience.getResponsibilities())
                        .build())
                .toList();
    }
}