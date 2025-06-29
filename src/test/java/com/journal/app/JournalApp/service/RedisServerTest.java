package com.journal.app.JournalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RedisServerTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testRedis() {
        redisTemplate.opsForValue().set("email", "test21aws@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        String name = redisTemplate.opsForValue().get("name").toString();
        assertEquals("test21aws@gmail.com", email);
        assertEquals("Ruchitn21", name);
    }
}