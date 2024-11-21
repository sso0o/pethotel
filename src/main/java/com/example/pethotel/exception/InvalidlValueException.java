package com.example.pethotel.exception;

public class InvalidlValueException extends RuntimeException {
    public InvalidlValueException(String message) {
        super(message); // 예외 메시지를 부모 클래스에 전달
    }
}