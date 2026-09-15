package com.example.demo.service.impl;

import com.example.demo.dto.EducationInfoDto;
import com.example.demo.model.EducationInfo;
import com.example.demo.model.Resume;
import com.example.demo.repository.EducationInfoRepository;
import com.example.demo.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoRepository educationInfoRepository;

    @Override
    public void saveAll(
            Resume resume,
            List<EducationInfoDto> dtoList
    ) {

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
                    .resume(resume)
                    .build();

            educationInfoRepository.save(education);
        }
    }

    @Override
    @Transactional
    public void replaceAll(
            Resume resume,
            List<EducationInfoDto> dtoList
    ) {

        educationInfoRepository.deleteByResume_Id(resume.getId());

        saveAll(resume, dtoList);
    }

    @Override
    public List<EducationInfoDto> findByResumeId(Long resumeId) {

        List<EducationInfo> educationList =
                educationInfoRepository.findByResume_Id(resumeId);

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