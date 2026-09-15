package com.example.demo.service;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.Resume;

import java.util.List;

public interface ContactInfoService {

    void saveAll(Resume resume, List<ContactInfoDto> dtoList);

    void replaceAll(Resume resume, List<ContactInfoDto> dtoList);

    List<ContactInfoDto> findByResumeId(Long resumeId);
}