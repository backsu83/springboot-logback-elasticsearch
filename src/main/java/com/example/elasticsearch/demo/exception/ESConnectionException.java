package com.example.elasticsearch.demo.exception;

public class ESConnectionException extends RuntimeException {
    public ESConnectionException(String message) {
        super(message);
    }
}
