package com.read.dima.Mappers;


import com.read.dima.LibDTO.User_BookDTO;
import com.read.dima.Models.User_Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface User_BookMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "book.id",target = "bookId")
    @Mapping(source = "user.id",target = "userId")
    User_BookDTO toDTO(User_Book user_book);
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "user", ignore = true)
    User_Book toEntity(User_BookDTO user_bookDTO);
}
