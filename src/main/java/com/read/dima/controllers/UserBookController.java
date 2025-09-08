package com.read.dima.controllers;

import com.read.dima.LibDTO.UserDTO;
import com.read.dima.LibDTO.User_BookDTO;
import com.read.dima.Models.Book;
import com.read.dima.Models.User;
import com.read.dima.Models.User_Book;
import com.read.dima.Service.User_BookServicempl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UserBook")
public class UserBookController {

private final User_BookServicempl userBookService;
    public UserBookController(User_BookServicempl userBookService) {
        this.userBookService = userBookService;
    }
    @GetMapping("/getAllByUserId/{id}")
    public List<User_BookDTO> getById(@PathVariable Long id){
        return userBookService.getAllByUserId(id);
    }
    @PostMapping("/Borrow")
    public ResponseEntity<String> BorrowBook(@Valid @RequestBody User_BookDTO dto){
        userBookService.BorrowBook(dto);
        return ResponseEntity.ok("borrowed");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User_BookDTO> updateUser(@PathVariable Long id,@Valid @RequestBody User_BookDTO dto){
        return ResponseEntity.ok(userBookService.updateUserBook(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserBook(@PathVariable Long id){
        userBookService.deleteUserBook(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/return")
    public ResponseEntity<User_BookDTO> returnBook(@RequestParam Long userId, @RequestParam Long bookId){
        return ResponseEntity.ok(userBookService.returnBook(userId, bookId));
    }

    @GetMapping("/countReadBooks/{id}")
    public ResponseEntity<Integer> countReadBooks(@PathVariable Long id){
        return ResponseEntity.ok(userBookService.CountReadBooks(id));
    }

    @GetMapping("/getAllReadingBooks")
    public List<User_BookDTO> getAllReadingBooks(){
        return userBookService.getAllReadingBooks();
    }

    @GetMapping("/getAllReadingBooks/{id}")
    public List<User_BookDTO> getAllReadingBooksByUserId(@PathVariable Long id){
        return userBookService.getAllReadingBooksByUser(id);
    }





















}

