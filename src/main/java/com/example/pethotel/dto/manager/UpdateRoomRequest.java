package com.example.pethotel.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRoomRequest {
    private String roomType;
    private String roomName;
    private int roomPrice;
    private int limitGuest;
    private int limitPet;
    private String checkIn;
    private String checkOut;

    private String roomAmenities;

    private Map<String, String> features;

//    private String bedType;
//    private String viewType;
//    private String pool;
//    private String roomCount;
//    private String bathCount;
//    private String balcony;
//    private String kitchen;
}
