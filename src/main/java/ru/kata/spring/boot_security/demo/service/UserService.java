package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    void removeUser(int id);
    User getUserById(int id);
    void updateUser(User updated);
    List<User> getUserList();
    User getUserByName(String name);

}
