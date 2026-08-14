package com.example.demo.repository;

import com.example.demo.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationInfoRepository
        extends JpaRepository<EducationInfo, Long> {

    List<EducationInfo> findByResume_Id(Long resumeId);

    void deleteByResume_Id(Long resumeId);
}