package com.example.pethotel.controller;

import com.example.pethotel.exception.InvalidlValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidlValueException.class)
    public ResponseEntity<?> handleInvalidValueException(InvalidlValueException ex) {
        // 예외가 발생하면 400 Bad Request와 예외 메시지를 전달
        HashMap<Object, Object> errorResponse = new HashMap<>();
        errorResponse.put("msg", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // IllegalArgumentException 예외 처리
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        HashMap<Object, Object> errorMap = new HashMap<>();
        errorMap.put("msg", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // MultipartException 예외 처리
    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<?> handleMultipartException(MultipartException ex) {
        HashMap<Object, Object> errorMap = new HashMap<>();
        errorMap.put("msg", "파일 업로드 오류: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }

    // FileNotFoundException 예외 처리
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<?> handleFileNotFoundException(FileNotFoundException ex) {
        HashMap<Object, Object> errorMap = new HashMap<>();
        errorMap.put("msg", "파일을 찾을 수 없습니다: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMap);
    }

    // 기타 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        HashMap<Object, Object> errorMap = new HashMap<>();
        errorMap.put("msg", "서버 내부 오류 발생: " + ex.getMessage());
        errorMap.put("message", ex.toString());
        errorMap.put("cause", ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());
        // 억제된 예외 (있다면)
        Throwable[] suppressed = ex.getSuppressed();
        if (suppressed.length > 0) {
            List<String> suppressedExceptions = new ArrayList<>();
            for (Throwable s : suppressed) {
                suppressedExceptions.add(s.toString());
            }
            errorMap.put("suppressed", suppressedExceptions);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }
}
