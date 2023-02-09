package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    User getUserByName(String login);

    void updateUser(User user);

    List<User> getUserList();

}
