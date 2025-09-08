package com.read.dima.Service;

import com.read.dima.LibDTO.User_BookDTO;
import com.read.dima.Models.Book;
import com.read.dima.Models.User;
import com.read.dima.Models.User_Book;

import java.util.List;

public interface User_BookService {
    List<User_BookDTO> getAllByUserId(Long id);
    Integer CountReadBooks(Long id);
    List<User_BookDTO> getAllReadingBooks();
    List<User_BookDTO> getAllReadingBooksByUser(Long id);
    void BorrowBook(User_BookDTO dto);
    User_BookDTO updateUserBook(Long id, User_BookDTO dto);
    void deleteUserBook(Long Id);
    User_BookDTO returnBook(Long userId, Long bookId);
    void CheckDateBackToReturn();
}
