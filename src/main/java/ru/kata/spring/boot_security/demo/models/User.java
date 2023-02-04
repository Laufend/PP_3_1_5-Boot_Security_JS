package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column(name="login", unique = true)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name = "surname")
    private String surname;
    @Column(name = "age")
    private Integer age;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "specialization")
    private String specialization;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> authorities = new HashSet<>();

    public User() {}

    public User(String username, String password, String surname, int age, int salary, String specialization, Set<Role> authorities) {
        this.username = username;
        this.password = password;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
        this.specialization = specialization;
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", specialization='" + specialization + '\'' +
                ", authorities=" + authorities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) && password.equals(user.password) && Objects.equals(surname, user.surname)
                && Objects.equals(age, user.age) && Objects.equals(salary, user.salary)
                && Objects.equals(specialization, user.specialization) && authorities.equals(user.authorities);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, surname, salary, specialization);
    }
    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return username;
    }
    public void setName(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }
    public void setUsername(String login) {
        this.username = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
