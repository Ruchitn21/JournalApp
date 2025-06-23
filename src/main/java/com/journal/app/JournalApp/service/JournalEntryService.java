package com.journal.app.JournalApp.service;

import com.journal.app.JournalApp.entity.JournalEntity;
import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.repository.JournalEntryRepository;
import com.journal.app.JournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntry(String userName, JournalEntity journalEntity) {
        try {
            User user = userRepository.findByUsername(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepository.save(journalEntity);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            log.info("Error saving journal entry: " + e.getMessage());
            throw new RuntimeException("An error occured while saving an entry");
        }
    }

    public void saveJournalEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> getJournalEntryById(ObjectId id) {
        return Optional.ofNullable(journalEntryRepository.findById(id).orElse(null));
    }

    @Transactional
    public boolean deleteJournalEntryById(String userName, ObjectId id) {
        boolean isDeleted = false;
        try {
            User user = userRepository.findByUsername(userName);
            isDeleted = user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(isDeleted) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.info("Error deleting journal entry: " + e.getMessage());
            throw new RuntimeException("An error occurred while deleting the entry");
        }
        return isDeleted;
    }
}
