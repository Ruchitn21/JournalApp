package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.model.SentimentData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaConsumerServiceTest {

    @Autowired
    private SentimentConsumerService kafkaConsumerService;

    @Test
    void testConsume() {
        SentimentData sentimentData = SentimentData.builder().email("nigam21aws@gmail.com").sentiment("Your Sentiment report for previous week").build();
        kafkaConsumerService.consume(sentimentData);
}
    }
