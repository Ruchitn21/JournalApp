package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUsers() {
        User user1 = User.builder().username("testUser1").password("testPassword1").roles(new ArrayList<>()).build();
        User user2 = User.builder().username("testUser2").password("testPassword2").roles(new ArrayList<>()).build();
        List<User> mockUsers = new ArrayList<>(List.of(user1, user2));
        when(userService.getAllUsers()).thenReturn(mockUsers);
        assertNotNull(userService.getAllUsers());
    }

    @Test
    void testDeleteUser() {
        assertTrue(userService.deleteUserByUsername("testUser"));
        assertTrue(userService.deleteUserByUsername("testAdminUser"));
    }

    @Test
    void testSaveNewUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        assertTrue(userService.saveNewUser(user));
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        userService.saveUser(user);
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void testSaveAdminUser() {
        User user = new User();
        user.setUsername("testAdminUser");
        user.setPassword("testAdminPassword");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        assertTrue(userService.saveAdminUser(user));
    }

    @Test
    void testGetUserByUsername() {
        when(userRepository.findByUsername("testUser")).thenReturn(User.builder().username("testUser").password("testPass1").build());
        when(userRepository.findByUsername("testAdminUser")).thenReturn(User.builder().username("testAdminUser").password("testPass2").build());
        assertEquals("testUser", userService.getUserByUsername("testUser").get().getUsername());
        assertEquals("testAdminUser", userService.getUserByUsername("testAdminUser").get().getUsername());

    }
}
