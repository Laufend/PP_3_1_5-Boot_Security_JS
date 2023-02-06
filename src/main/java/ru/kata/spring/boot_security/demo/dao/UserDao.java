package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    void removeUser(int id);

    User getUser(int id);

    User getUser(String login);

    void updateUser(User user);

    List<User> getUserList();

}
