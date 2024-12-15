package com.example.pethotel.dto.manager;

import lombok.Getter;

@Getter
public class PaidBookingResponse {
    private Long hotelId;
    private Long roomId;
    private Long roomDetailId;
    private String targetDate;
    private int totalDate;
    private String startDate;
    private String endDate;
    private String paymentId;

    public PaidBookingResponse(Long hotelId, Long roomId, Long roomDetailId, String targetDate, int totalDate,
                               String startDate, String endDate, String paymentId) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomDetailId = roomDetailId;
        this.targetDate = targetDate;
        this.totalDate = totalDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentId = paymentId;
    }
}
