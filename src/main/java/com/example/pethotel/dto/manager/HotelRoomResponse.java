package com.example.pethotel.dto.manager;

import lombok.Getter;

@Getter
public class HotelRoomResponse {
    private String roomType;
    private Integer roomPrice;
    private Integer limitGuest;
    private Integer limitPet;
    private String checkIn;
    private String checkOut;
    private Long roomCount;

    public HotelRoomResponse(String roomType,
                             Integer roomPrice, Integer limitGuest, Integer limitPet,
                             String checkIn, String checkOut, Long roomCount){
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomCount = roomCount;
    }
}
