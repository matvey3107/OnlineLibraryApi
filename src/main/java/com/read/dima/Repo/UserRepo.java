package com.read.dima.Repo;

import com.read.dima.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT p FROM User p WHERE LOWER(p.username) LIKE LOWER (CONCAT('%', :username, '%'))")
    List<User> findAllByUsername(@Param("username")String username);
    Optional<User> findByUsername(String username);
    @Query("SELECT p FROM User p ORDER BY p.countReadBooks DESC LIMIT 10")
    List<User> SortByReadBooks();
}
