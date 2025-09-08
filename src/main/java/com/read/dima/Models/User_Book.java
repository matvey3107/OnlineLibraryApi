package com.read.dima.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user_book")
public class User_Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user.id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "book.id")
    private Book book;
    @Column(name = "dateTake", columnDefinition = "DATE")
    private LocalDate dateTake;
    @Column(name = "dateBack", columnDefinition = "DATE")
    private LocalDate dateBack;
    @Column(name = "dateCurrentBack",columnDefinition = "DATE")
    private LocalDate dateCurrentBack;
    @Column(name = "bookBack", columnDefinition = "BOOLEAN")
    private boolean bookBack;

    public User_Book() {
    }

    public User_Book(Long id,
                     User user,
                     Book book,
                     LocalDate dateTake,
                     LocalDate dateBack,
                     LocalDate dateCurrentBack,
                     boolean bookBack) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.dateTake = dateTake;
        this.dateBack = dateBack;
        this.dateCurrentBack = dateCurrentBack;
        this.bookBack = bookBack;
    }

    public User_Book(User user,
                     Book book,
                     LocalDate dateTake,
                     LocalDate dateBack,
                     LocalDate dateCurrentBack,
                     boolean bookBack) {
        this.user = user;
        this.book = book;
        this.dateTake = dateTake;
        this.dateBack = dateBack;
        this.dateCurrentBack = dateCurrentBack;
        this.bookBack = bookBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateTake() {
        return dateTake;
    }

    public void setDateTake(LocalDate dateTake) {
        this.dateTake = dateTake;
    }

    public LocalDate getDateBack() {
        return dateBack;
    }

    public void setDateBack(LocalDate dateBack) {
        this.dateBack = dateBack;
    }

    public LocalDate getDateCurrentBack() {
        return dateCurrentBack;
    }

    public void setDateCurrentBack(LocalDate dateCurrentBack) {
        this.dateCurrentBack = dateCurrentBack;
    }

    public boolean isBookBack() {
        return bookBack;
    }

    public void setBookBack(boolean bookBack) {
        this.bookBack = bookBack;
    }
}
