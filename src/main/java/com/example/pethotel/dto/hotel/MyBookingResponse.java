package com.example.pethotel.dto.hotel;

import lombok.Getter;

import java.util.Date;

@Getter
public class MyBookingResponse {

    private Date createdAt;
    private String hotelName;
    private Long hotelId;
    private String roomType;
    private Long roomId;
    private int bookingGuest;
    private int bookingPet;
    private String startDate;
    private String endDate;
    private int totalPrice;
    private String payChk;

    public MyBookingResponse(Date createdAt, String hotelName, Long hotelId, String roomType, Long roomId,
                             int bookingGuest, int bookingPet, String startDate, String endDate,
                             int totalPrice, String payChk){
        this.createdAt = createdAt;
        this.hotelName = hotelName;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.payChk = payChk;
    }
}
