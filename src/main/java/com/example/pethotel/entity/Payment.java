package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Table(name = "payment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pay_id")
    private UUID payId;

    @Column(name = "pay_date")
    private String payDate;

    @Column(name = "pay_amount")
    private Integer payAmount;

    @Column(name = "pay_date_num")
    private Integer payDateNum;

    @Column(name = "booking_id")
    private UUID bookingId;

    @Column(name = "userid")
    private String userId;

    @Builder
    public Payment(String payDate, Integer payAmount, Integer payDateNum, UUID bookingId, String userId) {
        this.payDate = payDate;
        this.payAmount = payAmount;
        this.payDateNum = payDateNum;
        this.bookingId = bookingId;
        this.userId = userId;
    }

}
