package com.read.dima.LibDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


public class User_BookDTO {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate dateTake;
    private LocalDate dateBack;
    private LocalDate dateCurrentBack;
    private Boolean bookBack;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public Boolean getBookBack() {
        return bookBack;
    }

    public void setBookBack(Boolean bookBack) {
        this.bookBack = bookBack;
    }

    public LocalDate getDateCurrentBack() {
        return dateCurrentBack;
    }

    public void setDateCurrentBack(LocalDate dateCurrentBack) {
        this.dateCurrentBack = dateCurrentBack;
    }
}
