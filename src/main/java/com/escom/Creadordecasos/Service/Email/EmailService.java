package com.escom.Creadordecasos.Service.Email;

import com.escom.Creadordecasos.Dto.EmailBody;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class EmailService{
    private final JavaMailSender sender;

    public boolean sendEmail(EmailBody emailBody) {
        return sendEmailTool(emailBody.getContent(), emailBody.getEmail(), emailBody.getSubject());
    }

    public boolean sendEmailTool(String textMessage, String email, String subject) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email);
            helper.setText(textMessage);
            helper.setSubject(subject);
            sender.send(message);
            send = true;
        } catch (MessagingException e ) {
            e.printStackTrace();
        }

        return send;
    }
}
