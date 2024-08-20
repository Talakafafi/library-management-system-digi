package com.library.management.system.exception.type;

public class IdNotFoundException extends Exception {

    public IdNotFoundException(String entityType, Long entityId) {
        super(String.format("Could not find %s with ID = %d", entityType, entityId));
    }

}
