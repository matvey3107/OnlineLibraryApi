package com.read.dima.Service;

import com.read.dima.LibDTO.BookDTO;
import com.read.dima.Models.Book;


import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getById(Long id);
    void addBook(BookDTO dto);
    BookDTO updateBook(Long id, BookDTO dto);
    void deleteBook(Long id);
    List<BookDTO> getAllByAuthor(String avtor);
    List<BookDTO> getAllByTitle(String name);
}
