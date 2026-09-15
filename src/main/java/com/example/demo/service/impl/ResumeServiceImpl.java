package com.example.demo.service.impl;

import com.example.demo.dto.ResumeDto;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.model.Category;
import com.example.demo.model.Resume;
import com.example.demo.model.User;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ContactInfoService;
import com.example.demo.service.EducationInfoService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.UserService;
import com.example.demo.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    private final UserService userService;
    private final CategoryService categoryService;

    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;

    @Override
    public List<ResumeDto> getResumesByCategoryId(Integer categoryId) {

        List<Resume> resumes = resumeRepository.findByCategory_Id(categoryId);

        if (resumes.isEmpty()) {
            throw new CategoryNotFoundException();
        }

        return resumes.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .active(e.getActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUser().getId())
                        .categoryId(e.getCategory().getId())
                        .build())
                .toList();
    }

    @Override
    public Page<ResumeDto> getAllResumes(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );

        Page<Resume> resumes = resumeRepository.findByActiveTrue(pageable);

        return resumes.map(r -> ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .salary(r.getSalary())
                .active(r.getActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .userId(r.getUser().getId())
                .categoryId(r.getCategory().getId())
                .build());
    }

    @Override
    public ResumeDto findResumeById(Long id) {

        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return ResumeDto.builder()
                .id(resume.getId())
                .name(resume.getName())
                .salary(resume.getSalary())
                .active(resume.getActive())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .userId(resume.getUser().getId())
                .categoryId(resume.getCategory().getId())
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
    public Resume findEntityById(Long id) {
        return resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
    }

    @Override
    public Page<ResumeDto> getResumesMadeByUser(
            Long userId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdDate").descending()
        );

        Page<Resume> resumes = resumeRepository.findByUser_Id(userId, pageable);

        return resumes.map(r -> ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .salary(r.getSalary())
                .active(r.getActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .userId(r.getUser().getId())
                .categoryId(r.getCategory().getId())
                .build());
    }

    @Override
    @Transactional
    public void save(ResumeDto dto) {

        User user = userService.findEntityById(dto.getUserId());

        Category category = categoryService.findEntityById(dto.getCategoryId());

        Resume resume = new Resume();

        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.getActive());
        resume.setCreatedDate(LocalDateTime.now());
        resume.setUpdateTime(LocalDateTime.now());

        resume.setUser(user);
        resume.setCategory(category);

        Resume savedResume = resumeRepository.save(resume);

        workExperienceInfoService.saveAll(savedResume, dto.getWorkExperiences());

        educationInfoService.saveAll(savedResume, dto.getEducations());

        contactInfoService.saveAll(savedResume, dto.getContacts());
    }

    @Override
    @Transactional
    public void update(ResumeDto dto) {

        Resume resume = resumeRepository.findById(dto.getId())
                .orElseThrow(ResumeNotFoundException::new);

        Category category = categoryService.findEntityById(dto.getCategoryId());

        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.getActive());
        resume.setUpdateTime(LocalDateTime.now());
        resume.setCategory(category);

        Resume savedResume = resumeRepository.save(resume);

        workExperienceInfoService.replaceAll(savedResume, dto.getWorkExperiences());

        educationInfoService.replaceAll(savedResume, dto.getEducations());

        contactInfoService.replaceAll(savedResume, dto.getContacts());
    }

    @Override
    public void deleteById(long id) {

        if (!resumeRepository.existsById(id)) {
            throw new ResumeNotFoundException();
        }

        resumeRepository.deleteById(id);
    }
}