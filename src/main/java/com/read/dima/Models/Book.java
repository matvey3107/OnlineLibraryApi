package com.read.dima.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,columnDefinition = "VARCHAR(50)")
    private String name;
    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String avtor;
    @Column(nullable = false,columnDefinition = "INT")
    private Integer year;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "book", cascade = CascadeType.ALL)
    private List<User_Book> books2 = new ArrayList<>();

    public Book() {
    }

    public Book(Long id, String name, String avtor, Integer year, Genre genre, List<User_Book> books2) {
        this.id = id;
        this.name = name;
        this.avtor = avtor;
        this.year = year;
        this.genre = genre;
        this.books2 = books2;
    }

    public Book(String name, String avtor, Integer year, Genre genre) {
        this.name = name;
        this.avtor = avtor;
        this.year = year;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvtor() {
        return avtor;
    }

    public void setAvtor(String avtor) {
        this.avtor = avtor;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<User_Book> getBooks2() {
        return books2;
    }

    public void setBooks2(List<User_Book> books2) {
        this.books2 = books2;
    }
}
