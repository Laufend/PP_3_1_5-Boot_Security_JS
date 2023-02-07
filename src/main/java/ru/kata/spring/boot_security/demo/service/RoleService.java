package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

public interface RoleService {

    Role getRole(String roleName);

    void addRole(Role role);
}
