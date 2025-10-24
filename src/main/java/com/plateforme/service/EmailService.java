package com.plateforme.service;

import com.plateforme.email.EmailAttachment;
import com.plateforme.email.EmailRequest;
import com.plateforme.entity.EmailLog;
import com.plateforme.repository.EmailLogRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@Transactional
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final EmailLogRepository emailLogRepository;

    @Value("${app.email.from}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender, TemplateEngine templateEngine, EmailLogRepository emailLogRepository) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.emailLogRepository = emailLogRepository;
    }

    @Async
    public void envoyerEmail(EmailRequest request) {
        EmailLog log = new EmailLog();
        log.setDestinataire(request.getTo());
        log.setTemplate(request.getTemplate());
        log.setSujet(request.getSubject());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(request.getTo());
            helper.setSubject(request.getSubject());

            Context context = new Context();
            Map<String, Object> vars = request.getVariables();
            if (vars != null) context.setVariables(vars);

            String htmlContent = templateEngine.process(request.getTemplate(), context);
            helper.setText(htmlContent, true);

            if (request.getAttachments() != null) {
                for (EmailAttachment attachment : request.getAttachments()) {
                    helper.addAttachment(attachment.getFileName(), new ByteArrayResource(attachment.getContent()));
                }
            }

            mailSender.send(message);
            log.setEnvoyeAvecSucces(true);
        } catch (Exception e) {
            log.setEnvoyeAvecSucces(false);
            log.setErreurMessage(e.getMessage());
        } finally {
            emailLogRepository.save(log);
        }
    }
}
