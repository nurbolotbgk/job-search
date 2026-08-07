package com.example.demo.service;

import com.example.demo.dto.ContactInfoDto;

import java.util.List;

public interface ContactInfoService {

    void saveAll(Long resumeId, List<ContactInfoDto> dtoList);

    void replaceAll(Long resumeId, List<ContactInfoDto> dtoList);

    List<ContactInfoDto> findByResumeId(Long resumeId);
}