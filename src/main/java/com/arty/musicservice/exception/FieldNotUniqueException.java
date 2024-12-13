package com.arty.musicservice.exception;

public class FieldNotUniqueException extends RuntimeException {
    public FieldNotUniqueException(String fieldName, String value) {
        super(String.format("%s with value '%s' already exists.", fieldName, value));
    }
}