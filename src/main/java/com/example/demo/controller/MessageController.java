package com.example.demo.controller;

import com.example.demo.dto.MessageDto;
import com.example.demo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{respondedApplicantId}")
    public String chat(
            @PathVariable Long respondedApplicantId,
            Model model
    ) {

        List<MessageDto> messages =
                messageService.getMessages(respondedApplicantId);

        model.addAttribute(
                "respondedApplicantId",
                respondedApplicantId
        );

        model.addAttribute(
                "messages",
                messages
        );

        return "messages/chat";
    }


    @PostMapping("/{respondedApplicantId}")
    public String sendMessage(
            @PathVariable Long respondedApplicantId,
            @RequestParam String content
    ) {

        messageService.sendMessage(
                respondedApplicantId,
                content
        );

        return "redirect:/messages/" + respondedApplicantId;
    }
}