package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomAmenityRepository extends JpaRepository<RoomAmenity, Long> {
    List<RoomAmenity> findAllByRoom(Room room);

    void deleteAllByRoom(Room room);
}
