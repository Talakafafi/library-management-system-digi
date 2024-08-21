package com.library.management.system.loan;

import com.library.management.system.book.BookEntity;
import com.library.management.system.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class Loan {
    private UserEntity user;
    private BookEntity book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate dueDate;
}
