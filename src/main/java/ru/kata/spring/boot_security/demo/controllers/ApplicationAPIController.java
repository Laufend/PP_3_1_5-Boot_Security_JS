package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class ApplicationAPIController {

    private final UserService userService;

    @Autowired
    ApplicationAPIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/get_login")
    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) {
                return (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            }
        }
        return new User();
    }

    @GetMapping(value = "/get_users_table")
    public List<User> getUsersTable() {
        return userService.getUserList();
    }

    @GetMapping(value = "/add_user")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "Success";
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping(value = "/delete_user/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.removeUser(id);
        return "Success";
    }

    @GetMapping(value = "/edit_user/{id}")
    public String editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "Success";
    }

}
