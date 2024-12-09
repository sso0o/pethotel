package com.example.pethotel.service;

import com.example.pethotel.dto.hotel.AddBookingRequest;
import com.example.pethotel.entity.Booking;
import com.example.pethotel.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
