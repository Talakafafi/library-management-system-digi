package com.library.management.system.book;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public static Book toModel(BookEntity entity){
        if(entity==null){
            return null;
        }

        return Book.builder()
                .title(entity.getTitle())
                .publisher(entity.getPublisher())
                .ISBN(entity.getISBN())
                .publishingYear(entity.getPublishingYear())
                .category(entity.getCategory())
                .author(entity.getAuthor())
                .description(entity.getDescription())
                .build();
    }

    public static BookEntity toEntity(Book model){
        if(model==null){
            return null;
        }

        return BookEntity.builder()
                .title(model.getTitle())
                .publisher(model.getPublisher())
                .ISBN(model.getISBN())
                .publishingYear(model.getPublishingYear())
                .category(model.getCategory())
                .author(model.getAuthor())
                .description(model.getDescription())
                .build();
    }
}
