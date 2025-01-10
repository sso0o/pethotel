package com.example.pethotel.repository;

import com.example.pethotel.dto.hotel.MyBookingResponse;
import com.example.pethotel.dto.manager.HotelBookingResponse;
import com.example.pethotel.dto.manager.HotelRequestResponse;
import com.example.pethotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query(value = "SELECT new com.example.pethotel.dto.manager.HotelBookingResponse( " +
            "b.roomDetailId, b.hotelId, b.startDate, b.endDate, b.bookingGuest, b.bookingPet, b.totalPrice, b.totalDate, b.paymentId, h.hotelName, r.roomType ) " +
            "FROM Booking b " +
            "left join Room r on b.roomDetailId = r.roomId " +
            "left join Hotel h on b.hotelId = h.hotelId " +
            "WHERE b.hotelId = :hotelId")
    List<HotelBookingResponse> findBookingResponseByHotelId(Long hotelId);

    @Query(value = "SELECT new com.example.pethotel.dto.manager.HotelRequestResponse( " +
            "b.bookingId, b.paymentId, r.roomType, b.roomId, b.hotelId, b.startDate, b.endDate, b.totalPrice, b.totalDate, b.bookingGuest, b.bookingPet ) " +
            "FROM Booking b " +
            "left join Room r on b.roomId = r.roomId " +
            "WHERE b.hotelId = :hotelId " +
            "AND b.payChk = 'Success' " +
            "AND b.paymentId IS NOT NULL")
    List<HotelRequestResponse> findAllByHotelIdAndPayChk(Long hotelId);

    @Query(value = "SELECT d " +
            "FROM  Booking b " +
            "RIGHT OUTER JOIN RoomDetail d on d.roomDetailId = b.roomDetailId " +
            "WHERE b.roomId = :roomId " +
            "AND b.payChk = 'paid' " +
            "AND b.paymentId IS NOT NULL " +
            "AND (b.startDate BETWEEN :startDate and :endDate OR b.endDate BETWEEN :startDate and :endDate)")
    List<Booking> findAllByRoomIdAndStartDateBetween(Long roomId, String startDate, String endDate);

//    @Query(value = "" +
//            "WITH recursive date_range AS ( " +
//            "   SELECT b.hotel_id as hotelId, b.room_id as roomId, b.room_d_id as roomDetailId, b.start_date AS targetDate, b.total_date as totalDate, b.start_date as startDate, b.end_date as endDate, b.payment_id as paymentId " +
//            "   FROM booking b" +
//            "   WHERE b.pay_chk = 'paid' " +
//            "   AND b.start_date BETWEEN :startDate AND :endDate " +
//            "   AND b.room_id = :roomId " +
//            "   UNION ALL " +
//            "   SELECT hotelId, roomId, roomDetailId, DATE_ADD(r.targetDate, INTERVAL 1 DAY) AS targetDate, totalDate, startDate, endDate, paymentId " +
//            "   FROM date_range r" +
//            "   WHERE DATE_ADD(r.targetDate, INTERVAL 1 DAY) < r.endDate " +
//            ") " +
//            "SELECT b.hotelId, b.roomId, b.roomDetailId, b.targetDate, b.totalDate, b.startDate, b.endDate, b.paymentId " +
//            "FROM date_range b", nativeQuery = true)
//    List<Map<String, Object>> findPaidBookingResponseByRoomId(Long roomId, String startDate, String endDate);

    @Query(value = "" +
            "With recursive date_range as (" +
            "   select :startDate as target_date " +
            "   union all " +
            "   select date_add(target_date, interval 1 day) " +
            "   from date_range " +
            "   where target_date < :endDate " +
            ") " +
            "select d.room_name, d.roomdetail_id, " +
            "group_concat(" +
            "   case " +
            "      when ( select count(*) from vw_paid_booking v where v.target_date = r.target_date and v.roomdetail_id = d.roomdetail_id) then 'X' " +
            "      else 'O' " +
            "   end " +
            "   Order by r.target_date separator ', ' ) as days " +
            "from roomdetail d " +
            "join date_range r on 1=1 " +
            "where d.room_id = :roomId " +
            "Group by d.room_name, d.roomdetail_id " +
            "order by d.room_name", nativeQuery = true)
    List<Object[]> findRoomBookingStatus(Long roomId, String startDate, String endDate);



    @Query(value = "SELECT  " +
            " b.createdAt AS bookingDate, h.hotelName AS hotelName, b.hotelId AS hotelId, r.roomType AS roomType, b.roomId AS roomId, " +
            " b.bookingGuest AS bookingGuest,  b.bookingPet AS bookingPet, b.startDate AS startDate, b.endDate AS endDate, " +
            " b.totalPrice AS totalPrice, b.totalDate AS totalDate, " +
            " b.payChk AS payChk, b.paymentId AS paymentId " +
            "FROM Booking b " +
            "left join Hotel h on b.hotelId = h.hotelId " +
            "left join Room r on b.roomId = r.roomId " +
            "where b.userId = :userid " +
            "and b.paymentId is not null " +
            "and b.payChk = :payChk")
    List<Map<String, Object>> findPaidOrCompleteByUserid(Long userid, String payChk);

    @Query(value = "SELECT  " +
            " b.createdAt AS bookingDate, h.hotelName AS hotelName, b.hotelId AS hotelId, r.roomType AS roomType, b.roomId AS roomId, " +
            " b.bookingGuest AS bookingGuest,  b.bookingPet AS bookingPet, b.startDate AS startDate, b.endDate AS endDate, " +
            " b.totalPrice AS totalPrice, b.totalDate AS totalDate, " +
            " b.payChk AS payChk, b.paymentId AS paymentId " +
            "FROM Booking b " +
            "left join Hotel h on b.hotelId = h.hotelId " +
            "left join Room r on b.roomId = r.roomId " +
            "where b.userId = :userid " +
            "and b.payChk = :payChk ")
    List<Map<String, Object>> findPendingOrCancelByUserid(Long userid, String payChk);




}
































