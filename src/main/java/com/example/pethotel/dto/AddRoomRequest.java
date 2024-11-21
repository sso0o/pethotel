package com.example.pethotel.dto;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomRequest {

    private String roomName;
    private String roomType;
    private int roomPrice;
    private int limitGuest;
    private int limitPet;
    private String checkIn;
    private String checkOut;
    private String roomInfo;
    private Long hotelId;

    public Room toEntity(Hotel hotel) {
        return Room.builder()
                .roomName(roomName)
                .roomType(roomType)
                .roomPrice(roomPrice)
                .limitGuest(limitGuest)
                .limitPet(limitPet)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .roomInfo(roomInfo)
                .hotel(hotel)
                .build();
    }
}
