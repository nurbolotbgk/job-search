package com.example.demo.service.impl;

import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.model.ContactType;
import com.example.demo.model.Resume;
import com.example.demo.repository.ContactInfoRepository;
import com.example.demo.service.ContactInfoService;
import com.example.demo.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ContactTypeService contactTypeService;

    @Override
    public void saveAll(Resume resume, List<ContactInfoDto> dtoList) {

        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        for (ContactInfoDto dto : dtoList) {

            ContactType contactType =
                    contactTypeService.findEntityById(dto.getTypeId());

            ContactInfo contactInfo = ContactInfo.builder()
                    .contactValue(dto.getContactValue())
                    .contactType(contactType)
                    .resume(resume)
                    .build();

            contactInfoRepository.save(contactInfo);
        }
    }

    @Override
    public void replaceAll(Resume resume, List<ContactInfoDto> dtoList) {
        contactInfoRepository.deleteByResume_Id(resume.getId());

        saveAll(resume, dtoList);
    }

    @Override
    public List<ContactInfoDto> findByResumeId(Long resumeId) {

        List<ContactInfo> contactList =
                contactInfoRepository.findByResume_Id(resumeId);

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