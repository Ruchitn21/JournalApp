package com.journal.app.JournalApp.controller;

import com.journal.app.JournalApp.entity.JournalEntity;
import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.service.JournalEntryService;
import com.journal.app.JournalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name = "Journal APIs", description = "APIs that can be used to perform operations on journal entries")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all journal entries of a user")
    public ResponseEntity<List<JournalEntity>> getJournalEntries() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> user = userService.getUserByUsername(userName);
        List<JournalEntity> allJournalEntries = user.get().getJournalEntries();
        if(allJournalEntries!=null && !allJournalEntries.isEmpty()) {
            return new ResponseEntity<>(allJournalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @Operation(summary = "Adds a journal entry for an authenticated user")
    public ResponseEntity<JournalEntity> addJournal(@RequestBody JournalEntity journalEntity) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveJournalEntry(userName, journalEntity);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{id}")
    @Operation(summary = "Retrieves a journal entry for a specified id")
    public ResponseEntity<Optional<JournalEntity>> getJournalEntryById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByUsername(userName).orElse(null);
        List<JournalEntity> journalEntries = user.getJournalEntries().stream().filter(x->x.getId().equals(objectId)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()) {
            Optional<JournalEntity> journalEntry = journalEntryService.getJournalEntryById(objectId);
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{id}")
    @Operation(summary = "Updates a journal entry for a specified id")
    public ResponseEntity<JournalEntity> updateJournal(@PathVariable String id, @RequestBody JournalEntity newEntry) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByUsername(userName).orElse(null);
        List<JournalEntity> journalEntries = user.getJournalEntries().stream().filter(x->x.getId().equals(objectId)).collect(Collectors.toList());
        if(!journalEntries.isEmpty()) {
            Optional<JournalEntity> journalEntry = journalEntryService.getJournalEntryById(objectId);
            if(journalEntry.isPresent()) {
                JournalEntity old = journalEntry.get();
                old.setJournalName(newEntry.getJournalName()!=null & !newEntry.getContent().equals("")? newEntry.getJournalName() : old.getJournalName());
                old.setContent(newEntry.getContent()!=null & !newEntry.getContent().equals("")? newEntry.getContent() : old.getContent());
                journalEntryService.saveJournalEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    @Operation(summary = "Deletes a journal entry for a specified id")
    public ResponseEntity<HttpStatus> deleteJournal(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean isDeleted = journalEntryService.deleteJournalEntryById(userName, objectId);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
