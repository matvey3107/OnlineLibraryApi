package com.read.dima.Mappers;

import com.read.dima.LibDTO.BookDTO;
import com.read.dima.Models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "id", target = "id")
    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);
}
