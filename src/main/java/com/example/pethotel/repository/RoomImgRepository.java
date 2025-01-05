package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomImgRepository extends JpaRepository<RoomImg, Long> {
    void deleteAllByRoom(Room room);

    List<RoomImg> findByRoom(Room room);
}
