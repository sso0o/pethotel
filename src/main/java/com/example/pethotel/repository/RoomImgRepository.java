package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomImgRepository extends JpaRepository<RoomImg, Long> {
    void deleteAllByRoom(Room room);
}
