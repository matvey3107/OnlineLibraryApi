package com.read.dima.Service;

import com.read.dima.LibDTO.User_BookDTO;
import com.read.dima.Mappers.User_BookMapper;
import com.read.dima.Models.Book;
import com.read.dima.Models.User;
import com.read.dima.Models.User_Book;
import com.read.dima.Repo.BookRepo;
import com.read.dima.Repo.UserRepo;
import com.read.dima.Repo.User_BookRepo;
import com.read.dima.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class User_BookServicempl implements User_BookService {

    private final User_BookRepo userBookRepo;
    private final User_BookMapper userBookMapper;
    private final UserRepo userRepo;
    private final BookRepo bookRepo;

    @Autowired
    public User_BookServicempl(User_BookRepo userBookRepo, User_BookMapper userBookMapper, UserRepo userRepo, BookRepo bookRepo) {
        this.userBookRepo = userBookRepo;
        this.userBookMapper = userBookMapper;
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
    }


    @Override
    public List<User_BookDTO> getAllByUserId(Long id) {
        return userBookRepo.findAllByUserId(id).stream().map(userBookMapper::toDTO).toList();
    }

    @Override
    public Integer CountReadBooks(Long id) {
        return userBookRepo.CountReadBooks(id);
    }

    @Override
    public List<User_BookDTO> getAllReadingBooks() {
        return userBookRepo.findAllReadingBooks().stream().map(userBookMapper::toDTO).toList();
    }

    @Override
    public List<User_BookDTO> getAllReadingBooksByUser(Long id) {
        return userBookRepo.findAllReadingBooksByUser(id).stream().map(userBookMapper::toDTO).toList();
    }



    @Override
    public User_BookDTO updateUserBook(Long id, User_BookDTO dto) {
        User_Book userBookToUpdate = userBookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find  by id:" + id));
        userBookToUpdate.setDateBack(dto.getDateBack());
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            userBookToUpdate.setUser(user);
        }
        if (dto.getBookId() != null) {
            Book book = bookRepo.findById(dto.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            userBookToUpdate.setBook(book);
        }
        userBookRepo.save(userBookToUpdate);
        return userBookMapper.toDTO(userBookToUpdate);
    }

    @Override
    public void deleteUserBook(Long Id) {
        User_Book user_book = userBookRepo.findById(Id).orElseThrow(()-> new ResourceNotFoundException("id not found"));
        userBookRepo.delete(user_book);
    }

    @Override
    public void BorrowBook(User_BookDTO dto) {
        List<User_Book> userBooki = userBookRepo.findAllReadingBooksByUser(dto.getUserId());
        if (userBooki.isEmpty()){
        User_Book user_book = userBookMapper.toEntity(dto);
        if (dto.getUserId() != null) {
            User user = userRepo.findById(dto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            user_book.setUser(user);
        }
        if (dto.getBookId() != null) {
            Book book = bookRepo.findById(dto.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            user_book.setBook(book);
        }
        user_book.setDateTake(LocalDate.now());
        user_book.setDateBack(LocalDate.now().plusMonths(1));
        user_book.setBookBack(false);
        userBookRepo.save(user_book);
    }else{
            System.out.println("Return Book first!!!!!!!!!!!!!!!!!!!!");
        }

    }

    @Override
    public User_BookDTO returnBook(Long userId, Long bookId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Book book = bookRepo.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book not found"));
        List<User_Book> reservations = userBookRepo.findAll();
        for(User_Book ub:reservations){
            if(user.getId().equals(ub.getUser().getId()) && book.getId().equals(ub.getBook().getId())){
                if(!ub.isBookBack()){
                    User_Book user_book = userBookRepo.FindByUserAndBook(userId, bookId);
                    user_book.setDateCurrentBack(LocalDate.now());
                    user_book.setBookBack(true);
                    userBookRepo.save(user_book);
                    return userBookMapper.toDTO(user_book);
                }
                else{
                    System.out.println("This book already returned");
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void CheckDateBackToReturn() {
        List<User_Book> userBooki = userBookRepo.findAllReadingBooks();
        for(User_Book ub: userBooki){
            if(ub.getDateBack().isAfter(LocalDate.now())){
                ub.setBookBack(true);
                User_BookDTO ubDTO = userBookMapper.toDTO(ub);
                this.updateUserBook(ub.getId(),ubDTO);
                userBookRepo.save(ub);
            }
        }

    }

}
