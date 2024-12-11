package com.example.pethotel.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateUtils {

    // 날짜 범위를 생성하는 메서드
    public static List<Date> generateDateRange(String startDateStr, String endDateStr) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        List<Date> dateRange = new ArrayList<>();
        while (!startDate.after(endDate)) {
            dateRange.add(startDate);
            startDate = new Date(startDate.getTime() + 86400000L);  // 하루씩 증가
        }

        return dateRange;
    }

    // Date 객체를 String으로 변환하는 메서드
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

}
