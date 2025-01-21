package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Long> {
    void deleteByRoom(Room room);
    List<RoomFeature> findByRoom(Room room);
    void deleteAllByRoom(Room room);
}
