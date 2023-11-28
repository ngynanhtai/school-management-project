package com.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class EmailUtil {

    @Value("${spring.mail.username}")
    private String senderEmail;

    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String[] to, String template, String subject, Map<String, Object> variables) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(variables);
        String html = templateEngine.process(template, context);
        helper.setTo(to);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom(senderEmail);
        javaMailSender.send(message);
    }

    public void sendEmail(String[] to, String[] cc, String template, String subject, Map<String, Object> variables)
            throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(variables);
        String html = templateEngine.process(template, context);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom(senderEmail);
        javaMailSender.send(message);
    }
}
