package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Long> {
    void deleteByRoom(Room room);
    RoomFeature findByRoom(Room room);
    void deleteAllByRoom(Room room);
}
