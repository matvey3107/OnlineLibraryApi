package com.read.dima.LibDTO;

import com.read.dima.Annotation.anotationDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class UserDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String username;
    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$", message = "password must include at least one digit and the letter and consist of 8-20 characters")
    private String password;
    @NotNull(message = "date is required")
    @anotationDate(min = 5, max = 101)
    private LocalDate date;
    private Long countReadBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Name is required") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password is required") @Pattern(regexp = "^(?=.[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$", message = "password must include at least one digit and the letter and consist of 8-20 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Pattern(regexp = "^(?=.[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$", message = "password must include at least one digit and the letter and consist of 8-20 characters") String password) {
        this.password = password;
    }

    public @NotNull(message = "date is required") LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "date is required") LocalDate date) {
        this.date = date;
    }

    public Long getCountReadBooks() {
        return countReadBooks;
    }

    public void setCountReadBooks(Long countReadBooks) {
        this.countReadBooks = countReadBooks;
    }
}
