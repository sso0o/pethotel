package com.example.pethotel.dto.hotel;

public class ValuableRoomResponse {
    private Long roomId;
    private String roomType;
    private Integer roomPrice;
    private Integer limitGuest;
    private Integer limitPet;
    private String checkIn;
    private String checkOut;
    // Constructor
    public ValuableRoomResponse(Long roomId, String roomType,
                               Integer roomPrice, Integer limitGuest, Integer limitPet, String checkIn, String checkOut) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.limitGuest = limitGuest;
        this.limitPet = limitPet;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
