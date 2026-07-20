package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantDao;
import com.example.demo.dto.RespondedApplicantDto;

import com.example.demo.model.RespondedApplicant;

import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public List<RespondedApplicantDto> getAllRespondedApplicants() {
        List<RespondedApplicant> respondedApplicants = respondedApplicantDao.getAllRespondedApplicants();

        return respondedApplicants.stream()
                .map(e -> RespondedApplicantDto.builder()
                        .id(e.getId())
                        .vacancyId(e.getVacancyId())
                        .resumeId(e.getResumeId())
                        .confirmation(e.isConfirmation())
                        .build()
                )
                .toList();

    }
}
