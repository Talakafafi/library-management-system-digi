package com.library.management.system.book;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
public class Book {
    private String title;
    private String description;
    private  Category category;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private Long ISBN;
    private Date publishingYear;
    private boolean isAvailable;


}
