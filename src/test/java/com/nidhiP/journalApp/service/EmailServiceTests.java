package com.nidhiP.journalApp.service;

import com.nidhiP.journalApp.services.EmailService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Disabled("Requires Gmail credentials")
    @Test
    void testSendMail(){
        emailService.sendEmail("yourmail@gmail.com", "Testing Spring Boot mail sender", "Hello there , the email sending from Spring Boot is successfull. Huurayyy!!!");
    }
}
