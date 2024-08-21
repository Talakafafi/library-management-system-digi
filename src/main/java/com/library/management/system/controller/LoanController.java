package com.library.management.system.controller;

import com.library.management.system.Service.LoanService;
import com.library.management.system.exception.type.IdNotFoundException;
import com.library.management.system.loan.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) throws IdNotFoundException {
            boolean result = loanService.borrowBook(userId, bookId);
            if (result) {
                return ResponseEntity.ok("Book borrowed successfully.");
            } else {
                return ResponseEntity.status(400).body("Book is not available.");
            }

    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long bookId) throws IdNotFoundException {
            boolean result = loanService.returnBook(userId, bookId);
            if (result) {
                return ResponseEntity.ok("Book returned successfully.");
            } else {
                return ResponseEntity.status(400).body("Loan record not found or book already returned.");
            }
    }

    @GetMapping("/user-history/{userId}")
    public List<Loan> getUserHistory(@PathVariable Long userId) throws IdNotFoundException {
        return loanService.userHistory(userId);

    }
}
