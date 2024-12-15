package com.example.pethotel.dto.manager;

import lombok.Getter;

import java.util.UUID;

@Getter
public class HotelRequestResponse {
    private UUID bookingId;
    private String paymentId;
    private String roomType;
    private Long roomId;
    private Long hotelId;
    private String startDate;
    private String endDate;
    private int totalPrice;
    private int totalDate;
    private int bookingGuest;
    private int bookingPet;

    public HotelRequestResponse(UUID bookingId, String paymentId, String roomType,
                                Long roomId, Long hotelId,
                                String startDate, String endDate,
                                int totalPrice, int totalDate,
                                int bookingGuest, int bookingPet) {
        this.bookingId = bookingId;
        this.paymentId = paymentId;
        this.roomType = roomType;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.totalDate = totalDate;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;

    }
}
