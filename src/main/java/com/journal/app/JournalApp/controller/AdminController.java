package com.journal.app.JournalApp.controller;

import com.journal.app.JournalApp.entity.User;
import com.journal.app.JournalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@Tag(name = "Admin APIs", description = "APIs that can be operated by user having ADMIN role")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("all-users")
    @Operation(summary = "Retrieves all users from the database")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if(!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("create-admin-user")
    @Operation(summary = "Creates a user with ADMIN role")
    public ResponseEntity<HttpStatus> createAdminUser(@RequestBody User user) {
        try {
            userService.saveAdminUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
