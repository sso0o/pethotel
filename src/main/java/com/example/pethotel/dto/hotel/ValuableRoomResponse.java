package com.example.pethotel.dto.hotel;

public class ValuableRoomResponse {
    private Long roomId;
    private String roomName;
    private String roomType;
    private String roomInfo;
    private Integer roomPrice;
    private Integer limitGuest;
    private Integer limitPet;
    private String checkIn;
    private String checkOut;
    // Constructor
    public ValuableRoomResponse(Long roomId, String roomName, String roomType, String roomInfo,
                               Integer roomPrice, Integer limitGuest, Integer limitPet, String checkIn, String checkOut) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomInfo = roomInfo;
        this.roomPrice = roomPrice;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
