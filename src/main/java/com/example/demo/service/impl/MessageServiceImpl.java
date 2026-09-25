package com.example.demo.service.impl;

import com.example.demo.dto.MessageDto;
import com.example.demo.model.Message;
import com.example.demo.model.RespondedApplicant;
import com.example.demo.model.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.RespondedApplicantRepository;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final RespondedApplicantRepository respondedApplicantRepository;
    private final UserService userService;

    @Override
    public List<MessageDto> getMessages(Long respondedApplicantId) {

        return messageRepository
                .findByRespondedApplicant_IdOrderByTimestampAsc(
                        respondedApplicantId
                )
                .stream()
                .map(message -> MessageDto.builder()
                        .id(message.getId())
                        .content(message.getContent())
                        .timestamp(message.getTimestamp())
                        .respondedApplicantId(
                                message.getRespondedApplicant().getId()
                        )
                        .senderId(message.getSender().getId())
                        .senderName(message.getSender().getName())
                        .build())
                .toList();
    }

    @Override
    public void sendMessage(
            Long respondedApplicantId,
            String content
    ) {

        RespondedApplicant respondedApplicant =
                respondedApplicantRepository
                        .findById(respondedApplicantId)
                        .orElseThrow();

        User currentUser = userService.findEntityById(
                userService.getCurrentUser().getId()
        );

        Message message = new Message();

        message.setContent(content);
        message.setTimestamp(new Date());
        message.setRespondedApplicant(respondedApplicant);
        message.setSender(currentUser);

        messageRepository.save(message);
    }
}