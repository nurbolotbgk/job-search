package com.example.demo.service.impl;

import com.example.demo.dao.ContactInfoDao;
import com.example.demo.dto.ContactInfoDto;
import com.example.demo.model.ContactInfo;
import com.example.demo.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoDao contactInfoDao;

    @Override
    public void saveAll(Long resumeId, List<ContactInfoDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return;
        }

        for (ContactInfoDto dto : dtoList) {
            ContactInfo contactInfo = ContactInfo.builder()
                    .contactValue(dto.getContactValue())
                    .typeId(dto.getTypeId())
                    .resumeId(resumeId)
                    .build();

            contactInfoDao.save(contactInfo);
        }
    }

    @Override
    public void replaceAll(Long resumeId, List<ContactInfoDto> dtoList) {
        contactInfoDao.deleteByResumeId(resumeId);
        saveAll(resumeId, dtoList);
    }

    @Override
    public List<ContactInfoDto> findByResumeId(Long resumeId) {
        List<ContactInfo> contactList =
                contactInfoDao.findByResumeId(resumeId);

        if (contactList.isEmpty()) {
            return Collections.emptyList();
        }

        return contactList.stream()
                .map(contact -> ContactInfoDto.builder()
                        .id(contact.getId())
                        .contactValue(contact.getContactValue())
                        .typeId(contact.getTypeId())
                        .build())
                .toList();
    }
}