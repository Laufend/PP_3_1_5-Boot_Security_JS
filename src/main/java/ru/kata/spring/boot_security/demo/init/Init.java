package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void addUserDB() {
        Set<Role> roleAdmin = new HashSet<>();
        Set<Role> roleUser = new HashSet<>();
        Role roleA = new Role("ROLE_ADMIN");
        Role roleU = new Role("ROLE_USER");
        roleService.addRole(roleA);
        roleService.addRole(roleU);
        roleAdmin.add(roleA);
        roleAdmin.add(roleU);
        roleUser.add(roleU);
        User user1 = new User("Admin","admin", "Ivan", 45, 2000, "IT", roleAdmin);
        User user2 = new User("User", "user", "Sergey", 35, 1000, "Sales", roleUser);
        userService.addUser(user1);
        userService.addUser(user2);
    }
}
