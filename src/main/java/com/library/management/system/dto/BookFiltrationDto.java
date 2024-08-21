package com.library.management.system.dto;

import com.library.management.system.book.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookFiltrationDto {
    private String title;
    private Category category;
    private String authorName;
    private String publisherName;
}
