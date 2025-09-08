package com.read.dima.Repo;

import com.read.dima.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query("SELECT p FROM Book p WHERE LOWER(p.avtor) LIKE LOWER (CONCAT('%', :avtor, '%'))")
    List<Book> findAllByAuthor(@Param("avtor")String avtor);
    @Query("SELECT p FROM Book p WHERE LOWER(p.name) LIKE LOWER (CONCAT('%', :name, '%'))")
    List<Book> findAllByTitle(@Param("name")String name);
}
