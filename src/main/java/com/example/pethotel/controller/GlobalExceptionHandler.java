package com.example.pethotel.controller;

import com.example.pethotel.exception.InvalidlValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidlValueException.class)
    public ResponseEntity<?> handleInvalidValueException(InvalidlValueException ex) {
        // 예외가 발생하면 400 Bad Request와 예외 메시지를 전달
        HashMap<String, String> errorResponse = new HashMap<>();
        errorResponse.put("msg", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // IllegalArgumentException 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("msg", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // MultipartException 예외 처리
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultipartException(MultipartException ex) {
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("msg", "파일 업로드 오류: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // FileNotFoundException 예외 처리
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex) {
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("msg", "파일을 찾을 수 없습니다: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

    // 기타 예외 처리 (예시)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        HashMap<String, String> errorMap = new HashMap<>();
        errorMap.put("msg", "서버 내부 오류 발생: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }
}
