package com.example.demo.service.impl;

import com.example.demo.model.ContactType;
import com.example.demo.repository.ContactTypeRepository;
import com.example.demo.service.ContactTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;

    @Override
    public ContactType findEntityById(Integer id) {
        return contactTypeRepository.findById(id)
                .orElseThrow();
    }
}