package com.example.pethotel.service;

import com.example.pethotel.dto.hotel.AddBookingRequest;
import com.example.pethotel.dto.manager.HotelBookingResponse;
import com.example.pethotel.dto.manager.HotelRequestResponse;
import com.example.pethotel.entity.Booking;
import com.example.pethotel.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;

    // 예약 저장
    public Booking save(AddBookingRequest request) {
        return bookingRepository.save(request.toEntity());
    }

    // 네이버페이 결제 승인 후 처리 로직
    @Transactional
    public Booking updatePaycheck(UUID bookingId, String payChk, String paymentId) {
        Booking booking = bookingRepository.findById(bookingId)
               .orElseThrow(() -> new IllegalArgumentException("not found: " + bookingId));
        booking.updatePaycheck(payChk, paymentId);
        return bookingRepository.save(booking);
    }

    public Booking findById(UUID bookingId) {
        return bookingRepository.findById(bookingId)
               .orElseThrow(() -> new IllegalArgumentException("not found: " + bookingId));
    }

    // 호텔 아이디별 예약 가져오기(manager 사용)
    public List<HotelBookingResponse> findBookingResponseByHotelId(Long hotelId) {
        return bookingRepository.findBookingResponseByHotelId(hotelId);
    }

    // 매니저가 예약 요청 페이지에서 사용
    public List<HotelRequestResponse> findAllByHotelIdAndPayChk(Long hotelId) {
        return bookingRepository.findAllByHotelIdAndPayChk(hotelId);
    }


    public List<Booking> findAllByRoomIdAndStartDateBetween(Long roomId, String startDate, String endDate) {
        return bookingRepository.findAllByRoomIdAndStartDateBetween(roomId, startDate, endDate);
    }

//    public List<PaidBookingResponse> findPaidBookingResponseByRoomId(Long roomId, String startDate, String endDate) {
//        List<PaidBookingResponse> paidBookingResponses = new ArrayList<>();
//        // <hotelId, roomId, roomDetailId, targetDate, totalDate, startDate, endDate>
//        List<Map<String, Object>> maps = bookingRepository.findPaidBookingResponseByRoomId(roomId, startDate, endDate);
//        for (Map<String, Object> map : maps) {
//            PaidBookingResponse response = new PaidBookingResponse(
//                (Long) map.get("hotelId"),
//                (Long) map.get("roomId"),
//                (Long) map.get("roomDetailId"),
//                (String) map.get("targetDate"),
//                (Integer) map.get("totalDate"),
//                (String) map.get("startDate"),
//                (String) map.get("endDate"),
//                (String) map.get("paymentId")
//            );
//            paidBookingResponses.add(response);
//        }
//        return paidBookingResponses;
//    }

    public Map<String, Object> findRoomBookingStatus(Long roomId, String startDate, String endDate) {
        List<Object[]> roomBookingStatus = new ArrayList<>(); // <roomId, startDate, endDate>
        roomBookingStatus = bookingRepository.findRoomBookingStatus(roomId, startDate, endDate);

        List<Map<String, Object>> result = new ArrayList<>();  // Map 형태로 수정

        for(Object[] obj : roomBookingStatus) {
            String roomName = (String) obj[0];
            Long roomDetailId = (Long) obj[1];
            String bookingData = (String) obj[2];
            String[] dataList = bookingData.split(",");

            Map<String, Object> roomData = new HashMap<>();
            roomData.put("roomName", roomName);  // roomId
            roomData.put("roomDetailId", roomDetailId);
            roomData.put("dates", Arrays.asList(dataList));  // 예약 상태를 날짜별로 추가

            result.add(roomData);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roomData", result);

        return resultMap;
    }







}
