package com.example.demo.service.impl;

import com.example.demo.dao.EducationInfoDao;
import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoDao educationInfoDao;

    @Override
    public void saveAll(Long resumeId, List<EducationInfoDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        for (EducationInfoDto dto : dtoList) {
            EducationInfo education = EducationInfo.builder()
                    .institution(dto.getInstitution())
                    .program(dto.getProgram())
                    .startDate(dto.getStartDate())
                    .endDate(dto.getEndDate())
                    .degree(dto.getDegree())
                    .resumeId(resumeId)
                    .build();

            educationInfoDao.save(education);
        }
    }

    @Override
    public void replaceAll(Long resumeId, List<EducationInfoDto> dtoList) {
        educationInfoDao.deleteByResumeId(resumeId);
        saveAll(resumeId, dtoList);
    }

    @Override
    public List<EducationInfoDto> findByResumeId(Long resumeId) {
        List<EducationInfo> educationList =
                educationInfoDao.findByResumeId(resumeId);

        if (educationList.isEmpty()) {
            return Collections.emptyList();
        }

        return educationList.stream()
                .map(education -> EducationInfoDto.builder()
                        .id(education.getId())
                        .institution(education.getInstitution())
                        .program(education.getProgram())
                        .startDate(education.getStartDate())
                        .endDate(education.getEndDate())
                        .degree(education.getDegree())
                        .build())
                .toList();
    }
}
