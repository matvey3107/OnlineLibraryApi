package com.read.dima.LibDTO;

import com.read.dima.Models.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Avtor is required")
    private String avtor;
    @NotNull(message = "year is required")
    @Min(value = 1)
    @Max(value = 2025)
    private Integer year;
    private Genre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Avtor is required") String getAvtor() {
        return avtor;
    }

    public void setAvtor(@NotBlank(message = "Avtor is required") String avtor) {
        this.avtor = avtor;
    }

    public @NotNull(message = "year is required") @Min(value = 1) @Max(value = 2025) Integer getYear() {
        return year;
    }

    public void setYear(@NotNull(message = "year is required") @Min(value = 1) @Max(value = 2025) Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
