package com.example.elasticsearch.demo.exception;

public class ESClientException extends RuntimeException{
    public ESClientException(String message) {
        super(message);
    }
}
