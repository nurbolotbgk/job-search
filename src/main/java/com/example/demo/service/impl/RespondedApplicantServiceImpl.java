package com.example.demo.service.impl;

import com.example.demo.dto.RespondedApplicantDto;

import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.exception.VacancyNotFoundException;
import com.example.demo.model.RespondedApplicant;

import com.example.demo.model.Resume;
import com.example.demo.model.Vacancy;
import com.example.demo.repository.RespondedApplicantRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.repository.VacancyRepository;
import com.example.demo.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final VacancyRepository vacancyRepository;
    private final ResumeRepository resumeRepository;

    @Override
    public void createResponse(RespondedApplicantDto dto) {

        Resume resume = resumeRepository.findById(dto.getResumeId()).orElseThrow(ResumeNotFoundException::new);

        Vacancy vacancy = vacancyRepository.findById(dto.getVacancyId()).orElseThrow(VacancyNotFoundException::new);

        RespondedApplicant respondedApplicant = new RespondedApplicant();
        respondedApplicant.setResume(resume);
        respondedApplicant.setVacancy(vacancy);

        respondedApplicantRepository.save(respondedApplicant);
    }
}
