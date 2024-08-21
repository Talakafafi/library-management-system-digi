package com.library.management.system.dto;

import com.library.management.system.book.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class BookSearchDto {
    private String title;
    private String description;
    private Category category;
    private String authorName;
    private String publisherName;
    private Long ISBN;
    private Date publishingYear;
}
