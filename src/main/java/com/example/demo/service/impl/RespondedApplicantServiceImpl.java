package com.example.demo.service.impl;

import com.example.demo.dto.RespondedApplicantDto;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.Resume;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.RespondedApplicantRepository;
import com.example.demo.service.RespondedApplicantService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {

    private final RespondedApplicantRepository respondedApplicantRepository;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    @Override
    public void createResponse(RespondedApplicantDto dto) {

        Resume resume = resumeService.findEntityById(dto.getResumeId());

        Vacancy vacancy = vacancyService.findEntityById(dto.getVacancyId());

        RespondedApplicant respondedApplicant = new RespondedApplicant();

        respondedApplicant.setResume(resume);
        respondedApplicant.setVacancy(vacancy);

        respondedApplicantRepository.save(respondedApplicant);
    }
}