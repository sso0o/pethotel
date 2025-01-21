package com.example.pethotel.dto.manager;

import lombok.Getter;

@Getter
public class HotelBookingResponse {

    private Long roomDetailId;
    private Long hotelId;
    private Long roomId;
    private String startDate;
    private String endDate;
    private int bookingGuest;
    private int bookingPet;
    private int totalPrice;
    private int totalDate;
    private String paymentId;
    private String payChk;

    private String hotelName;
    private String roomType;
    private String roomName;

    public HotelBookingResponse( Long roomDetailId, Long hotelId, Long roomId, String startDate, String endDate,
            int bookingGuest, int bookingPet, int totalPrice, int totalDate, String paymentId, String payChk, String hotelName, String roomType, String roomName ) {
        this.roomDetailId = roomDetailId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;
        this.totalPrice = totalPrice;
        this.totalDate = totalDate;
        this.paymentId = paymentId;
        this.payChk = payChk;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.roomName = roomName;
    }

}
