package com.read.dima.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false,columnDefinition = "VARCHAR(50)")
    private String username;
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;
    @Column(name = "Date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;
    @Column(name = "CountReadBooks", nullable = false, columnDefinition = "INT")
    private Long countReadBooks;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private List<User_Book> books = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    public User() {
    }

    public User(Long id,
                String username,
                String password,
                LocalDate date,
                Long countReadBooks,
                List<User_Book> books) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.countReadBooks = countReadBooks;
        this.books = books;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCountReadBooks() {
        return countReadBooks;
    }

    public void setCountReadBooks(Long countReadBooks) {
        this.countReadBooks = countReadBooks;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<User_Book> getBooks() {
        return books;
    }

    public void setBooks(List<User_Book> books) {
        this.books = books;
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
}

