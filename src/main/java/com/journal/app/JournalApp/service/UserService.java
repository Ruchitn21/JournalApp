package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.dto.UserDTO;
import com.journal.app.JournalApp.entity.JournalEntity;
import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.repository.JournalEntryRepository;
import com.journal.app.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER")); // Default role for new users
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("Error saving user: " + e.getMessage());
            return false;
        }
    }

    public boolean saveAdminUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("ADMIN")); // Default role for new users
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("Error saving admin user: " + e.getMessage());
            return false;
        }
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    @Transactional
    public boolean deleteUserByUsername(String userName) {
        try{
            List<JournalEntity> journalEntities = userRepository.findByUsername(userName).getJournalEntries();
            for(JournalEntity journalEntity : journalEntities) {
                journalEntryRepository.deleteById(journalEntity.getId()); // Remove the user reference from the journal entry
            }
            userRepository.deleteByUsername(userName);
            return true;
        } catch (Exception e) {
            log.info("Error deleting user: " + e.getMessage());
            return false;
        }
    }
}