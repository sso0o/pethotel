package com.example.pethotel.dto.manager;

import com.example.pethotel.entity.Hotel;
import com.example.pethotel.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomRequest {

    private String roomType;
    private String roomName;
    private int roomPrice;
    private int limitGuest;
    private int limitPet;
    private String checkIn;
    private String checkOut;
    private Long hotelId;

    private String roomAmenities;
//    private String roomFeatures;

    private String bedType;
    private String viewType;
    private String pool;
    private String roomCount;
    private String bathCount;
    private String balcony;
    private String kitchen;

    public Room toEntity(Hotel hotel) {
        return Room.builder()
                .roomType(roomType)
                .roomName(roomName)
                .roomPrice(roomPrice)
                .limitGuest(limitGuest)
                .limitPet(limitPet)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .hotel(hotel)
                .build();
    }
}
