package com.example.demo.repository;

import com.example.demo.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkExperienceInfoRepository
        extends JpaRepository<WorkExperienceInfo, Long> {

    List<WorkExperienceInfo> findByResume_Id(Long resumeId);

    void deleteByResume_Id(Long resumeId);
}