package com.journal.app.JournalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail() {
        emailService.sendEmail("nigam21aws@gmail.com",
                                "Test Email",
                                "This is a test email from Journal App. Thank you for using our service!");
    }
}