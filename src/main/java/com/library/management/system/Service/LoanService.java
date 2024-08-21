package com.library.management.system.Service;

import com.library.management.system.book.Book;
import com.library.management.system.book.BookEntity;
import com.library.management.system.book.BookRepository;
import com.library.management.system.exception.type.IdNotFoundException;
import com.library.management.system.loan.Loan;
import com.library.management.system.loan.LoanEntity;
import com.library.management.system.loan.LoanMapper;
import com.library.management.system.loan.LoanRepository;
import com.library.management.system.user.User;
import com.library.management.system.user.UserEntity;
import com.library.management.system.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public boolean borrowBook(Long userId, Long bookId) throws IdNotFoundException {

        UserEntity user = userRepository.findById(userId).orElseThrow(() ->new  IdNotFoundException("",userId));
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() ->new  IdNotFoundException("",userId));

        if (!book.isAvailable()) {
            return false;
        }


        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.minusDays(10);
        LoanEntity loan = LoanEntity.builder()
                .user(user)
                .book(book)
                .dueDate(dueDate)
                .dueDate(dueDate)
                .build();
        loanRepository.save(loan);


        book.setAvailable(false);
        bookRepository.save(book);

        return true;
    }

    @Transactional
    public boolean returnBook(Long userId, Long bookId) throws IdNotFoundException {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->new  IdNotFoundException("",userId));
        BookEntity book = bookRepository.findById(bookId).orElseThrow(() ->new  IdNotFoundException("",userId));


        LoanEntity loan = loanRepository.findByUserAndBook(user, book);

        if (loan == null || loan.getReturnDate() != null) {
            return false;
        }


        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        book.setAvailable(true);
        bookRepository.save(book);

        return true;
    }

    public List<Loan> userHistory(Long userId) throws IdNotFoundException {
        UserEntity user = userRepository.findById(userId).orElseThrow(() ->new  IdNotFoundException("",userId));
        return loanRepository.findByUser(user).stream().map(LoanMapper::toModel).toList();

}


}
