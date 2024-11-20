package com.example.pethotel.dto;

import com.example.pethotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomRequest {

    private String roomName;
    private String roomType;
    private int roomPrice;
    private int limitGuset;
    private int limitPet;
    private String checkIn;
    private String checkOut;
    private String roomInfo;
    private Long hotelId;

    public Room toEntity() {
        return Room.builder()
                .roomName(roomName)
                .roomType(roomType)
                .roomPrice(roomPrice)
                .limitGuest(limitGuset)
                .limitPet(limitPet)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .roomInfo(roomInfo)
                .hotelId(hotelId)
                .build();
    }



}
