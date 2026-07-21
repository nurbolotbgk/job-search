package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {
    private final ResumeDao resumeDao;


    @Override
    public List<ResumeDto> getResumesByCategoryId(Integer categoryId) {
        List<Resume> resumeByCatId = resumeDao.getResumeByCategory(categoryId);

        return resumeByCatId.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .isActive(e.isActive())
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
                        .isActive(e.isActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUserId())
                        .categoryId(e.getCategoryId())
                        .build()
                )
                .toList();

    }

    @Override
    public ResumeDto findResumeById(Integer id) {
        Resume resume = resumeDao.findResumeById(id).orElseThrow(RuntimeException::new);
        return ResumeDto.builder()
                .id(resume.getId())
                .salary(resume.getSalary())
                .createdDate(resume.getCreatedDate())
                .updateTime(resume.getUpdateTime())
                .userId(resume.getUserId())
                .name(resume.getName())
                .categoryId(resume.getCategoryId())
                .isActive(resume.isActive())
                .build();

    }

    @Override
    public List<ResumeDto> getResumesMadeByUser(Long userId) {
        List<Resume> resumesMadeByUser = resumeDao.getResumesMadeByUser(userId);
        return resumesMadeByUser.stream()
                .map(e -> ResumeDto.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .salary(e.getSalary())
                        .isActive(e.isActive())
                        .createdDate(e.getCreatedDate())
                        .updateTime(e.getUpdateTime())
                        .userId(e.getUserId())
                        .categoryId(e.getCategoryId())
                        .build()
                )
                .toList();
    }

    @Override
    public void save(ResumeDto dto) {
        Resume resume = new Resume();
        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.isActive());
        resume.setCreatedDate(dto.getCreatedDate());
        resume.setUpdateTime(dto.getUpdateTime());
        resume.setUserId(dto.getUserId());
        resume.setCategoryId(dto.getCategoryId());
        resumeDao.save(resume);
    }

    @Override
    public void update(ResumeDto dto) {
        Resume resume = new Resume();
        resume.setId(dto.getId());
        resume.setName(dto.getName());
        resume.setSalary(dto.getSalary());
        resume.setActive(dto.isActive());
        resume.setUpdateTime(LocalDateTime.now());
        resume.setUserId(dto.getUserId());
        resume.setCategoryId(dto.getCategoryId());
        resumeDao.update(resume);
    }

    @Override
    public void deleteById(long id) {
        resumeDao.deleteById(id);
    }
}
