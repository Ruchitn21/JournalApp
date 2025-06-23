package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.journal.app.JournalApp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Pihu21").password("Pihu21#").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsService.loadUserByUsername("Pihu21");
        Assertions.assertNotNull(userDetails);
    }

    @Test
    void testLoadUserByUsernameNotFoundException() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(null);
        Assertions.assertThrows(org.springframework.security.core.userdetails.UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("NonExistentUser");
        });
    }
}
