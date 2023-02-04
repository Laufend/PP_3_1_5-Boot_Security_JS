package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    int Id;
    @Column(name="role", unique = true)
    String role;
    public Role() {}
    public Role(String role) {
        this.role = role;
    }
    // Методы
    @Override
    public String getAuthority() {
        return this.getRole();
    }
    @Override
    public String toString() {
        return this.getRole();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role);
    }
    @Override
    public int hashCode() {
        return Objects.hash(role);
    }

    // Сеттеры и геттеры
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
