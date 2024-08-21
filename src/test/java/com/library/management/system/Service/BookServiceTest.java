package com.library.management.system.Service;

import static org.junit.jupiter.api.Assertions.*;

import com.library.management.system.LibraryManagementSystemApplication;
import com.library.management.system.book.*;
import com.library.management.system.dto.BookRequestDto;
import com.library.management.system.dto.BookFiltrationDto;
import com.library.management.system.dto.BookSearchDto;
import com.library.management.system.exception.type.IdNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
class BookServiceTest {

    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    PublisherRepository publisherRepository;


    @Test
    void findAuthor_authorFound() throws IdNotFoundException {
        AuthorEntity author = AuthorEntity.builder().name("Eric Freeman").build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        AuthorEntity result = bookService.findAuthor(1L);

        assertEquals("Eric Freeman", result.getName());
    }

    @Test
    void findAuthor_authorNotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {bookService.findAuthor(1L);});
    }

    void findPublisher_publisherFound() throws IdNotFoundException {
        PublisherEntity publisher = PublisherEntity.builder().name("publisher").build();
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));

        PublisherEntity result = bookService.findPublisher(1L);

        assertEquals("publisher", result.getName());
    }

    @Test
    void findPublisher_publisherNotFound() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {bookService.findPublisher(1L);});
    }

    @Test
    void addBook_withValidAuthorAndPublisherIds() throws IdNotFoundException {
        AuthorEntity author = AuthorEntity.builder().build();
        PublisherEntity publisher =PublisherEntity.builder().build();
        BookEntity book = BookEntity.builder()
                .author(author)
                .publisher(publisher)
                .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(publisherRepository.findById(1L)).thenReturn(Optional.of(publisher));
        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .title("Book1")
                .authorId(1L)
                .publisherId(1L)
                .build();

        Book result = bookService.addBook(bookRequestDto);

        assertEquals(result.getAuthor() , book.getAuthor());
        assertEquals(result.getPublisher() , book.getPublisher());
        assertEquals(result.getTitle() , "Book1");

    }

    @Test
    void addBook_withNullAuthorAndPublisherIds() throws IdNotFoundException {
        BookEntity book = BookEntity.builder()
                .build();

        when(bookRepository.save(any(BookEntity.class))).thenReturn(book);

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .title("Book1")
                .build();

        Book result = bookService.addBook(bookRequestDto);

        assertEquals(result.getTitle(),"Book1");
        assertNull(result.getAuthor());
        assertNull(result.getPublisher());
    }

    @Test
    void addBook_authorIdNotFound_throwsException() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .title("Test Book")
                .authorId(1L)
                .build();

        assertThrows(IdNotFoundException.class, () -> bookService.addBook(bookRequestDto));
    }

    @Test
    void addBook_publisherIdNotFound_throwsException() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        BookRequestDto bookRequestDto = BookRequestDto.builder()
                .title("Test Book")
                .publisherId(1L)
                .build();

        assertThrows(IdNotFoundException.class, () -> bookService.addBook(bookRequestDto));
    }

    @Test
    void searchBooks_byDescription() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Book 1").description("A great book").build(),
                BookEntity.builder().title("Book 2").description("Another great book").build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .description("great")
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(2, searchResults.size());
    }
    @Test
    void searchBooks_byTitle() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Java Programming").build(),
                BookEntity.builder().title("Spring Framework").build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .title("Java")
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(1, searchResults.size());
        assertEquals("Java Programming", searchResults.get(0).getTitle());
    }

    @Test
    void searchBooks_byAuthorName() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Head First Design Patterns")
                        .author(AuthorEntity.builder().name("Eric Freeman").build()).build(),
                BookEntity.builder().title("Book 2")
                        .author(AuthorEntity.builder().name("Author 2").build()).build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .authorName("Eric")
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(1, searchResults.size());
        assertEquals("Head First Design Patterns", searchResults.getFirst().getTitle());
    }

    @Test
    void searchBooks_byPublisherName() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Head First Design Patterns")
                        .publisher(PublisherEntity.builder().name("publisher 1").build()).build(),
                BookEntity.builder().title("Book 2")
                        .publisher(PublisherEntity.builder().name("publisher 2").build()).build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .publisherName("publisher 2")
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(1, searchResults.size());
        assertEquals("Book 2", searchResults.getFirst().getTitle());
    }

    @Test
    void searchBooks_byCategory() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("book 1").category(Category.ROMANCE).build(),
                BookEntity.builder().title("book 2").category(Category.CHILDREN_BOOKS).build(),
                BookEntity.builder().title("book 3").category(Category.CHILDREN_BOOKS).build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .category(Category.CHILDREN_BOOKS)
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(2, searchResults.size());
    }


    @Test
    void searchBooks_byISBN() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("book 1").ISBN(1234567890L).build(),
                BookEntity.builder().title("book 2").ISBN(1987654020L).build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .ISBN(1234567890L)
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(1, searchResults.size());
        assertEquals("book 1", searchResults.getFirst().getTitle());
    }


    @Test
    void filterBooks_withAllCriteria() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Book 1").build(),
                BookEntity.builder().title("Book 2").build(),
                BookEntity.builder().title("Book 3").build()
        ).toList());

        BookFiltrationDto filtrationDto = BookFiltrationDto.builder()
                .title("Book 1")
                .build();

        List<Book> filteredBooks = bookService.filterBooks(filtrationDto);

        assertEquals(1, filteredBooks.size());
    }

    @Test
    void searchBooks_withMultipleCriteria() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Book 1").description("A great book").build(),
                BookEntity.builder().title("Book 2").description("Another great book").build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder()
                .title("Book 1")
                .description("great")
                .build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(1, searchResults.size());
    }


    @Test
    void searchBooks_withNoCriteria_returnsAllBooks() {
        when(bookRepository.findAll()).thenReturn(Stream.of(
                BookEntity.builder().title("Book 1").build(),
                BookEntity.builder().title("Book 2").build()
        ).toList());

        BookSearchDto searchDto = BookSearchDto.builder().build();

        List<Book> searchResults = bookService.searchBooks(searchDto);

        assertEquals(2, searchResults.size());
    }
}
