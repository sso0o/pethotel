package com.example.pethotel.repository;

import com.example.pethotel.entity.Room;
import com.example.pethotel.entity.RoomDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long> {

    @Query(value = "SELECT rd FROM RoomDetail rd WHERE rd.room.roomId = :roomId")
    List<RoomDetail> findAllByRoomId(Long roomId);

    void deleteAllByRoom(Room room);

}
