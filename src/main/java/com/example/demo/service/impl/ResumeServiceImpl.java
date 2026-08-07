package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.model.Resume;
import com.example.demo.service.ContactInfoService;
import com.example.demo.service.EducationInfoService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;


    @Override
    public List<ResumeDto> getResumesByCategoryId(Integer categoryId) {
        List<Resume> resumeByCatId = resumeDao.getResumeByCategory(categoryId);
        if (resumeByCatId.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return resumeByCatId.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .active(e.getActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUserId())
                        .categoryId(e.getCategoryId())
                        .build()
                )
                .toList();
    }


    @Override
    public List<ResumeDto> getAllResumes() {
        List<Resume> resumeList = resumeDao.getAllResumes();

        return resumeList.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .active(e.getActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUserId())
                        .categoryId(e.getCategoryId())
                        .build()
                )
                .toList();

    }

    @Override
    public ResumeDto findResumeById(Long id) {
        Resume resume = resumeDao.findResumeById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .salary(resume.getSalary())
                .active(resume.getActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .userId(resume.getUserId())
                .categoryId(resume.getCategoryId())
                .workExperiences(
                        workExperienceInfoService.findByResumeId(id)
                )
                .educations(
                        educationInfoService.findByResumeId(id)
                )
                .contacts(
                        contactInfoService.findByResumeId(id)
                )
                .build();
    }

    @Override
    public List<ResumeDto> getResumesMadeByUser(Long userId) {
        List<Resume> resumesMadeByUser = resumeDao.getResumesMadeByUser(userId);
        if (resumesMadeByUser.isEmpty()) {
            throw new ResumeNotFoundException();
        }

        return resumesMadeByUser.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .active(e.getActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUserId())
                        .categoryId(e.getCategoryId())
                        .build()
                )
                .toList();
    }

    @Override
    @Transactional
    public void save(ResumeDto dto) {
        LocalDateTime now = LocalDateTime.now();

        Resume resume = new Resume();
        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.getActive());
        resume.setCreatedDate(now);
        resume.setUpdateTime(now);
        resume.setUserId(dto.getUserId());
        resume.setCategoryId(dto.getCategoryId());

        Long resumeId = resumeDao.save(resume);

        workExperienceInfoService.saveAll(
                resumeId,
                dto.getWorkExperiences()
        );

        educationInfoService.saveAll(
                resumeId,
                dto.getEducations()
        );

        contactInfoService.saveAll(
                resumeId,
                dto.getContacts()
        );
    }

    @Override
    @Transactional
    public void update(ResumeDto dto) {
        resumeDao.findResumeById(dto.getId())
                .orElseThrow(ResumeNotFoundException::new);

        Resume resume = new Resume();
        resume.setId(dto.getId());
        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.getActive());
        resume.setUpdateTime(LocalDateTime.now());
        resume.setUserId(dto.getUserId());
        resume.setCategoryId(dto.getCategoryId());

        resumeDao.update(resume);

        workExperienceInfoService.replaceAll(
                dto.getId(),
                dto.getWorkExperiences()
        );

        educationInfoService.replaceAll(
                dto.getId(),
                dto.getEducations()
        );

        contactInfoService.replaceAll(
                dto.getId(),
                dto.getContacts()
        );
    }

    @Override
    public void deleteById(long id) {
        resumeDao.deleteById(id);
    }
}
