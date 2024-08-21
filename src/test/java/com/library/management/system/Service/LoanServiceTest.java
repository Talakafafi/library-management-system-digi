package com.library.management.system.Service;

import com.library.management.system.loan.Loan;
import com.library.management.system.loan.LoanEntity;
import com.library.management.system.loan.LoanRepository;
import com.library.management.system.user.UserEntity;
import com.library.management.system.user.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import com.library.management.system.LibraryManagementSystemApplication;
import com.library.management.system.book.*;

import com.library.management.system.exception.type.IdNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = LibraryManagementSystemApplication.class)
class LoanServiceTest {

    @InjectMocks
    LoanService loanService;

    @Mock
    UserRepository userRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    LoanRepository loanRepository;

    @Test
    void borrowBook_succeseded() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        BookEntity book = BookEntity.builder().id(1L).isAvailable(true).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        boolean result = loanService.borrowBook(1L, 1L);

        assertTrue(result);
    }

    @Test
    void borrowBook_bookNotAvailable() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        BookEntity book = BookEntity.builder().id(1L).isAvailable(false).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        boolean result = loanService.borrowBook(1L, 1L);

        assertFalse(result);

    }

    @Test
    void borrowBook_userNotFound_throwsException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {loanService.borrowBook(1L, 1L);});
    }

    @Test
    void borrowBook_bookNotFound_throwsException() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> {loanService.borrowBook(1L, 1L);});
    }

    @Test
    void returnBook_success() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        BookEntity book = BookEntity.builder().id(1L).isAvailable(false).build();
        LoanEntity loan = LoanEntity.builder().user(user).book(book).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findByUserAndBook(user, book)).thenReturn(loan);

        boolean result = loanService.returnBook(1L, 1L);

        assertTrue(result);
    }

    @Test
    void returnBook_loanNotFound() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        BookEntity book = BookEntity.builder().id(1L).isAvailable(false).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findByUserAndBook(user, book)).thenReturn(null);

        boolean result = loanService.returnBook(1L, 1L);

        assertFalse(result);

    }

    @Test
    void returnBook_loanAlreadyReturned() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        BookEntity book = BookEntity.builder().id(1L).isAvailable(true).build();
        LoanEntity loan = LoanEntity.builder().user(user).book(book).returnDate(LocalDate.now()).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findByUserAndBook(user, book)).thenReturn(loan);

        boolean result = loanService.returnBook(1L, 1L);

        assertFalse(result);
    }

    @Test
    void userHistory() throws IdNotFoundException {
        UserEntity user = UserEntity.builder().id(1L).build();
        LoanEntity loan = LoanEntity.builder().user(user).build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(loanRepository.findByUser(user)).thenReturn(List.of(loan));

        List<Loan> result = loanService.userHistory(1L);

        assertEquals(1, result.size());
    }

    @Test
    void userHistory_userNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class, () -> { loanService.userHistory(1L);});
    }
}

