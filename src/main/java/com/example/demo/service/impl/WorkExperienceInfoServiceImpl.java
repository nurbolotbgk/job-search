package com.example.demo.service.impl;

import com.example.demo.dao.WorkExperienceInfoDao;
import com.example.demo.dto.WorkExperienceInfoDto;
import com.example.demo.model.WorkExperienceInfo;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {

    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public void saveAll(
            Long resumeId,
            List<WorkExperienceInfoDto> dtoList
    ) {
        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        for (WorkExperienceInfoDto dto : dtoList) {
            WorkExperienceInfo experience = WorkExperienceInfo.builder()
                    .years(dto.getYears())
                    .companyName(dto.getCompanyName())
                    .position(dto.getPosition())
                    .responsibilities(dto.getResponsibilities())
                    .resumeId(resumeId)
                    .build();

            workExperienceInfoDao.save(experience);
        }
    }

    @Override
    public void replaceAll(Long resumeId, List<WorkExperienceInfoDto> dtoList) {
        workExperienceInfoDao.deleteByResumeId(resumeId);
        saveAll(resumeId, dtoList);
    }

    @Override
    public List<WorkExperienceInfoDto> findByResumeId(Long resumeId) {
        List<WorkExperienceInfo> experienceList =
                workExperienceInfoDao.findByResumeId(resumeId);

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