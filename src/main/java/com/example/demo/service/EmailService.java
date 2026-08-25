package com.example.demo.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendEmail(String toEmail, String link)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(emailFrom, "Job Search Support");

        helper.setTo(toEmail);

        String subject = "Восстановление пароля";

        String content =
                "<p>Здравствуйте!</p>"
                        + "<p>Вы запросили восстановление пароля.</p>"
                        + "<p>Перейдите по ссылке:</p>"
                        + "<p><a href=\"" + link + "\">"
                        + "Изменить пароль"
                        + "</a></p>"
                        + "<p>Если вы не запрашивали восстановление пароля, "
                        + "просто проигнорируйте это письмо.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }
}