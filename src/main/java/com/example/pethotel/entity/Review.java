package com.example.pethotel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long reviewId;

    @Column(name = "review_rate")
    private Integer reviewRate;

    @Column(name = "review_content")
    private String reviewContent;

    @CreationTimestamp
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateAt;

    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "userid")
    private Long userId;

    @Builder
    public Review(Integer reviewRate, String reviewContent,
                  LocalDateTime createAt, LocalDateTime updateAt,
                  Long bookingId, Long userId) {
        this.reviewRate = reviewRate;
        this.reviewContent = reviewContent;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.bookingId = bookingId;
        this.userId = userId;
    }

    public void update(Integer reviewRate, String reviewContent) {
        this.reviewRate = reviewRate;
        this.reviewContent = reviewContent;
    }
}
