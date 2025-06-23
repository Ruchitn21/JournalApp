package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        assertNotNull(userService.getAllUsers());
    }

    @Test
    void testDeleteUser() {
        assertTrue(userService.deleteByUsername("testUser"));
        assertTrue(userService.deleteByUsername("testAdminUser"));
    }

    @Test
    void testSaveNewUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        assertTrue(userService.saveNewUser(user));
    }

    @Test
    void testSaveAdminUser() {
        User user = new User();
        user.setUsername("testAdminUser");
        user.setPassword("testAdminPassword");
        assertTrue(userService.saveAdminUser(user));
    }

    @Test
    void testGetUserByUsername() {
        assertEquals("testUser", userService.getUserByUsername("testUser").get().getUsername());
        assertEquals("testAdminUser", userService.getUserByUsername("testAdminUser").get().getUsername());

    }

}
