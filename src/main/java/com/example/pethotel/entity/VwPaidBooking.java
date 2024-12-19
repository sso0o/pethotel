package com.example.pethotel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "vw_paid_booking")
public class VwPaidBooking {

    @Id
    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_d_id")
    private Long roomDetailId;

    @Column(name = "target_date")
    private String targetDate;

    @Column(name = "total_date")
    private int totalDate;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "payment_id")
    private String paymentId;
}
