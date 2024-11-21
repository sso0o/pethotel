package com.example.pethotel.service;

import com.example.pethotel.exception.InvalidlValueException;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    // 필수 항목 체크 메서드
    public void checkRequiredField(Object field, String errorMessage) {
        if (field == null || (field instanceof String && ((String) field).trim().isEmpty())) {
            throw new InvalidlValueException(errorMessage);
        }
        // 숫자일 경우 0 체크 (Integer or Long or Double 등)
        if (field instanceof Number && ((Number) field).doubleValue() <= 0) {
            throw new InvalidlValueException(errorMessage);
        }
    }
}