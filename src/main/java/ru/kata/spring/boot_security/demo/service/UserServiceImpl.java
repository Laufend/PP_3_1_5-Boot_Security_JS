package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    private final RoleDao roleDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    UserServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    private void checkRolesForUser(User user) {
        Set<Role> checkedRoles = new HashSet<>();
        for (Role role: user.getAuthorities()) {
            checkedRoles.add(roleDao.getRole(role.getRole()));
        }
        user.setAuthorities(checkedRoles);
    }

    @Transactional
    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        checkRolesForUser(user);
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Transactional(readOnly=true)
    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Transactional
    @Override
    public void updateUser(User updated) {
        updated.setPassword(passwordEncoder.encode(updated.getPassword()));
        checkRolesForUser(updated);
        userDao.updateUser(updated);
    }

    @Transactional(readOnly=true)
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
}