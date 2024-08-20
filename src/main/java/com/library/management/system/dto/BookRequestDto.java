package com.library.management.system.dto;

import com.library.management.system.book.Author;
import com.library.management.system.book.Category;
import com.library.management.system.book.Publisher;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class BookRequestDto {
    private String title;
    private String description;
    private Category category;
    private Long authorId;
    private Long publisherId;
    private Long ISBN;
    private Date publishingYear;
}
