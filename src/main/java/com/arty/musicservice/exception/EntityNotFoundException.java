package com.arty.musicservice.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Integer id) {
        super(String.format("%s with ID %d not found", entityName, id));
    }
    public EntityNotFoundException(String entityName, String name) {
        super(String.format("%s with name '%s' not found", entityName, name));
    }
}