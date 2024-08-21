package com.library.management.system.loan;

import com.library.management.system.book.BookEntity;
import com.library.management.system.user.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@Table(name="loans")
public class LoanEntity {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "loan_id")
        private Long id;

       @ManyToOne
       @JoinColumn(name="ref_user_Id")
        private UserEntity user;

       @ManyToOne
       @JoinColumn(name="ref_book_Id")
        private BookEntity book;

       @Column(name="loan_date")
        private LocalDate loanDate;

       @Column(name="loan_return_date")
        private LocalDate returnDate;

       @Column(name="loan_due_date")
        private LocalDate dueDate;

}
