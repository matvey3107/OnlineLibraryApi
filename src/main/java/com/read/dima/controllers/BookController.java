package com.read.dima.controllers;

import com.read.dima.LibDTO.BookDTO;
import com.read.dima.Service.BookServicempl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
private final BookServicempl bookService;

    @Autowired
    public BookController(BookServicempl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAll")
    public List<BookDTO> getAllBooks(){return bookService.getAllBooks();}

    @GetMapping("/getAllByAuthor/{avtor}")
    public List<BookDTO> getAllByAuthor(@PathVariable String avtor){return bookService.getAllByAuthor(avtor);}

    @GetMapping("/getAllByTitle/{name}")
    public List<BookDTO> getAllByTitle(@PathVariable String name){return bookService.getAllByTitle(name);}

    @GetMapping("/getById/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id){return ResponseEntity.ok(bookService.getById(id));}


    @PostMapping("/add")
    public ResponseEntity<String> addBook(@Valid @RequestBody BookDTO dto){
        bookService.addBook(dto);
        return ResponseEntity.ok("saved");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@Valid @RequestBody BookDTO dto){
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("deleted");
    }

}
