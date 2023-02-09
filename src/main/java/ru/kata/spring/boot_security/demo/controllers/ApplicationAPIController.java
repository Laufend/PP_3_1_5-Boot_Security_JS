package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApplicationAPIController {

    private final UserService userService;

    @Autowired
    ApplicationAPIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get_login")
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) {
                return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }
        return new User();
    }

    @GetMapping("/get_users_table")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> response = userService.getUserList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        User response = userService.getUserById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add_user")
    public ResponseEntity<HttpStatus> addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/edit_user/{id}")
    public ResponseEntity<HttpStatus> editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/delete_user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.removeUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
