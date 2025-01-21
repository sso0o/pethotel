package com.example.pethotel.dto.manager;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddRoomFeatureRequest {
    private Room room;
    private String featureType;
    private String value;

    public RoomFeature toEntity() {
        return RoomFeature.builder()
                .room(room)
                .featureType(featureType)
                .value(value)
                .build();
    }
}
