package com.dwit.developers.springrestservice.exception;

public class PageOutOfRangeException extends RuntimeException {
    public PageOutOfRangeException(String message) {
        super(message);
    }
}