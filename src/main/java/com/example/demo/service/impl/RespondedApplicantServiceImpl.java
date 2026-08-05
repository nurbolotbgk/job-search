package com.example.demo.service.impl;

import com.example.demo.dao.RespondedApplicantDao;
import com.example.demo.dto.RespondedApplicantDto;

import com.example.demo.model.RespondedApplicant;

import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public void createResponse(RespondedApplicantDto dto) {
        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResumeId(dto.getResumeId());
        respondedApplicant.setVacancyId(dto.getVacancyId());

        respondedApplicantDao.createResponse(respondedApplicant);
    }
}
