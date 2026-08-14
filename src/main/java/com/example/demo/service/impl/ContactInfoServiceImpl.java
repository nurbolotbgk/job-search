package com.example.demo.service.impl;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.exception.ResumeNotFoundException;
import com.example.demo.model.ContactInfo;
import com.example.demo.model.ContactType;
import com.example.demo.model.Resume;
import com.example.demo.repository.ContactInfoRepository;
import com.example.demo.repository.ContactTypeRepository;
import com.example.demo.repository.ResumeRepository;
import com.example.demo.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ResumeRepository resumeRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    public void saveAll(Long resumeId, List<ContactInfoDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(ResumeNotFoundException::new);

        for (ContactInfoDto dto : dtoList) {

            ContactType contactType = contactTypeRepository.findById(dto.getTypeId()).orElseThrow();

            ContactInfo contactInfo = ContactInfo.builder()
                    .contactValue(dto.getContactValue())
                    .contactType(contactType)
                    .resume(resume)
                    .build();

            contactInfoRepository.save(contactInfo);
        }
    }

    @Override
    public void replaceAll(Long resumeId, List<ContactInfoDto> dtoList) {
        contactInfoRepository.deleteByResume_Id(resumeId);
        saveAll(resumeId, dtoList);
    }

    @Override
    public List<ContactInfoDto> findByResumeId(Long resumeId) {
        List<ContactInfo> contactList = contactInfoRepository.findByResume_Id(resumeId);

        if (contactList.isEmpty()) {
            return Collections.emptyList();
        }

        return contactList.stream()
                .map(contact -> ContactInfoDto.builder()
                        .id(contact.getId())
                        .contactValue(contact.getContactValue())
                        .typeId(contact.getContactType().getId())
                        .build())
                .toList();
    }
}