package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "booking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id", updatable = false)
    private UUID bookingId;

    @Column(name = "userid", updatable = false)
    private Long userId;

    @Column(name = "hotel_id", updatable = false)
    private Long hotelId;

    @Column(name = "roonm_id", updatable = false)
    private Long roomId;

    @Column(name = "start_date", updatable = false)
    private String startDate;

    @Column(name = "end_date", updatable = false)
    private String endDate;

    @Column(name = "booking_guest")
    private int bookingGuest;

    @Column(name = "booking_pet")
    private int bookingPet;

    @Column(name = "pay_chk")
    private String payChk;

    @Column(name = "total_price", updatable = false)
    private int totalPrice;

    @Column(name = "total_date", updatable = false)
    private int totalDate;

    @Column(name = "payment_id", updatable = false)
    private String paymentId;


    @Builder
    public Booking (Long userId, Long hotelId, Long roomId,
                    String startDate, String endDate,
                    int bookingGuest, int bookingPet,
                    String payChk, int totalPrice, int totalDate,
                    String paymentId) {
        this.userId = userId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingGuest = bookingGuest;
        this.bookingPet = bookingPet;
        this.payChk = payChk;
        this.totalPrice = totalPrice;
        this.totalDate = totalDate;
        this.paymentId = paymentId;
    }

    public void updatePaycheck(String payChk, String paymentId) {
        this.payChk = payChk;
        this.paymentId = paymentId;
    }


}
