package com.example.pethotel.dto.manager;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateRoomRequest {
    private String roomType;
    private int roomPrice;
    private int limitGuest;
    private int limitPet;
    private String checkIn;
    private String checkOut;

    private String roomAmenities;
}
