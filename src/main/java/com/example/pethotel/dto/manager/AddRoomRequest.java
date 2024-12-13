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
    private int roomPrice;
    private int limitGuest;
    private int limitPet;
    private String checkIn;
    private String checkOut;
    private Long hotelId;

    public Room toEntity(Hotel hotel) {
        return Room.builder()
                .roomType(roomType)
                .roomPrice(roomPrice)
                .limitGuest(limitGuest)
                .limitPet(limitPet)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .hotel(hotel)
                .build();
    }
}
