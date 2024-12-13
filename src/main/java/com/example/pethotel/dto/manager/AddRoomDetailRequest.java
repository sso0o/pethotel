package com.example.pethotel.dto.manager;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomDetailRequest {
    private Room room;
    private String roomName;

    public RoomDetail toEntity() {
        return RoomDetail.builder()
                .room(room)
                .roomName(roomName)
                .build();
    }
}
