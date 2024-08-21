package com.library.management.system.book;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Table(name = "Books")
@Entity
@Builder
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "book_id")
    private Long id;

    @Column(name ="book_name")
    private String title;

    @Column(name="book_description")
    private String description;

    @ManyToOne
    @JoinColumn(name="ref_author_id")
    private AuthorEntity author;

    @ManyToOne
    @JoinColumn(name="ref_publisher_id")
    private PublisherEntity publisher;

    @Enumerated
    @Column(name = "book_category")
    private  Category category;

    @Column(name="book_ISBN")
    private Long ISBN;

    @Column(name="book_publishing_year")
    private Date publishingYear;

    @Column(name="book_IsAvailable")
    private boolean isAvailable;
}
