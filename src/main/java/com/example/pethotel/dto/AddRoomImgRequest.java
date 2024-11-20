package com.example.pethotel.dto;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomImg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomImgRequest {

    private Room room;
    private String rimgFile;

    public RoomImg toEntity() {
        return RoomImg.builder()
                .room(room)
                .rimgFile(rimgFile)
                .build();
    }
}
