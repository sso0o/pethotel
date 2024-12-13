package com.example.pethotel.dto.manager;

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
    private String rimgUrl;

    public RoomImg toEntity() {
        return RoomImg.builder()
                .room(room)
                .rimgFile(rimgFile)
                .rimgUrl(rimgUrl)
                .build();
    }
}
