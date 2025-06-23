package com.journal.app.JournalApp.controller;

import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.repository.UserRepository;
import com.journal.app.JournalApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    public void setup() {
        // Set up security context mocking
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    public void testUpdateUser_WhenUserExists_ReturnsUpdatedUser() {
        String username = "testUser";
        User requestUser = new User();
        requestUser.setUsername("newUser");
        requestUser.setPassword("newPass");

        User existingUser = new User();
        existingUser.setUsername("testUser");
        existingUser.setPassword("oldPass");

        when(authentication.getName()).thenReturn(username);
        when(userService.getUserByUsername(username)).thenReturn(Optional.of(existingUser));

        ResponseEntity<User> response = userController.updateUser(requestUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("newUser", response.getBody().getUsername());
        assertEquals("newPass", response.getBody().getPassword());
    }

    @Test
    public void testUpdateUser_WhenUserNotFound_ReturnsNotFound() {
        String username = "missingUser";

        when(authentication.getName()).thenReturn(username);
        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userController.updateUser(new User());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testDeleteUser_ReturnsNoContent() {
        String username = "deleteUser";

        when(authentication.getName()).thenReturn(username);

        ResponseEntity<?> response = userController.deleteUser();

        verify(userService).deleteUserByUsername(username);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
