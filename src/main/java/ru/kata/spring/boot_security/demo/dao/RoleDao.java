package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;

public interface RoleDao {

    Role getRole(String roleName);

    void addRole(Role role);
}
