package com.example.pethotel.dto.hotel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SearchHotelRequest {
    private String filter;
    private int filterSize;
    private String location;
    private String hotelType;
    private String checkIn;
    private String checkOut;
    private int room;
    private int guest;
    private int pet;

    private int dateDiff;

    // 생성자에서 날짜 차이를 자동으로 계산하도록 처리
    public SearchHotelRequest(String filter, int filterSize, String location, String hotelType,
                              String checkIn, String checkOut, int room, int guest, int pet) {
        this.filter = filter;
        this.filterSize = filterSize;
        this.location = location;
        this.hotelType = hotelType;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
        this.guest = guest;
        this.pet = pet;

        // 날짜 차이를 자동으로 계산
        calculateDateDiff();
    }

    public void calculateDateDiff(){
        // 날짜 포맷에 맞게 LocalDate로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate checkInDate = LocalDate.parse(this.checkIn, formatter);
        LocalDate checkOutDate = LocalDate.parse(this.checkOut, formatter);

        // 두 날짜의 차이를 계산하여 dateDiff에 설정
        this.dateDiff = (int) ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }
}
