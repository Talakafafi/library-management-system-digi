package com.library.management.system.loan;


import com.library.management.system.library.LibraryEntity;

public class LoanMapper {
        public static Loan toModel(LoanEntity entity){
            if(entity==null){
                return null;
            }
            return Loan.builder()
                    .user(entity.getUser())
                    .book(entity.getBook())
                    .loanDate(entity.getLoanDate())
                    .returnDate(entity.getReturnDate())
                    .dueDate(entity.getDueDate())
                    .build();
        }

        public static LoanEntity toEntity(LoanEntity model){
            if(model==null){
                return null;
            }
            return LoanEntity.builder().user(model.getUser())
                    .book(model.getBook())
                    .loanDate(model.getLoanDate())
                    .returnDate(model.getReturnDate())
                    .dueDate(model.getDueDate())
                    .build();
        }


}
