package com.example.demo.service;

import com.example.demo.dto.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getMessages(Long respondedApplicantId);

    void sendMessage(
            Long respondedApplicantId,
            String content
    );
}
