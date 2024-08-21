package com.library.management.system.controller;


import com.library.management.system.Service.BookService;
import com.library.management.system.book.Author;
import com.library.management.system.book.Book;

import com.library.management.system.book.Publisher;
import com.library.management.system.dto.BookFiltrationDto;
import com.library.management.system.dto.BookRequestDto;
import com.library.management.system.dto.BookSearchDto;
import com.library.management.system.exception.type.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookRequestDto bookRequestDto) throws IdNotFoundException {
            Book book = bookService.addBook(bookRequestDto);
            return new ResponseEntity<>(book, HttpStatus.CREATED);
        }

    @GetMapping()
    public List<Book> getAllBooks() throws IdNotFoundException {
        return  bookService.getAllBooks();

    }
        @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws IdNotFoundException {
            Book book = bookService.findBookById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);

    }

    @GetMapping("/filter")
    public List<Book> filterBooks(BookFiltrationDto bookFiltrationDto) {
        return bookService.filterBooks(bookFiltrationDto);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(BookSearchDto bookSearchDto) {
        return bookService.searchBooks(bookSearchDto);
    }
}
