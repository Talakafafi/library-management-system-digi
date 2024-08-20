package com.library.management.system.Service;

import com.library.management.system.book.Book;
import com.library.management.system.book.BookEntity;
import com.library.management.system.book.BookMapper;
import com.library.management.system.book.BookRepository;
import com.library.management.system.dto.BookRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public Book addBook(BookRequestDto bookRequestDto){
        BookEntity book=BookEntity.builder()
                .title(bookRequestDto.getTitle())
                .description(bookRequestDto.getDescription())
                .publishingYear(bookRequestDto.getPublishingYear())
                .ISBN(bookRequestDto.getISBN())
                .category(bookRequestDto.getCategory())
                .build();
return BookMapper.toModel(book);
    }


}
