package com.library.management.system.Service;

import com.library.management.system.book.*;
import com.library.management.system.dto.BookFiltrationDto;
import com.library.management.system.dto.BookRequestDto;
import com.library.management.system.dto.BookSearchDto;
import com.library.management.system.exception.type.IdNotFoundException;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublisherRepository publisherRepository;


    public Book addBook(BookRequestDto bookRequestDto) throws IdNotFoundException {
        AuthorEntity author;
        PublisherEntity publisher;

        if (bookRequestDto.getAuthorId()!=null ){
            author = findAuthor(bookRequestDto.getAuthorId());} else {
            author = null;
        }

        if (bookRequestDto.getPublisherId()!=null ){
            publisher = findPublisher(bookRequestDto.getPublisherId());} else {
            publisher = null;
        }

        BookEntity book=BookEntity.builder()
                .title(bookRequestDto.getTitle())
                .description(bookRequestDto.getDescription())
                .publishingYear(bookRequestDto.getPublishingYear())
                .ISBN(bookRequestDto.getISBN())
                .category(bookRequestDto.getCategory())
                .publisher(publisher)
                .author(author)
                .build();

return BookMapper.toModel(book);
    }

    public AuthorEntity findAuthor(Long authorId) throws IdNotFoundException {
        return authorRepository.findById(authorId)
                .orElseThrow(()->new IdNotFoundException("",authorId));
    }

    public PublisherEntity findPublisher(Long publisherId) throws IdNotFoundException {
        return publisherRepository.findById(publisherId)
                .orElseThrow(()->new IdNotFoundException("",publisherId));
    }

    public List<Book> filterBooks(BookFiltrationDto bookFiltration) {
        return bookRepository.findAll().stream()
                    .filter(book -> bookFiltration.getTitle() == null || book.getTitle().contains(bookFiltration.getTitle()))
                    .filter(book -> bookFiltration.getAuthorName() == null || book.getAuthor().getName().contains(bookFiltration.getAuthorName()))
                    .filter(book -> bookFiltration.getPublisherName() == null || book.getPublisher().getName().equals(bookFiltration.getPublisherName()))
                    .filter(book -> bookFiltration.getCategory() == null || book.getCategory().equals(bookFiltration.getCategory()))
                .map(BookMapper::toModel)
                .toList();
        }

    public List<Book> searchBooks(BookSearchDto bookSearch) {
        return bookRepository.findAll().stream()
                .filter(book -> bookSearch.getTitle() == null || book.getTitle().contains(bookSearch.getTitle()))
                .filter(book -> bookSearch.getAuthorName() == null || book.getAuthor().getName().contains(bookSearch.getAuthorName()))
                .filter(book -> bookSearch.getPublisherName() == null || book.getPublisher().getName().equals(bookSearch.getPublisherName()))
                .filter(book -> bookSearch.getCategory() == null || book.getCategory().equals(bookSearch.getCategory()))
                .filter(book -> bookSearch.getDescription() == null || book.getDescription().contains(bookSearch.getDescription()))
                .filter(book -> bookSearch.getISBN() == null || book.getISBN().equals(bookSearch.getISBN()))
                .filter(book -> bookSearch.getPublishingYear() == null || book.getPublishingYear().equals(bookSearch.getPublishingYear()))
                .map(BookMapper::toModel)
                .toList();
    }

    public Book findBookById(Long id) throws IdNotFoundException {
        BookEntity bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Book not found with id: ", id));

        return BookMapper.toModel(bookEntity);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toModel).toList();
    }

}


