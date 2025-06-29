package com.journal.app.JournalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testGetUsersForSentimentAnalysis() {
        Assertions.assertNotNull(userRepositoryImpl.getUserForSentimentAnalysis());
    }
}
