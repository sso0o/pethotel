package com.example.pethotel.repository;

import com.example.pethotel.entity.VwPaidBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface VwPaidBookingRepository extends JpaRepository<VwPaidBooking, UUID> {

    @Query(value = "SELECT COUNT(v) " +
            "FROM VwPaidBooking v " +
            "WHERE v.roomDetailId = :roomDetailId " +
            "AND v.targetDate >= :startDate " +
            "AND v.targetDate < :endDate ")
    Integer findByRoomDetailIdAndTargetDate(Long roomDetailId, String startDate, String endDate);
}
