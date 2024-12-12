package com.example.pethotel.dto.manager;

import lombok.Getter;

@Getter
public class HotelBookingResponse {

    private Long roomId;
    private Long hotelId;
    private String startDate;
    private String endDate;
    private int bookingGuest;
    private int bookingPet;
    private int totalPrice;
    private int totalDate;
    private String paymentId;

    private String hotelName;
    private String roomName;

    public HotelBookingResponse( Long roomId, Long hotelId, String startDate, String endDate,
            int bookingGuest, int bookingPet, int totalPrice,
            int totalDate, String paymentId, String hotelName, String roomName ) {
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;
        this.totalPrice = totalPrice;
        this.totalDate = totalDate;
        this.paymentId = paymentId;
        this.hotelName = hotelName;
        this.roomName = roomName;
    }

}
