package com.read.dima.Repo;

import com.read.dima.Models.User_Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface User_BookRepo extends JpaRepository<User_Book, Long> {
    @Query("SELECT p FROM User_Book p WHERE p.user.id = ?1")
    List<User_Book> findAllByUserId(Long id);
    @Query("SELECT COUNT(*) FROM User_Book p WHERE p.user.id = ?1 AND p.bookBack = TRUE")
    Integer CountReadBooks(Long id);
    @Query("SELECT p FROM User_Book p WHERE p.bookBack = FALSE")
    List<User_Book> findAllReadingBooks();
    @Query("SELECT p FROM User_Book p WHERE p.bookBack = FALSE AND p.user.id = ?1")
    List<User_Book> findAllReadingBooksByUser(Long id);
    @Query("SELECT p FROM User_Book p WHERE p.user.id = ?1 AND p.book.id = ?2")
    User_Book  FindByUserAndBook (Long userId, Long BookId);

}
