package com.read.dima.Service;

import com.read.dima.LibDTO.BookDTO;
import com.read.dima.Mappers.BookMapper;
import com.read.dima.Models.Book;
import com.read.dima.Repo.BookRepo;
import com.read.dima.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServicempl implements BookService{
    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    @Autowired
    public BookServicempl(BookRepo bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }
    @Override
    public List<BookDTO> getAllBooks(){
        return bookRepo.findAll().stream().map(bookMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    public BookDTO getById(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Cannot find id " + id));
        return bookMapper.toDTO(book);
    }

    @Override
    public void addBook(BookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        bookRepo.save(book);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO dto) {
        Book updatesBook = bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cannot find book by id" + id));

        updatesBook.setName(dto.getName());
        updatesBook.setAvtor(dto.getAvtor());
        updatesBook.setYear(dto.getYear());
        updatesBook.setGenre(dto.getGenre());
        bookRepo.save(updatesBook);
        return bookMapper.toDTO(updatesBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Cannot find book by id" + id));
        bookRepo.deleteById(id);
    }

    @Override
    public List<BookDTO> getAllByAuthor(String avtor) {
        return bookRepo.findAllByAuthor(avtor).stream().map(bookMapper::toDTO).toList();
    }

    @Override
    public List<BookDTO> getAllByTitle(String name) {
        return bookRepo.findAllByTitle(name).stream().map(bookMapper::toDTO).toList();
    }

}
