package com.library.management.system.loan;

import com.library.management.system.book.BookEntity;
import com.library.management.system.library.LibraryEntity;
import com.library.management.system.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
    public interface LoanRepository extends JpaRepository<LoanEntity,Long> {
        List<LoanEntity> findByUser(UserEntity user);

         LoanEntity findByUserAndBook(UserEntity user, BookEntity book);
}

