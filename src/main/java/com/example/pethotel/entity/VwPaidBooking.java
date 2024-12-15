package com.example.pethotel.entity;

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
    private UUID bookingId;

    private Long hotelId;
    private Long roomId;
    private Long roomDetailId;
    private String targetDate;
    private int totalDate;
    private String startDate;
    private String endDate;
    private String paymentId;
}
