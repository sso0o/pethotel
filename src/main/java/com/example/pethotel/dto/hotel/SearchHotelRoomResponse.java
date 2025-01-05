package com.example.pethotel.dto.hotel;

import lombok.Getter;

@Getter
public class SearchHotelRoomResponse {
    private Long hotelId;
    private Long roomId;
    private String roomType;
    private String roomName;
    private String checkIn;
    private String checkOut;
    private int limitGuest;
    private int limitPet;
    private int roomPrice;
    private String roomInfo;
    private Long roomCount;

    // Constructor
    public SearchHotelRoomResponse(Long hotelId, Long roomId, String roomType, String roomName, String checkIn, String checkOut,
                                   int limitGuest, int limitPet, int roomPrice, String roomInfo, Long roomCount) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomName = roomName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.roomPrice = roomPrice;
        this.roomInfo = roomInfo;
        this.roomCount = roomCount;
    }
}
