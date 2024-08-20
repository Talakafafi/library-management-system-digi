package com.library.management.system.library;

import jakarta.persistence.Entity;

public class LibraryMapper {
    public static Library toModel(LibraryEntity entity){
        if(entity==null){
            return null;
        }
        return Library.builder()
                .name("")
                .contactInformation("")
                .location("")
                .build();
    }

    public static LibraryEntity toEntity(LibraryEntity model){
        if(model==null){
            return null;
        }
        return LibraryEntity.builder()
                .name("")
                .location("")
                .contactInformation("")
                .build();
    }

}
