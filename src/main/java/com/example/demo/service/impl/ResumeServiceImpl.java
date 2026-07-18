package com.example.demo.service.impl;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;
import com.example.demo.model.Resume;
import com.example.demo.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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



}
