package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "booking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id", updatable = false)
    private Long bookingId;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "roonm_id")
    private Long roonmId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "booking_guest")
    private int bookingGuest;

    @Column(name = "booking_pet")
    private int bookingPet;

    @Column(name = "pay_chk")
    private String payChk;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "guest_name")
    private String guestName;

    @Column(name = "guest_phone")
    private String guestPhone;

    @Builder
    public Booking (Long userId, Long hotelId, Long roonmId,
                    String startDate, String endDate,
                    int bookingGuest, int bookingPet,
                    String payChk, int totalPrice,
                    String guestName, String guestPhone) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.roonmId = roonmId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;
        this.payChk = payChk;
        this.totalPrice = totalPrice;
        this.guestName = guestName;
        this.guestPhone = guestPhone;
    }




}
