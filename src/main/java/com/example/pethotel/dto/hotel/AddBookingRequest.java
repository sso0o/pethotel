package com.example.pethotel.dto.hotel;

import com.example.pethotel.entity.Booking;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AddBookingRequest {
    private Long userId;
    private Long roomId;
    private Long hotelId;
    private String checkIn;
    private String checkOut;
    private int guest;
    private int pet;
    private int totalPrice;
    private int totalDate;
    private String paymentId;

    public Booking toEntity() {
        return Booking.builder()
                .userId(userId)
                .roomId(roomId)
                .hotelId(hotelId)
                .startDate(checkIn)
                .endDate(checkOut)
                .bookingGuest(guest)
                .bookingPet(pet)
                .totalPrice(totalPrice)
                .totalDate(totalDate)
                .paymentId(paymentId)
                .build();

    }
}
